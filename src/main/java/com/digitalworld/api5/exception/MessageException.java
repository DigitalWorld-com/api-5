package com.digitalworld.api5.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MessageException extends RuntimeException{
    private final String message;
}
