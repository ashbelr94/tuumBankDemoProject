package com.tuum.bank.transactionservice.Controller;

import com.tuum.bank.transactionservice.Dto.TransactionDto;
import com.tuum.bank.transactionservice.Service.MapValidationErrorService;
import com.tuum.bank.transactionservice.Service.TransactionService;
import com.tuum.bank.transactionservice.responseDto.AccountAndTransactionResponseDto;
import com.tuum.bank.transactionservice.responseDto.TransactionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
            TransactionResponseDto transaction = transactionService.createTransaction(transactionDto);
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);

    }


    @GetMapping("/getTransactionsByAccountId")
    public ResponseEntity<?> getTransactionByAccId(@RequestParam String accountId){

            AccountAndTransactionResponseDto transactions = transactionService.findAllTransactionByAccountId(accountId);
            return new ResponseEntity<>(transactions, HttpStatus.OK);

    }


    @GetMapping("/webclient/getTransactionsByAccountId")
    public ResponseEntity<?> getTransactionByAccIdWebclient(@RequestParam String accountId){

            AccountAndTransactionResponseDto transactions = transactionService.findAllTransactionByAccountIdWebClient(accountId);
            return new ResponseEntity<>(transactions, HttpStatus.OK);

    }

    @DeleteMapping("/deleteTransactionByAccountId")
    public ResponseEntity<?> deleteTransactionsByAccountId(@RequestParam String accountId){
        String response = transactionService.deleteAllTransactionsByAccountId(accountId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
