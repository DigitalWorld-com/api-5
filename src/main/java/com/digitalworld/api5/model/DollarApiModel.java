package com.digitalworld.api5.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DollarApiModel {

    private float compra;
    private float venta;
    private String tipo;
    private String nombre;
    private String casa;
    private LocalDateTime fechaActualizacion;
}
