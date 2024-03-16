package com.wayon.infra;

import com.wayon.exceptions.NoBalanceException;
import com.wayon.exceptions.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(TransactionException.class)
    private ResponseEntity<CustomErrorMessage> transactionExceptionHandler(TransactionException exception) {
        CustomErrorMessage response = new CustomErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(NoBalanceException.class)
    private ResponseEntity<CustomErrorMessage> transactionExceptionHandler(NoBalanceException exception) {
        CustomErrorMessage response = new CustomErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
