package com.tuum.bank.accountservice.example.mapper;

import static com.tuum.bank.accountservice.example.mapper.AccountsDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.tuum.bank.accountservice.example.model.Accounts;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
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
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface AccountsMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Accounts>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.029927+05:30", comments="Source Table: public.accounts")
    BasicColumn[] selectList = BasicColumn.columnList(accountId, userId, createAt, updateAt, balance, country, currency);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.023339+05:30", comments="Source Table: public.accounts")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="AccountsResult", value = {
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.DATE),
        @Result(column="update_at", property="updateAt", jdbcType=JdbcType.DATE),
        @Result(column="balance", property="balance", jdbcType=JdbcType.NUMERIC),
        @Result(column="country", property="country", jdbcType=JdbcType.VARCHAR),
        @Result(column="currency", property="currency", jdbcType=JdbcType.VARCHAR)
    })
    List<Accounts> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.02492+05:30", comments="Source Table: public.accounts")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("AccountsResult")
    Optional<Accounts> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.025316+05:30", comments="Source Table: public.accounts")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, accounts, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.025666+05:30", comments="Source Table: public.accounts")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, accounts, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.026149+05:30", comments="Source Table: public.accounts")
    default int deleteByPrimaryKey(String accountId_) {
        return delete(c -> 
            c.where(accountId, isEqualTo(accountId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.026492+05:30", comments="Source Table: public.accounts")
    default int insert(Accounts row) {
        return MyBatis3Utils.insert(this::insert, row, accounts, c ->
            c.map(accountId).toProperty("accountId")
            .map(userId).toProperty("userId")
            .map(createAt).toProperty("createAt")
            .map(updateAt).toProperty("updateAt")
            .map(balance).toProperty("balance")
            .map(country).toProperty("country")
            .map(currency).toProperty("currency")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.028305+05:30", comments="Source Table: public.accounts")
    default int insertMultiple(Collection<Accounts> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, accounts, c ->
            c.map(accountId).toProperty("accountId")
            .map(userId).toProperty("userId")
            .map(createAt).toProperty("createAt")
            .map(updateAt).toProperty("updateAt")
            .map(balance).toProperty("balance")
            .map(country).toProperty("country")
            .map(currency).toProperty("currency")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.028955+05:30", comments="Source Table: public.accounts")
    default int insertSelective(Accounts row) {
        return MyBatis3Utils.insert(this::insert, row, accounts, c ->
            c.map(accountId).toPropertyWhenPresent("accountId", row::getAccountId)
            .map(userId).toPropertyWhenPresent("userId", row::getUserId)
            .map(createAt).toPropertyWhenPresent("createAt", row::getCreateAt)
            .map(updateAt).toPropertyWhenPresent("updateAt", row::getUpdateAt)
            .map(balance).toPropertyWhenPresent("balance", row::getBalance)
            .map(country).toPropertyWhenPresent("country", row::getCountry)
            .map(currency).toPropertyWhenPresent("currency", row::getCurrency)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.030552+05:30", comments="Source Table: public.accounts")
    default Optional<Accounts> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, accounts, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.030891+05:30", comments="Source Table: public.accounts")
    default List<Accounts> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, accounts, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.031263+05:30", comments="Source Table: public.accounts")
    default List<Accounts> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, accounts, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.031637+05:30", comments="Source Table: public.accounts")
    default Optional<Accounts> selectByPrimaryKey(String accountId_) {
        return selectOne(c ->
            c.where(accountId, isEqualTo(accountId_))
        );
    }

    default Optional<Accounts> selectByUserId(String userId_) {
        return selectOne(c ->
                c.where(userId, isEqualTo(userId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.031952+05:30", comments="Source Table: public.accounts")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, accounts, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.032257+05:30", comments="Source Table: public.accounts")
    static UpdateDSL<UpdateModel> updateAllColumns(Accounts row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(accountId).equalTo(row::getAccountId)
                .set(userId).equalTo(row::getUserId)
                .set(createAt).equalTo(row::getCreateAt)
                .set(updateAt).equalTo(row::getUpdateAt)
                .set(balance).equalTo(row::getBalance)
                .set(country).equalTo(row::getCountry)
                .set(currency).equalTo(row::getCurrency);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.032658+05:30", comments="Source Table: public.accounts")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Accounts row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(accountId).equalToWhenPresent(row::getAccountId)
                .set(userId).equalToWhenPresent(row::getUserId)
                .set(createAt).equalToWhenPresent(row::getCreateAt)
                .set(updateAt).equalToWhenPresent(row::getUpdateAt)
                .set(balance).equalToWhenPresent(row::getBalance)
                .set(country).equalToWhenPresent(row::getCountry)
                .set(currency).equalToWhenPresent(row::getCurrency);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.033285+05:30", comments="Source Table: public.accounts")
    default int updateByPrimaryKey(Accounts row) {
        return update(c ->
            c.set(userId).equalTo(row::getUserId)
            .set(createAt).equalTo(row::getCreateAt)
            .set(updateAt).equalTo(row::getUpdateAt)
            .set(balance).equalTo(row::getBalance)
            .set(country).equalTo(row::getCountry)
            .set(currency).equalTo(row::getCurrency)
            .where(accountId, isEqualTo(row::getAccountId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-10-12T02:39:19.033751+05:30", comments="Source Table: public.accounts")
    default int updateByPrimaryKeySelective(Accounts row) {
        return update(c ->
            c.set(userId).equalToWhenPresent(row::getUserId)
            .set(createAt).equalToWhenPresent(row::getCreateAt)
            .set(updateAt).equalToWhenPresent(row::getUpdateAt)
            .set(balance).equalToWhenPresent(row::getBalance)
            .set(country).equalToWhenPresent(row::getCountry)
            .set(currency).equalToWhenPresent(row::getCurrency)
            .where(accountId, isEqualTo(row::getAccountId))
        );
    }
}