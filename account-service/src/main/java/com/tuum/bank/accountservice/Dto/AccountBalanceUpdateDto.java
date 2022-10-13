package com.tuum.bank.accountservice.Dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AccountBalanceUpdateDto {

    @NotNull(message = "Must have account Id")
    private String accountId;

    @NotNull(message = "Must have a value")
    @Min(value = 1, message = "Must have value greater than 0")
    private BigDecimal balance;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
