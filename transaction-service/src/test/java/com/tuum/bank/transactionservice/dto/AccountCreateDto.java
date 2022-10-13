package com.tuum.bank.transactionservice.dto;

import com.tuum.bank.transactionservice.AnnotationValidation.ValueOfEnum;
import com.tuum.bank.transactionservice.Enum.Currency;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


public class AccountCreateDto {

    @NotBlank
    @NotNull(message = "User id is required")
    private String userId;

    @Min(1)
    @NotNull(message = "Please provide opening Balance")
    private BigDecimal balance;

    @NotBlank
    @NotNull(message = "Need country ")
    private String country;

    @ValueOfEnum(message = "Please input a valid currency, Allowed currencies ('EUR', 'SEK', 'GBP', 'USD') ", enumClazz = Currency.class)
    @NotNull(message = "Requires currency field")
    private String currency;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
