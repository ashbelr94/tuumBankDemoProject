package com.tuum.bank.accountservice.exception;

import com.tuum.bank.accountservice.Dto.AccountClientResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    public final ResponseEntity<Object> handleUserException(CustomException ex,
                                                            WebRequest request) {
        CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(ex.getMessage());
        exceptionResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<AccountClientResponseDto> handleException(CustomException ex){

        AccountClientResponseDto responseDto = new AccountClientResponseDto();
        responseDto.setStatus("ERROR");
        responseDto.setCustomExceptionResponse(new CustomExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
        return ResponseEntity.ok().body(responseDto);
    }
}
