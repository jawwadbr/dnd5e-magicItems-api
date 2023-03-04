package com.jawbr.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MagicItemExceptionHandler {
	
	// Exception if item not found in DB
	@ExceptionHandler
	public ResponseEntity<MagicItemsErrorResponse> handleException(MagicItemNotFoundException exc) {
		
		MagicItemsErrorResponse error = new MagicItemsErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	// Generic for any exception 
	@ExceptionHandler
	public ResponseEntity<MagicItemsErrorResponse> handleException(Exception exc) {
		
		MagicItemsErrorResponse error = new MagicItemsErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(), System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
}