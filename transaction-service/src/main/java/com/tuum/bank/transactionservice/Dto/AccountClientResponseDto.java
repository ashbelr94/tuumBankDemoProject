package com.tuum.bank.transactionservice.Dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tuum.bank.transactionservice.example.model.Accounts;
import com.tuum.bank.transactionservice.exception.CustomExceptionResponse;

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

    @JsonCreator
    public void setCustomExceptionResponse(@JsonProperty("customExceptionResponse") CustomExceptionResponse customExceptionResponse) {
        this.customExceptionResponse = customExceptionResponse;
    }
}
