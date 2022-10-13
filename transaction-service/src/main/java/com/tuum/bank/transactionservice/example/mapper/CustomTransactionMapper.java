package com.tuum.bank.transactionservice.example.mapper;

import com.tuum.bank.transactionservice.example.model.Transaction;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;

import javax.annotation.Generated;

import static com.tuum.bank.transactionservice.example.mapper.TransactionDynamicSqlSupport.accountId;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface CustomTransactionMapper extends TransactionMapper {

    default int deleteByAccountId(String accountId_) {
        return delete(c ->
                c.where(accountId, isEqualTo(accountId_))
        );
    }
}
