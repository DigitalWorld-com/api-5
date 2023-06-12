package com.digitalworld.api5.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Data
@Getter
@Setter
public class GeolocationModel {
    private String name;
    private List<String> local_names;
    private int lat;
    private int lon;
    private String country;
    private String state;
}
