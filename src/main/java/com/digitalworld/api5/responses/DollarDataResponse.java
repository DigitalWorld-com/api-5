package com.digitalworld.api5.responses;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class DollarDataResponse {
    private String officialName;
    private float officialBuyPrice;
    private float officialSellPrice;
    private LocalDateTime officialUpdated;
    private String blueName;
    private float blueBuyPrice;
    private float blueSellPrice;
    private LocalDateTime blueUpdated;
}
