package com.tuum.bank.accountservice.Controller;

import com.tuum.bank.accountservice.Dto.AccountBalanceUpdateDto;
import com.tuum.bank.accountservice.Dto.AccountClientResponseDto;
import com.tuum.bank.accountservice.Dto.AccountCreateDto;
import com.tuum.bank.accountservice.Service.AccountService;
import com.tuum.bank.accountservice.Service.MapValidationErrorService;
import com.tuum.bank.accountservice.example.model.Accounts;
import com.tuum.bank.accountservice.exception.CustomExceptionResponse;
import com.tuum.bank.accountservice.exception.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/accounts")
public class AccountsController {

    private AccountService accountService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;


    @Autowired
    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/mybatis/getAllAccounts")
    public ResponseEntity<?> findAllAccounts() {
        return new ResponseEntity<>(accountService.findAllAccounts(), HttpStatus.OK);
    }

    @PostMapping("/mybatis/createAccount")
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountCreateDto accountCreateDto, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null)
            return errorMap;
        try {
            Accounts account = accountService.createAccountUsingMyBatis(accountCreateDto);
            return new ResponseEntity<>(account, HttpStatus.CREATED);
        } catch(Exception ex){
            ErrorResponse err = new ErrorResponse();
            err.setErrorMessage(ex.getMessage());
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/hibernate/createAccount")
//    public ResponseEntity<?> createAccountHibernate(@Valid @RequestBody AccountCreateDto accountCreateDto, BindingResult result) {
//
//        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
//        if (errorMap != null)
//            return errorMap;
//        try {
//            Account account = accountService.createAccount(accountCreateDto);
//            return new ResponseEntity<>(account, HttpStatus.CREATED);
//        } catch (Exception ex){
//            ErrorResponse err = new ErrorResponse();
//            err.setErrorMessage(ex.getMessage());
//            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping("/getAccount")
    public ResponseEntity<?> findAccountByAccountId(@RequestParam String accountId){

        try {
            Accounts account = accountService.findAccountByAccountId(accountId);
            AccountClientResponseDto responseDto = new AccountClientResponseDto();
            responseDto.setAccount(account);
            responseDto.setStatus("SUCCESS");
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception ex){
            ErrorResponse err = new ErrorResponse();
            err.setErrorMessage(ex.getMessage());
            AccountClientResponseDto responseDto = new AccountClientResponseDto();
            responseDto.setStatus("ERROR");
            responseDto.setCustomExceptionResponse(new CustomExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), err.getErrorMessage()));
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
    }

    @PatchMapping("/updateBalance")
    public ResponseEntity<?> updateAccountBalance(@RequestBody AccountBalanceUpdateDto accountBalanceUpdateDto){
        try {
            Accounts account = accountService.findAccountByAccountId(accountBalanceUpdateDto.getAccountId());
            int acc = accountService.updateAccountBalance(account, accountBalanceUpdateDto.getBalance());
            AccountClientResponseDto accountClientResponseDto = new AccountClientResponseDto();
            accountClientResponseDto.setAccount(account);
            accountClientResponseDto.setStatus("SUCCESS");
            return new ResponseEntity<>(accountClientResponseDto, HttpStatus.OK);
        } catch (Exception ex){
            ErrorResponse err = new ErrorResponse();
            err.setErrorMessage(ex.getMessage());
            AccountClientResponseDto responseDto = new AccountClientResponseDto();
            responseDto.setStatus("ERROR");
            responseDto.setCustomExceptionResponse(new CustomExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), err.getErrorMessage()));
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
    }

}
