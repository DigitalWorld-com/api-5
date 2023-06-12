package com.digitalworld.api5.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class WeatherRequest {
    private String cityName;
    private String countryCode;
}
