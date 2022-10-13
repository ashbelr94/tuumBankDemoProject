package com.tuum.bank.transactionservice.responseDto;

import com.tuum.bank.transactionservice.example.model.Accounts;
import com.tuum.bank.transactionservice.example.model.Transaction;
import reactor.core.publisher.Flux;

import java.util.List;

public class AccountAndTransactionResponseWebClientDto {
    private Accounts accounts;
    private Flux<Transaction> transactions;

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public Flux<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Flux<Transaction> transactions) {
        this.transactions = transactions;
    }
}
