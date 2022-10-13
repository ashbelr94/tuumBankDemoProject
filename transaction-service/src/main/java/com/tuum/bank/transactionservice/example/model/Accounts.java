package com.tuum.bank.transactionservice.example.model;

import javax.annotation.Generated;
import java.math.BigDecimal;
import java.util.Date;

public class Accounts {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.40412+05:30", comments="Source field: public.accounts.account_id")
    private String accountId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.405698+05:30", comments="Source field: public.accounts.user_id")
    private String userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.405894+05:30", comments="Source field: public.accounts.create_at")
    private Date createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.406098+05:30", comments="Source field: public.accounts.update_at")
    private Date updateAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.406274+05:30", comments="Source field: public.accounts.balance")
    private BigDecimal balance;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.406463+05:30", comments="Source field: public.accounts.country")
    private String country;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.406641+05:30", comments="Source field: public.accounts.currency")
    private String currency;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.40532+05:30", comments="Source field: public.accounts.account_id")
    public String getAccountId() {
        return accountId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.405628+05:30", comments="Source field: public.accounts.account_id")
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.405762+05:30", comments="Source field: public.accounts.user_id")
    public String getUserId() {
        return userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.405836+05:30", comments="Source field: public.accounts.user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.405968+05:30", comments="Source field: public.accounts.create_at")
    public Date getCreateAt() {
        return createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.406036+05:30", comments="Source field: public.accounts.create_at")
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.406159+05:30", comments="Source field: public.accounts.update_at")
    public Date getUpdateAt() {
        return updateAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.406219+05:30", comments="Source field: public.accounts.update_at")
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.406339+05:30", comments="Source field: public.accounts.balance")
    public BigDecimal getBalance() {
        return balance;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.406406+05:30", comments="Source field: public.accounts.balance")
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.406523+05:30", comments="Source field: public.accounts.country")
    public String getCountry() {
        return country;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.406585+05:30", comments="Source field: public.accounts.country")
    public void setCountry(String country) {
        this.country = country;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.406712+05:30", comments="Source field: public.accounts.currency")
    public String getCurrency() {
        return currency;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.406776+05:30", comments="Source field: public.accounts.currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}