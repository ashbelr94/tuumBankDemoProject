package com.tuum.bank.accountservice.Service;
/**
 * @author Ashbel Reinhard
 *
 */

import com.tuum.bank.accountservice.Dto.AccountClientResponseDto;
import com.tuum.bank.accountservice.exception.BindingResultErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MapValidationErrorService {

	public ResponseEntity<?> MapValidationService(BindingResult result) {

		if (result.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			BindingResultErrorResponse res = new BindingResultErrorResponse();
			res.setErrorMessage(errorMap);
			return new ResponseEntity<BindingResultErrorResponse>(res, HttpStatus.BAD_REQUEST);
		}
		return null;
	}

	public ResponseEntity<AccountClientResponseDto> MapValidationServiceWebClient(BindingResult result) {

		if (result.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			BindingResultErrorResponse res = new BindingResultErrorResponse();
			res.setErrorMessage(errorMap);
			AccountClientResponseDto accountClientResponseDto = new AccountClientResponseDto();
			accountClientResponseDto.setStatus("ERROR");
			accountClientResponseDto.setBindingResultErrorResponse(res);
			return new ResponseEntity<AccountClientResponseDto>(accountClientResponseDto, HttpStatus.BAD_REQUEST);
		}

		return null;
	}
	
	public ResponseEntity<?> MapValidationServiceList(List<BindingResult> result){
		
		for(BindingResult res: result) {
			if (res.hasErrors()) {
				Map<String, String> errorMap = new HashMap<>();
				for (FieldError error : res.getFieldErrors()) {
					errorMap.put(error.getField(), error.getDefaultMessage());
				}
				BindingResultErrorResponse res1 = new BindingResultErrorResponse();
				res1.setErrorMessage(errorMap);
				return new ResponseEntity<BindingResultErrorResponse>(res1, HttpStatus.BAD_REQUEST);
			}
		}
		return null;
	}
}
