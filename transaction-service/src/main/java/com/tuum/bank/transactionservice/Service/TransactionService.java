package com.tuum.bank.transactionservice.Service;

import com.tuum.bank.transactionservice.Dto.TransactionDto;
import com.tuum.bank.transactionservice.responseDto.AccountAndTransactionResponseDto;
import com.tuum.bank.transactionservice.responseDto.TransactionResponseDto;

public interface TransactionService {

    TransactionResponseDto createTransaction(TransactionDto transactionDto);

    AccountAndTransactionResponseDto findAllTransactionByAccountId(String accountId);

}
