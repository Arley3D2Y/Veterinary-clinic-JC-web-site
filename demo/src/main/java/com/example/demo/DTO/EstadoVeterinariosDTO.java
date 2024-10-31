package com.example.demo.DTO;

import lombok.Data;

@Data
public class EstadoVeterinariosDTO {
    
    private String estado;
    private Integer cantidadVeterinarios;

    public EstadoVeterinariosDTO(String estado, Integer cantidadVeterinarios) {
        this.estado = estado;
        this.cantidadVeterinarios = cantidadVeterinarios;
    }
}