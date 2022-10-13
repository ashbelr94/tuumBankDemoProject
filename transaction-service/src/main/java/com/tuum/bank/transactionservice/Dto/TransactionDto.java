package com.tuum.bank.transactionservice.Dto;

import com.tuum.bank.transactionservice.AnnotationValidation.ValueOfEnum;
import com.tuum.bank.transactionservice.Enum.Currency;
import com.tuum.bank.transactionservice.Enum.TransactionType;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class TransactionDto {

    @NotNull(message = "account Id is mandatory to make a transaction")
    @NotBlank
    private String accountId;

    @Min(1)
    @NotNull(message = "Please input amount value")
    private BigDecimal amount;

    @ValueOfEnum(message = "Please input a valid currency, Allowed currencies ('EUR', 'SEK', 'GBP', 'USD') ", enumClazz = Currency.class)
    @NotNull(message = "Currency cannot be empty")
    private String currency;

    @ValueOfEnum(message = "Please specify a valid value: 'IN' for Deposit and 'OUT' for Withdrawal", enumClazz = TransactionType.class)
    @NotNull(message = "Please specify 'IN' for Deposit and 'OUT' for Withdrawal")
    private String transactionDirection;

    @NotNull(message = "Description Cannot be empty")
    @Size(min = 3, max = 36, message = "Description should be a minimum of 3 characters and maximum of 36 characters")
    private String description;


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTransactionDirection() {
        return transactionDirection;
    }

    public void setTransactionDirection(String transactionDirection) {
        this.transactionDirection = transactionDirection;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
