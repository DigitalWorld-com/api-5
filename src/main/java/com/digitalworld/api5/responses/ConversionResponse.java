package com.digitalworld.api5.responses;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ConversionResponse {
    private float officialBuyPrice;
    private float officialSellPrice;
    private float blueBuyPrice;
    private float blueSellPrice;
}
