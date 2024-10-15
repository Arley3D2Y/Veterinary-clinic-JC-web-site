package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Especialidad {

    // Atributos
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String caracteristicas;

    // Constructores

    public Especialidad(String nombreEsp, String caracteristicas) {
        this.nombre = nombreEsp;
        this.caracteristicas = caracteristicas;
    }

    public Especialidad() { }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
