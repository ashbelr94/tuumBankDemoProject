package com.tuum.bank.accountservice.exception;
/**
 * @author Ashbel Reinhard
 *
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionException extends RuntimeException {

	public TransactionException(String message) {
		super(message);
	}
}
