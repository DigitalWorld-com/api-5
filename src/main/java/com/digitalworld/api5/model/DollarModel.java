package com.digitalworld.api5.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class DollarModel {
    private float compra;
    private float venta;
    private String nombre;
    private String casa;
    private LocalDateTime fechaActualizacion;
}
