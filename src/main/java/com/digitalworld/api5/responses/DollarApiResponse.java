package com.digitalworld.api5.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class DollarApiResponse {

    private float compra;
    private float venta;
    private String tipo;
    private String nombre;
    private String casa;
    private LocalDateTime fechaActualizacion;
}
