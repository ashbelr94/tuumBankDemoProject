package com.tuum.bank.transactionservice.ServiceImp;

import com.tuum.bank.transactionservice.Dto.AccountBalanceUpdateDto;
import com.tuum.bank.transactionservice.Dto.AccountClientResponseDto;
import com.tuum.bank.transactionservice.Dto.TransactionDto;
import com.tuum.bank.transactionservice.Events.TransactionEvent;
import com.tuum.bank.transactionservice.Service.TransactionService;
import com.tuum.bank.transactionservice.example.mapper.CustomTransactionMapper;
import com.tuum.bank.transactionservice.example.mapper.TransactionMapper;
import com.tuum.bank.transactionservice.example.model.Transaction;
import com.tuum.bank.transactionservice.exception.CustomException;
import com.tuum.bank.transactionservice.publisher.TransactionProducer;
import com.tuum.bank.transactionservice.responseDto.AccountAndTransactionResponseDto;
import com.tuum.bank.transactionservice.responseDto.TransactionResponseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.tuum.bank.transactionservice.example.mapper.TransactionDynamicSqlSupport.transaction;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

@Service
public class TransactionServiceImpl implements TransactionService {


    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private CustomTransactionMapper customTransactionMapper;

    @Autowired
    private Resilience4JCircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    private TransactionProducer transactionProducer;

    @Autowired
    private WebClient.Builder webClientBuilder;


//    @Override
//    public TransactionResponseDto createTransactionHibernate(TransactionDto transactionDto) {
//
//        Transaction transaction = new Transaction();
//        Account account = accountRepository.findByAccountId(transactionDto.getAccountId())
//                .orElseThrow(() ->
//                        new CustomException(String.format("Account Id %s Does not Exists, Transaction Failed",
//                                transactionDto.getAccountId())));
//
//        BeanUtils.copyProperties(transactionDto, transaction);
//
//        if(!transaction.getCurrency().equals(account.getCurrency())){
//            throw new CustomException(String.format("Invalid currency please use your original currency: %s",account.getCurrency()));
//        }
//
//        transaction.setTransactionId(UUID.randomUUID().toString());
//        if(transaction.getTransactionDirection().equals("IN")){
//
//            account.setBalance(account.getBalance().add(transaction.getAmount()));
//            transaction.setAccount(account);
//        }else {
//            if(account.getBalance().doubleValue() < transaction.getAmount().doubleValue()){
//                throw new CustomException(String.format("Insufficient Funds to make this withdrawal, Available Balance: %s",account.getBalance()));
//            }
//            account.setBalance(account.getBalance().subtract(transaction.getAmount()));
//            transaction.setAccount(account);
//        }
//
//        account = accountRepository.save(account);
//        transactionRepository.save(transaction);
//        TransactionResponseDto responseDto = new TransactionResponseDto();
//        BeanUtils.copyProperties(transaction, responseDto);
//        responseDto.setAccountId(account.getAccountId());
//        responseDto.setBalance(account.getBalance());
//        return responseDto;
//    }

    @Override
    @CircuitBreaker(name = "accounts", fallbackMethod = "fallback")
    public TransactionResponseDto createTransaction(TransactionDto transactionDto) {


        AccountClientResponseDto accountsDto = webClientBuilder.build().get().uri("http://account-service/api/v1/accounts/getAccount",
                        uriBuilder -> uriBuilder.queryParam("accountId", transactionDto.getAccountId()).build())
                .retrieve()
                .bodyToMono(AccountClientResponseDto.class)
                .block();
        if(accountsDto == null){
            throw new CustomException("Account Not Found");
        }else if(accountsDto.getStatus().equals("ERROR")){
            throw new CustomException(accountsDto.getCustomExceptionResponse().getMessage());
        }
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionDto, transaction);

        if(!transaction.getCurrency().equals(accountsDto.getAccount().getCurrency())){
            throw new CustomException(String.format("Invalid currency please use your original currency: %s",accountsDto.getAccount().getCurrency()));
        }

        transaction.setTransactionId(UUID.randomUUID().toString());
        if(transaction.getTransactionDirection().equals("IN")){
            accountsDto.getAccount().setBalance(accountsDto.getAccount().getBalance().add(transaction.getAmount()));
            transaction.setAmount(transactionDto.getAmount());
        }else {
            if(accountsDto.getAccount().getBalance().doubleValue() < transaction.getAmount().doubleValue()){
                throw new CustomException(String.format("Insufficient Funds to make this withdrawal, Available Balance: %s",accountsDto.getAccount().getBalance()));
            }
            accountsDto.getAccount().setBalance(accountsDto.getAccount().getBalance().subtract(transaction.getAmount()));
            transaction.setAmount(transactionDto.getAmount());
        }

        AccountBalanceUpdateDto accountBalanceUpdateDto = new AccountBalanceUpdateDto();
        accountBalanceUpdateDto.setAccountId(accountsDto.getAccount().getAccountId());
        accountBalanceUpdateDto.setBalance(accountsDto.getAccount().getBalance());



        AccountClientResponseDto acc = webClientBuilder.build().patch().uri("http://account-service/api/v1/accounts/updateBalance")
                .body(Mono.just(accountBalanceUpdateDto), AccountBalanceUpdateDto.class)
                .retrieve()
                .bodyToMono(AccountClientResponseDto.class).block();

        if(acc.getStatus().equals("ERROR")){
            throw new CustomException(acc.getCustomExceptionResponse().getMessage());
        }

        int s = transactionMapper.insert(transaction);
        transaction = transactionMapper.selectByPrimaryKey(transaction.getTransactionId())
                .orElseThrow(() -> new CustomException("Transaction not available"));
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();

        BeanUtils.copyProperties(transaction, transactionResponseDto);
        transactionResponseDto.setBalance(acc.getAccount().getBalance());

        TransactionEvent transactionEvent = new TransactionEvent();
        transactionEvent.setMessage(String.format("Transaction of %s %s is created for user: %s", transaction.getAmount(),
                transaction.getCurrency(),
                accountsDto.getAccount().getUserId()));
        transactionEvent.setStatus("CREATED");
        transactionEvent.setTransaction(transactionResponseDto);


        transactionProducer.sendMessage(transactionEvent);

        return transactionResponseDto;
    }

    public TransactionResponseDto fallback(TransactionDto transactionDto, RuntimeException runtimeException){
        if(runtimeException.getMessage().contains("api/v1/accounts/updateBalance")) {
            throw new CustomException("Account Balance updated, transaction not persisted, Roll back Account Balance Update");
        } else {
            throw runtimeException;
        }

    }


    public AccountAndTransactionResponseDto findAllTransactionByAccountId(String accountId) {
        AccountClientResponseDto accountsDto = webClientBuilder.build().get().uri("http://account-service/api/v1/accounts/getAccount",
                        uriBuilder -> uriBuilder.queryParam("accountId", accountId).build())
                .retrieve()
                .bodyToMono(AccountClientResponseDto.class)
                .block();
        SelectStatementProvider allTransactionQuery = SqlBuilder.select(transaction.allColumns())
                .from(transaction).where(transaction.accountId, isLike(accountId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<Transaction> transactionsList = transactionMapper.selectMany(allTransactionQuery);
        AccountAndTransactionResponseDto accAndTransacRepo = new AccountAndTransactionResponseDto();
        accAndTransacRepo.setAccounts(accountsDto.getAccount());
        accAndTransacRepo.setTransactions(transactionsList);
        return accAndTransacRepo;
    }


    public AccountAndTransactionResponseDto findAllTransactionByAccountIdWebClient(String accountId) {
        Mono<AccountClientResponseDto> accountsDto = webClientBuilder.build().get().uri("http://account-service/api/v1/accounts/webclient/getAccount",
                        uriBuilder -> uriBuilder.queryParam("accountId", accountId).build())
                .retrieve()
                .bodyToMono(AccountClientResponseDto.class);
        if(accountsDto == null){
            throw new CustomException("Internal Server Issue, Please try again later");
        }

        SelectStatementProvider allTransactionQuery = SqlBuilder.select(transaction.allColumns())
                .from(transaction).where(transaction.accountId, isLike(accountId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        Flux<Transaction> transactionsList = Flux.fromStream(transactionMapper.selectMany(allTransactionQuery).stream());

        AccountAndTransactionResponseDto accAndTransacRepo = new AccountAndTransactionResponseDto();
        accAndTransacRepo.setAccounts(accountsDto.block().getAccount());
        accAndTransacRepo.setTransactions(transactionsList.toStream().collect(Collectors.toList()));
        return accAndTransacRepo;
    }

    @Override
    public String deleteAllTransactionsByAccountId(String accountId) {
        int i = customTransactionMapper.deleteByAccountId(accountId);
        if(i==0){
            throw new CustomException("Account Id: "+accountId+" Could not be deleted");
        }else {
            return "Successfully Deleted";
        }
    }
}
