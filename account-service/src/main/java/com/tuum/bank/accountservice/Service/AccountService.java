package com.tuum.bank.accountservice.Service;

import com.tuum.bank.accountservice.Dto.AccountClientResponseDto;
import com.tuum.bank.accountservice.Dto.AccountCreateDto;
import com.tuum.bank.accountservice.example.model.Accounts;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    List<Accounts> findAllAccounts();

    Accounts createAccount(AccountCreateDto accountCreateDto);

    Accounts findAccountByAccountId(String accountId);

    int updateAccountBalance(Accounts accounts, BigDecimal balance);

    Mono<Accounts> findAccountByAccountIdWebClient(Mono<String> accountId);

    Flux<Accounts> findAllAccountsWebClient();

    Mono<AccountClientResponseDto> updateAccountBalanceWebclient(Mono<Accounts> accounts, BigDecimal balance);

    Accounts findAccountByUserId(String userId);

    String deleteAccountByUserId(Accounts userId);
}
