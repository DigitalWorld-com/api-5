package com.digitalworld.api5.exception.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ErrorDetails {

    private LocalDateTime dateTime;

    private String code;

    private String detail;

}
