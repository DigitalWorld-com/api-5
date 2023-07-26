package com.digitalworld.api5.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConversionHistoryRequest {
    private String coinName;
    private LocalDateTime dateCreatedFrom;
    private LocalDateTime dateCreatedTo;
    private LocalDateTime dateCheckedFrom;
    private LocalDateTime dateCheckedTo;
}
