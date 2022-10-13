package com.tuum.bank.transactionservice.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ashbel Reinhard
 *
 */
public class CustomExceptionResponse {

	private int errorCode;
	private String message;

	@JsonCreator
	public CustomExceptionResponse(@JsonProperty("message") String message) {
		this.message = message;
	}

	@JsonCreator
	public CustomExceptionResponse(@JsonProperty("errorCode") int errorCode,@JsonProperty("message") String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
