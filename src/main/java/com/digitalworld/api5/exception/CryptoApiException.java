package com.digitalworld.api5.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CryptoApiException extends RuntimeException{
    private final String message;
}
