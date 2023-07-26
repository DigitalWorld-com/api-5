package com.digitalworld.api5.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConversionRequest {
    private String coinName;
    private float coinPrice;
    private float officialBuyPrice;
    private float officialSellPrice;
    private float blueBuyPrice;
    private float blueSellPrice;
}
