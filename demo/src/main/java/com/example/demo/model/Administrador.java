package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Administrador {
    @Id
    @GeneratedValue
    private Long id;
    private String usuario;
    private String password;

    public Administrador(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

}