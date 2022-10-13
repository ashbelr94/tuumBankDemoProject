package com.tuum.bank.transactionservice.example.mapper;

import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import javax.annotation.Generated;
import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.Date;

public final class TransactionDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.425696+05:30", comments="Source Table: public.transaction")
    public static final Transaction transaction = new Transaction();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.425848+05:30", comments="Source field: public.transaction.transaction_id")
    public static final SqlColumn<String> transactionId = transaction.transactionId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.42593+05:30", comments="Source field: public.transaction.amount")
    public static final SqlColumn<BigDecimal> amount = transaction.amount;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.425999+05:30", comments="Source field: public.transaction.create_at")
    public static final SqlColumn<Date> createAt = transaction.createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.426081+05:30", comments="Source field: public.transaction.currency")
    public static final SqlColumn<String> currency = transaction.currency;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.42615+05:30", comments="Source field: public.transaction.description")
    public static final SqlColumn<String> description = transaction.description;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.426255+05:30", comments="Source field: public.transaction.transaction_direction")
    public static final SqlColumn<String> transactionDirection = transaction.transactionDirection;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.426323+05:30", comments="Source field: public.transaction.update_at")
    public static final SqlColumn<Date> updateAt = transaction.updateAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.426418+05:30", comments="Source field: public.transaction.account_id")
    public static final SqlColumn<String> accountId = transaction.accountId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.425771+05:30", comments="Source Table: public.transaction")
    public static final class Transaction extends AliasableSqlTable<Transaction> {
        public final SqlColumn<String> transactionId = column("\"transaction_id\"", JDBCType.VARCHAR);

        public final SqlColumn<BigDecimal> amount = column("\"amount\"", JDBCType.NUMERIC);

        public final SqlColumn<Date> createAt = column("\"create_at\"", JDBCType.TIMESTAMP);

        public final SqlColumn<String> currency = column("\"currency\"", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("\"description\"", JDBCType.VARCHAR);

        public final SqlColumn<String> transactionDirection = column("\"transaction_direction\"", JDBCType.VARCHAR);

        public final SqlColumn<Date> updateAt = column("\"update_at\"", JDBCType.TIMESTAMP);

        public final SqlColumn<String> accountId = column("\"account_id\"", JDBCType.VARCHAR);

        public Transaction() {
            super("\"public\".\"transaction\"", Transaction::new);
        }
    }
}