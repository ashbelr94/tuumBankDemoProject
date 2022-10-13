package com.tuum.bank.accountservice.event;

import com.tuum.bank.accountservice.example.model.Accounts;

public class AccountEvent {

    private String status;
    private String message;
    private Accounts account;

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

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "AccountEvent{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", account=" + account +
                '}';
    }
}
