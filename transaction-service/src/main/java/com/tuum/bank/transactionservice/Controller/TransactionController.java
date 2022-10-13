package com.tuum.bank.transactionservice.Controller;

import com.tuum.bank.transactionservice.Dto.TransactionDto;
import com.tuum.bank.transactionservice.Service.MapValidationErrorService;
import com.tuum.bank.transactionservice.Service.TransactionService;
import com.tuum.bank.transactionservice.example.model.Transaction;
import com.tuum.bank.transactionservice.exception.ErrorResponse;
import com.tuum.bank.transactionservice.responseDto.AccountAndTransactionResponseDto;
import com.tuum.bank.transactionservice.responseDto.TransactionResponseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/mybatis/createTransaction")
    public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionDto transactionDto, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null)
            return errorMap;
        try {
            TransactionResponseDto transaction = transactionService.createTransaction(transactionDto);
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        } catch (Exception ex){
            ErrorResponse err = new ErrorResponse();
            err.setErrorMessage(ex.getMessage());
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getTransactionsByAccountId")
    public ResponseEntity<?> getTransactionByAccId(@RequestParam String accountId){

        try {
            AccountAndTransactionResponseDto transactions = transactionService.findAllTransactionByAccountId(accountId);
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception ex){
            ErrorResponse err = new ErrorResponse();
            err.setErrorMessage(ex.getMessage());
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
    }
}
