package com.tuum.bank.accountservice.Service;

import com.tuum.bank.accountservice.Dto.AccountCreateDto;
import com.tuum.bank.accountservice.example.model.Accounts;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    List<Accounts> findAllAccounts();

    Accounts createAccountUsingMyBatis(AccountCreateDto accountCreateDto);

    Accounts findAccountByAccountId(String accountId);

    int updateAccountBalance(Accounts accounts, BigDecimal balance);
}
