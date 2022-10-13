package com.tuum.bank.transactionservice.responseDto;

import com.tuum.bank.transactionservice.example.model.Accounts;
import com.tuum.bank.transactionservice.example.model.Transaction;

import java.util.List;

public class AccountAndTransactionResponseDto {
    private Accounts accounts;
    private List<Transaction> transactions;

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
}
