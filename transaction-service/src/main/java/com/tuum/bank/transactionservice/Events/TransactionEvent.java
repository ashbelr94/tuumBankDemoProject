package com.tuum.bank.transactionservice.Events;

import com.tuum.bank.transactionservice.example.model.Transaction;
import com.tuum.bank.transactionservice.responseDto.TransactionResponseDto;

import java.util.List;

public class TransactionEvent {
    private String status;
    private String message;
    private TransactionResponseDto transaction;
    private List<Transaction> transactions;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TransactionResponseDto getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionResponseDto transaction) {
        this.transaction = transaction;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "TransactionEvent{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", transaction=" + transaction +
                ", transactions=" + transactions +
                '}';
    }
}
