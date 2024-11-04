package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Administrador {

    // Security
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;

    @Id
    @GeneratedValue
    private Long id;
    private String usuario;

    @Transient
    private String password;

    public Administrador(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

}