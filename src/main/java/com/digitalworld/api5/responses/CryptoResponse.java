package com.digitalworld.api5.responses;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CryptoResponse {

    private String name;
    private String currency;
    private double price;
}
