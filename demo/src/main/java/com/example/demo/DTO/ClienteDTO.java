package com.example.demo.DTO;

import lombok.Data;

@Data
public class ClienteDTO {
    private Long id;
    private String nombre;
    private String cedula;
    private String correo;
    private String fotoString;
}
// cliente -> ClienteDTO