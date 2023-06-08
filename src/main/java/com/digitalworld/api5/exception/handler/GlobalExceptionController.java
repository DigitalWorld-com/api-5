package com.digitalworld.api5.exception.handler;


import com.digitalworld.api5.exception.CryptoApiException;
import com.digitalworld.api5.exception.error.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(CryptoApiException.class)
    public ResponseEntity<ErrorDetails> handleCryptoApiException(CryptoApiException exception) {

        ErrorDetails errorDetails = ErrorDetails.builder()
                .detail(exception.getMessage())
                .dateTime(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorDetails);
    }


}
