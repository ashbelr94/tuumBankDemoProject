package com.tuum.bank.accountservice.example.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class AccountsDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.021852+05:30", comments="Source Table: public.accounts")
    public static final Accounts accounts = new Accounts();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.022138+05:30", comments="Source field: public.accounts.account_id")
    public static final SqlColumn<String> accountId = accounts.accountId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.022441+05:30", comments="Source field: public.accounts.user_id")
    public static final SqlColumn<String> userId = accounts.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.022528+05:30", comments="Source field: public.accounts.create_at")
    public static final SqlColumn<Date> createAt = accounts.createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.022607+05:30", comments="Source field: public.accounts.update_at")
    public static final SqlColumn<Date> updateAt = accounts.updateAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.02271+05:30", comments="Source field: public.accounts.balance")
    public static final SqlColumn<BigDecimal> balance = accounts.balance;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.022782+05:30", comments="Source field: public.accounts.country")
    public static final SqlColumn<String> country = accounts.country;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.022852+05:30", comments="Source field: public.accounts.currency")
    public static final SqlColumn<String> currency = accounts.currency;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.022013+05:30", comments="Source Table: public.accounts")
    public static final class Accounts extends AliasableSqlTable<Accounts> {
        public final SqlColumn<String> accountId = column("\"account_id\"", JDBCType.VARCHAR);

        public final SqlColumn<String> userId = column("\"user_id\"", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("\"create_at\"", JDBCType.DATE);

        public final SqlColumn<Date> updateAt = column("\"update_at\"", JDBCType.DATE);

        public final SqlColumn<BigDecimal> balance = column("\"balance\"", JDBCType.NUMERIC);

        public final SqlColumn<String> country = column("\"country\"", JDBCType.VARCHAR);

        public final SqlColumn<String> currency = column("\"currency\"", JDBCType.VARCHAR);

        public Accounts() {
            super("\"public\".\"accounts\"", Accounts::new);
        }
    }
}