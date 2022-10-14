package com.tuum.bank.accountservice.Dto;

import com.tuum.bank.accountservice.example.model.Accounts;
import com.tuum.bank.accountservice.exception.CustomExceptionResponse;

import java.util.List;

public class AccountAndTransactionResponseDto {
    private Accounts accounts;
    private List<Transaction> transactions;
    private String status;
    private CustomExceptionResponse customExceptionResponse;

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CustomExceptionResponse getCustomExceptionResponse() {
        return customExceptionResponse;
    }

    public void setCustomExceptionResponse(CustomExceptionResponse customExceptionResponse) {
        this.customExceptionResponse = customExceptionResponse;
    }
}
