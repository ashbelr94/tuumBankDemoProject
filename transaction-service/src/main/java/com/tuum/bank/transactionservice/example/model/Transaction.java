package com.tuum.bank.transactionservice.example.model;

import javax.annotation.Generated;
import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.424517+05:30", comments="Source field: public.transaction.transaction_id")
    private String transactionId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.42469+05:30", comments="Source field: public.transaction.amount")
    private BigDecimal amount;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.424825+05:30", comments="Source field: public.transaction.create_at")
    private Date createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.424978+05:30", comments="Source field: public.transaction.currency")
    private String currency;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.425108+05:30", comments="Source field: public.transaction.description")
    private String description;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.425248+05:30", comments="Source field: public.transaction.transaction_direction")
    private String transactionDirection;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.425382+05:30", comments="Source field: public.transaction.update_at")
    private Date updateAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.425509+05:30", comments="Source field: public.transaction.account_id")
    private String accountId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.424578+05:30", comments="Source field: public.transaction.transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.424626+05:30", comments="Source field: public.transaction.transaction_id")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.424742+05:30", comments="Source field: public.transaction.amount")
    public BigDecimal getAmount() {
        return amount;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.424787+05:30", comments="Source field: public.transaction.amount")
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.424878+05:30", comments="Source field: public.transaction.create_at")
    public Date getCreateAt() {
        return createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.424924+05:30", comments="Source field: public.transaction.create_at")
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.425021+05:30", comments="Source field: public.transaction.currency")
    public String getCurrency() {
        return currency;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.425069+05:30", comments="Source field: public.transaction.currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.42516+05:30", comments="Source field: public.transaction.description")
    public String getDescription() {
        return description;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.425206+05:30", comments="Source field: public.transaction.description")
    public void setDescription(String description) {
        this.description = description;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.425298+05:30", comments="Source field: public.transaction.transaction_direction")
    public String getTransactionDirection() {
        return transactionDirection;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.425343+05:30", comments="Source field: public.transaction.transaction_direction")
    public void setTransactionDirection(String transactionDirection) {
        this.transactionDirection = transactionDirection;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.425425+05:30", comments="Source field: public.transaction.update_at")
    public Date getUpdateAt() {
        return updateAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.425468+05:30", comments="Source field: public.transaction.update_at")
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.425551+05:30", comments="Source field: public.transaction.account_id")
    public String getAccountId() {
        return accountId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.425597+05:30", comments="Source field: public.transaction.account_id")
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}