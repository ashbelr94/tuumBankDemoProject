package com.tuum.bank.accountservice.example.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;

public class Accounts {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.016924+05:30", comments="Source field: public.accounts.account_id")
    private String accountId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.018501+05:30", comments="Source field: public.accounts.user_id")
    private String userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.018698+05:30", comments="Source field: public.accounts.create_at")
    private Date createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.018913+05:30", comments="Source field: public.accounts.update_at")
    private Date updateAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.01908+05:30", comments="Source field: public.accounts.balance")
    private BigDecimal balance;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.019251+05:30", comments="Source field: public.accounts.country")
    private String country;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.019424+05:30", comments="Source field: public.accounts.currency")
    private String currency;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.018115+05:30", comments="Source field: public.accounts.account_id")
    public String getAccountId() {
        return accountId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.01843+05:30", comments="Source field: public.accounts.account_id")
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.018561+05:30", comments="Source field: public.accounts.user_id")
    public String getUserId() {
        return userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.018632+05:30", comments="Source field: public.accounts.user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.018792+05:30", comments="Source field: public.accounts.create_at")
    public Date getCreateAt() {
        return createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.01885+05:30", comments="Source field: public.accounts.create_at")
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.018972+05:30", comments="Source field: public.accounts.update_at")
    public Date getUpdateAt() {
        return updateAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.019027+05:30", comments="Source field: public.accounts.update_at")
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.019142+05:30", comments="Source field: public.accounts.balance")
    public BigDecimal getBalance() {
        return balance;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.019198+05:30", comments="Source field: public.accounts.balance")
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.019314+05:30", comments="Source field: public.accounts.country")
    public String getCountry() {
        return country;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.019372+05:30", comments="Source field: public.accounts.country")
    public void setCountry(String country) {
        this.country = country;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.019481+05:30", comments="Source field: public.accounts.currency")
    public String getCurrency() {
        return currency;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.019537+05:30", comments="Source field: public.accounts.currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}