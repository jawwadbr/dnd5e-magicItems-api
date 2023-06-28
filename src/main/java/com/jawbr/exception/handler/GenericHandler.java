package com.jawbr.exception.handler;

import com.jawbr.exception.IntegrityConstraintViolationException;
import com.jawbr.exception.RarityEnumInvalidException;
import com.jawbr.exception.errorResponse.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

@ControllerAdvice
public class GenericHandler {

    @ExceptionHandler(IntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleException(IntegrityConstraintViolationException exc) {
        ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(), exc.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException exc) {
        BindingResult result = exc.getBindingResult();
        String message = Objects.requireNonNull(result.getFieldError()).getDefaultMessage();
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message, System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RarityEnumInvalidException.class)
    public ResponseEntity<ErrorResponse> handleException(RarityEnumInvalidException exc) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
