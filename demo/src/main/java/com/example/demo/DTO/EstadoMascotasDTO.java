package com.example.demo.DTO;

import lombok.Data;

@Data
public class EstadoMascotasDTO {
    
    private String estado;
    private Integer cantidad;

    public EstadoMascotasDTO(String estado, Integer cantidad) {
        this.estado = estado;
        this.cantidad = cantidad;
    }
}