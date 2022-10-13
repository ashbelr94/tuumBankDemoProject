package com.tuum.bank.accountservice.Dto;

import com.tuum.bank.accountservice.example.model.Accounts;
import com.tuum.bank.accountservice.exception.CustomExceptionResponse;

public class AccountClientResponseDto {
    private Accounts account;
    private String status;
    private CustomExceptionResponse customExceptionResponse;

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
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
