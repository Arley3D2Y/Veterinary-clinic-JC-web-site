package com.example.demo.DTO;

import lombok.Data;

@Data
public class VeterinarioDTO {
    private Long id;
    private String cedula;
    private String nombre;
    private String correo;
    private String fotoString;
}
// veterinario -> VeterinarioDTO