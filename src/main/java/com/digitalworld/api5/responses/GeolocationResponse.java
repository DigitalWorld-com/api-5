package com.digitalworld.api5.responses;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class GeolocationResponse {
    private int lat;
    private int lon;
}
