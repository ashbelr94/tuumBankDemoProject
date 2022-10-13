package com.tuum.bank.transactionservice.Dto;


import com.tuum.bank.transactionservice.example.model.Accounts;
import com.tuum.bank.transactionservice.exception.BindingResultErrorResponse;
import com.tuum.bank.transactionservice.exception.CustomExceptionResponse;

public class AccountClientResponseDto {
    private Accounts account;
    private String status;
    private CustomExceptionResponse customExceptionResponse;
    private BindingResultErrorResponse bindingResultErrorResponse;


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

    public BindingResultErrorResponse getBindingResultErrorResponse() {
        return bindingResultErrorResponse;
    }

    public void setBindingResultErrorResponse(BindingResultErrorResponse bindingResultErrorResponse) {
        this.bindingResultErrorResponse = bindingResultErrorResponse;
    }
}
