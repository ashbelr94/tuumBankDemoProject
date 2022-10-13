package com.tuum.bank.transactionservice.example.mapper;

import com.tuum.bank.transactionservice.example.model.Transaction;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.*;

import javax.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.tuum.bank.transactionservice.example.mapper.TransactionDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface TransactionMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Transaction>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.427385+05:30", comments="Source Table: public.transaction")
    BasicColumn[] selectList = BasicColumn.columnList(transactionId, amount, createAt, currency, description, transactionDirection, updateAt, accountId);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.426492+05:30", comments="Source Table: public.transaction")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TransactionResult", value = {
        @Result(column="transaction_id", property="transactionId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="amount", property="amount", jdbcType=JdbcType.NUMERIC),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="currency", property="currency", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="transaction_direction", property="transactionDirection", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_at", property="updateAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.VARCHAR)
    })
    List<Transaction> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.426632+05:30", comments="Source Table: public.transaction")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TransactionResult")
    Optional<Transaction> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.426706+05:30", comments="Source Table: public.transaction")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, transaction, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.426775+05:30", comments="Source Table: public.transaction")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, transaction, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.426847+05:30", comments="Source Table: public.transaction")
    default int deleteByPrimaryKey(String transactionId_) {
        return delete(c -> 
            c.where(transactionId, isEqualTo(transactionId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.426914+05:30", comments="Source Table: public.transaction")
    default int insert(Transaction row) {
        return MyBatis3Utils.insert(this::insert, row, transaction, c ->
            c.map(transactionId).toProperty("transactionId")
            .map(amount).toProperty("amount")
            .map(createAt).toProperty("createAt")
            .map(currency).toProperty("currency")
            .map(description).toProperty("description")
            .map(transactionDirection).toProperty("transactionDirection")
            .map(updateAt).toProperty("updateAt")
            .map(accountId).toProperty("accountId")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.427046+05:30", comments="Source Table: public.transaction")
    default int insertMultiple(Collection<Transaction> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, transaction, c ->
            c.map(transactionId).toProperty("transactionId")
            .map(amount).toProperty("amount")
            .map(createAt).toProperty("createAt")
            .map(currency).toProperty("currency")
            .map(description).toProperty("description")
            .map(transactionDirection).toProperty("transactionDirection")
            .map(updateAt).toProperty("updateAt")
            .map(accountId).toProperty("accountId")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.427183+05:30", comments="Source Table: public.transaction")
    default int insertSelective(Transaction row) {
        return MyBatis3Utils.insert(this::insert, row, transaction, c ->
            c.map(transactionId).toPropertyWhenPresent("transactionId", row::getTransactionId)
            .map(amount).toPropertyWhenPresent("amount", row::getAmount)
            .map(createAt).toPropertyWhenPresent("createAt", row::getCreateAt)
            .map(currency).toPropertyWhenPresent("currency", row::getCurrency)
            .map(description).toPropertyWhenPresent("description", row::getDescription)
            .map(transactionDirection).toPropertyWhenPresent("transactionDirection", row::getTransactionDirection)
            .map(updateAt).toPropertyWhenPresent("updateAt", row::getUpdateAt)
            .map(accountId).toPropertyWhenPresent("accountId", row::getAccountId)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.427443+05:30", comments="Source Table: public.transaction")
    default Optional<Transaction> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, transaction, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.427505+05:30", comments="Source Table: public.transaction")
    default List<Transaction> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, transaction, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.427569+05:30", comments="Source Table: public.transaction")
    default List<Transaction> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, transaction, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.427627+05:30", comments="Source Table: public.transaction")
    default Optional<Transaction> selectByPrimaryKey(String transactionId_) {
        return selectOne(c ->
            c.where(transactionId, isEqualTo(transactionId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.427696+05:30", comments="Source Table: public.transaction")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, transaction, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.427766+05:30", comments="Source Table: public.transaction")
    static UpdateDSL<UpdateModel> updateAllColumns(Transaction row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(transactionId).equalTo(row::getTransactionId)
                .set(amount).equalTo(row::getAmount)
                .set(createAt).equalTo(row::getCreateAt)
                .set(currency).equalTo(row::getCurrency)
                .set(description).equalTo(row::getDescription)
                .set(transactionDirection).equalTo(row::getTransactionDirection)
                .set(updateAt).equalTo(row::getUpdateAt)
                .set(accountId).equalTo(row::getAccountId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.4279+05:30", comments="Source Table: public.transaction")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Transaction row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(transactionId).equalToWhenPresent(row::getTransactionId)
                .set(amount).equalToWhenPresent(row::getAmount)
                .set(createAt).equalToWhenPresent(row::getCreateAt)
                .set(currency).equalToWhenPresent(row::getCurrency)
                .set(description).equalToWhenPresent(row::getDescription)
                .set(transactionDirection).equalToWhenPresent(row::getTransactionDirection)
                .set(updateAt).equalToWhenPresent(row::getUpdateAt)
                .set(accountId).equalToWhenPresent(row::getAccountId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.428053+05:30", comments="Source Table: public.transaction")
    default int updateByPrimaryKey(Transaction row) {
        return update(c ->
            c.set(amount).equalTo(row::getAmount)
            .set(createAt).equalTo(row::getCreateAt)
            .set(currency).equalTo(row::getCurrency)
            .set(description).equalTo(row::getDescription)
            .set(transactionDirection).equalTo(row::getTransactionDirection)
            .set(updateAt).equalTo(row::getUpdateAt)
            .set(accountId).equalTo(row::getAccountId)
            .where(transactionId, isEqualTo(row::getTransactionId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-10T19:22:53.428203+05:30", comments="Source Table: public.transaction")
    default int updateByPrimaryKeySelective(Transaction row) {
        return update(c ->
            c.set(amount).equalToWhenPresent(row::getAmount)
            .set(createAt).equalToWhenPresent(row::getCreateAt)
            .set(currency).equalToWhenPresent(row::getCurrency)
            .set(description).equalToWhenPresent(row::getDescription)
            .set(transactionDirection).equalToWhenPresent(row::getTransactionDirection)
            .set(updateAt).equalToWhenPresent(row::getUpdateAt)
            .set(accountId).equalToWhenPresent(row::getAccountId)
            .where(transactionId, isEqualTo(row::getTransactionId))
        );
    }
}