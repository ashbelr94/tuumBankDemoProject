package com.tuum.bank.transactionservice.exception;
/**
 * @author Ashbel Reinhard
 *
 */

import java.util.Map;

public class BindingResultErrorResponse {

	    private Map<String, String> errorMessage;

		public Map<String, String> getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(Map<String, String> errorMessage) {
			this.errorMessage = errorMessage;
		}

}
