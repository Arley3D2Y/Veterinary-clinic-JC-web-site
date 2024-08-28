package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Administrador {
    
    //Atributos

    @Id
    @GeneratedValue
    private Long id;

    private String usuario;

    private String password;

    // cosntructores

    public Administrador(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public Administrador() {}

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
