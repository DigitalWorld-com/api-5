package com.digitalworld.api5.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "conversion")
public class ConversionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String coinName;
    private double coinPrice;
    private double officialBuyPrice;
    private double officialSellPrice;
    private double blueBuyPrice;
    private double blueSellPrice;
    private LocalDateTime dateCreated;
}
