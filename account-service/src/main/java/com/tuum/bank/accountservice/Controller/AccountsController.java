package com.tuum.bank.accountservice.Controller;

import com.tuum.bank.accountservice.Dto.AccountBalanceUpdateDto;
import com.tuum.bank.accountservice.Dto.AccountClientResponseDto;
import com.tuum.bank.accountservice.Dto.AccountCreateDto;
import com.tuum.bank.accountservice.Service.AccountService;
import com.tuum.bank.accountservice.Service.MapValidationErrorService;
import com.tuum.bank.accountservice.example.model.Accounts;
import com.tuum.bank.accountservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @GetMapping("/webclient/getAllAccounts")
    public Flux<Accounts> findAllAccountsWebClient() {
        return accountService.findAllAccountsWebClient();
    }

    @GetMapping("/getAccountByUser")
    public ResponseEntity<?>  findAccountByUserId(@RequestParam String userId){
        Accounts account = accountService.findAccountByUserId(userId);
        AccountClientResponseDto responseDto = new AccountClientResponseDto();
        responseDto.setStatus("SUCCESS");
        responseDto.setAccount(account);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/mybatis/createAccount")
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountCreateDto accountCreateDto, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationServiceWebClient(result);
        if (errorMap != null)
            return errorMap;

            Accounts account = accountService.createAccount(accountCreateDto);
            AccountClientResponseDto responseDto = new AccountClientResponseDto();
            responseDto.setAccount(account);
            responseDto.setStatus("SUCCESS");
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

    }

    @DeleteMapping("/deleteAccount")
    public ResponseEntity<?> deleteAccountByUserId(@RequestParam String userId){
        Accounts account = accountService.findAccountByUserId(userId);
        return new ResponseEntity<>(accountService.deleteAccountByUserId(account), HttpStatus.OK);
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

            Accounts account = accountService.findAccountByAccountId(accountId);
            AccountClientResponseDto responseDto = new AccountClientResponseDto();
            responseDto.setAccount(account);
            responseDto.setStatus("SUCCESS");
            return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    @GetMapping("/webclient/getAccount")
    public Mono<ResponseEntity<AccountClientResponseDto>> findAccountByAccountIdWebClient(@RequestParam String accountId){

            return accountService.findAccountByAccountIdWebClient(Mono.just(accountId))
                    .map(ent -> EntityDtoUtil.toDto(ent)).map(ResponseEntity::ok)
                    .defaultIfEmpty(ResponseEntity.notFound().build());
//            return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }


    @PatchMapping("/updateBalance")
    public ResponseEntity<?> updateAccountBalance(@RequestBody AccountBalanceUpdateDto accountBalanceUpdateDto, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null)
            return errorMap;

            Accounts account = accountService.findAccountByAccountId(accountBalanceUpdateDto.getAccountId());
            int acc = accountService.updateAccountBalance(account, accountBalanceUpdateDto.getBalance());
            AccountClientResponseDto accountClientResponseDto = new AccountClientResponseDto();
            accountClientResponseDto.setAccount(account);
            accountClientResponseDto.setStatus("SUCCESS");
            return new ResponseEntity<>(accountClientResponseDto, HttpStatus.OK);

    }


    @PatchMapping("/webclient/updateBalance")
    public Mono<ResponseEntity<AccountClientResponseDto>> updateAccountBalanceWebClient(@Valid @RequestBody AccountBalanceUpdateDto accountBalanceUpdateDto, BindingResult result){
        ResponseEntity<AccountClientResponseDto> errorMap = mapValidationErrorService.MapValidationServiceWebClient(result);
        if (errorMap != null)
            return Mono.just(errorMap);


            return accountService.updateAccountBalanceWebclient(accountService
                                    .findAccountByAccountIdWebClient(Mono.just(accountBalanceUpdateDto.getAccountId())),
                            accountBalanceUpdateDto.getBalance()).map(ResponseEntity::ok)
                    .defaultIfEmpty(ResponseEntity.notFound().build());


        //        try {
//        }catch (Exception e){
//            AccountClientResponseDto responseDto = new AccountClientResponseDto();
//            responseDto.setStatus("ERROR");
//            responseDto.setCustomExceptionResponse(new CustomExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
//            return Mono.just(ResponseEntity.ok(responseDto));
//        }

    }

}
