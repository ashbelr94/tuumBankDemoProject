package com.tuum.bank.accountservice.Dto;

import javax.annotation.Generated;
import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    private String transactionId;
    
    private BigDecimal amount;
    
    private Date createAt;
    
    private String currency;
    
    private String description;
    
    private String transactionDirection;
    
    private Date updateAt;
    
    private String accountId;
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public Date getCreateAt() {
        return createAt;
    }
    
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getTransactionDirection() {
        return transactionDirection;
    }
    
    public void setTransactionDirection(String transactionDirection) {
        this.transactionDirection = transactionDirection;
    }
    
    public Date getUpdateAt() {
        return updateAt;
    }
    
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
    
    public String getAccountId() {
        return accountId;
    }
    
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}