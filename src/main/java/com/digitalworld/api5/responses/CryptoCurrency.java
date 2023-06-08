package com.digitalworld.api5.responses;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CryptoCurrency {

    private String name;
    private Currency currency;

    public static class Currency {
        private double usd;

        // Getters and Setters

        public double getUsd() {
            return usd;
        }
    }
}
