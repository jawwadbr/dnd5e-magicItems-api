package com.jawbr.exception.handler;

import com.jawbr.exception.IntegrityConstraintViolationException;
import com.jawbr.exception.MagicItemNotFoundException;
import com.jawbr.exception.errorResponse.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MagicItemExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(MagicItemNotFoundException exc) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
