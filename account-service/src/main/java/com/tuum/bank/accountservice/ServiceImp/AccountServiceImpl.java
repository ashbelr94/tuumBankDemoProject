package com.tuum.bank.accountservice.ServiceImp;

import com.tuum.bank.accountservice.Dto.AccountClientResponseDto;
import com.tuum.bank.accountservice.Dto.AccountCreateDto;
import com.tuum.bank.accountservice.Service.AccountService;
import com.tuum.bank.accountservice.event.AccountEvent;
import com.tuum.bank.accountservice.example.mapper.AccountsMapper;
import com.tuum.bank.accountservice.example.mapper.CustomAccountsMapper;
import com.tuum.bank.accountservice.example.model.Accounts;
import com.tuum.bank.accountservice.exception.CustomException;
import com.tuum.bank.accountservice.publisher.AccountProducer;
import com.tuum.bank.accountservice.util.EntityDtoUtil;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.tuum.bank.accountservice.example.mapper.AccountsDynamicSqlSupport.*;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountsMapper accountsMapper;

    @Autowired
    private AccountProducer accountProducer;

    @Autowired
    private CustomAccountsMapper customAccountsMapper;


    @Autowired
    public AccountServiceImpl(AccountsMapper accountsMapper) {
        this.accountsMapper = accountsMapper;
    }


    @Autowired
    private WebClient.Builder webClientBuilder;


    @Override
    public List<Accounts> findAllAccounts() {
        SelectStatementProvider ac = SqlBuilder.select(accounts.allColumns())
                .from(accounts)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<Accounts> accountsList = accountsMapper.selectMany(ac);
        return accountsList;
    }

    @Override
    public Flux<Accounts> findAllAccountsWebClient() {
        SelectStatementProvider ac = SqlBuilder.select(accounts.allColumns())
                .from(accounts)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return Flux.fromStream(accountsMapper.selectMany(ac).stream());
    }




//    @Override
//    public Account createAccount(AccountCreateDto accountCreateDto) {
//        Account account = new Account();
//        BeanUtils.copyProperties(accountCreateDto, account);
//        account.setAccountId(UUID.randomUUID().toString());
//        return accountRepository.save(account);
//    }

    @Override
    public Accounts createAccount(AccountCreateDto accountCreateDto) {

        Accounts account = customAccountsMapper.selectByUserId(accountCreateDto.getUserId()).orElse(new Accounts());
        if(account.getAccountId() != null){
            throw new CustomException(String.format("Cannot Create Account, Account already exists for user: %s", accountCreateDto.getUserId()));
        }
        BeanUtils.copyProperties(accountCreateDto, account);
        account.setAccountId(UUID.randomUUID().toString());
        account.setCreateAt(new Date());
        int s = accountsMapper.insert(account);
        System.out.println(s);
        Accounts acc = accountsMapper.selectByPrimaryKey(account.getAccountId()).orElseThrow(() ->
                new CustomException("Could not retrieve or account not found"));
        AccountEvent accountEvent = new AccountEvent();
        accountEvent.setAccount(acc);
        accountEvent.setMessage(String.format("Account Created with user Id: %s", acc.getUserId()));
        accountEvent.setStatus("CREATED");

        // Sending RabbitMq Even Message
        accountProducer.sendMessage(accountEvent);
        return acc;
    }

    @Override
    public Accounts findAccountByAccountId(String accountId) {

        Accounts account = accountsMapper
                .selectByPrimaryKey(accountId).orElseThrow(() ->
                        new CustomException("Invalid Account / Account not Found"));
        return account;
    }


    public Accounts findAccountByUserId(String userId){
        return customAccountsMapper.selectByUserId(userId)
                .orElseThrow(() -> new CustomException(String.format("Account not found for userId: %s", userId)));
    }

    @Override
    public String deleteAccountByUserId(Accounts account) {

        int a = accountsMapper.deleteByPrimaryKey(account.getAccountId());
        if(a == 1){
            return "Delete Success";
        }else {
            throw new CustomException("Could not Delete Resources");
        }

    }

    @Override
    public int updateAccountBalance(Accounts account, BigDecimal balance) {
        account.setBalance(balance);
        account.setUpdateAt(new Date());
        int acc = accountsMapper.updateByPrimaryKey(account);
        if(acc == 0){
            throw new CustomException("Account Balance was not updated");
        }
        AccountEvent accountEvent = new AccountEvent();
        accountEvent.setAccount(account);
        accountEvent.setMessage(String.format("Account Balance has been updated: %s", account.getBalance()));
        accountEvent.setStatus("UPDATED");
        accountProducer.sendMessage(accountEvent);
        return acc;
    }

    @Override
    public Mono<AccountClientResponseDto> updateAccountBalanceWebclient(Mono<Accounts> account, BigDecimal balance) {

        return account.map(acc -> {
            acc.setBalance(balance);
            acc.setUpdateAt(new Date());
            int i = accountsMapper.updateByPrimaryKey(acc);
            if(i==0){
                throw new CustomException("Account Balance was not updated");
            }
            return acc;

        }).map(acc -> {
            accountProducer.sendMessage(EntityDtoUtil.toEvent(acc));
            return acc;
        }).map(EntityDtoUtil::toDto);
    }

    @Override
    public Mono<Accounts> findAccountByAccountIdWebClient(Mono<String> accountId) {
        return accountId.map(e -> accountsMapper
                .selectByPrimaryKey(e)
                .orElseThrow(() -> new CustomException("Invalid Account / Account not found")));
    }

}
