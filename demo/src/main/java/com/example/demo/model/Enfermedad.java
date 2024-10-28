package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Enfermedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String caracteristicas;
    private String medicacion;

    public Enfermedad(String nombre, String caracteristicas, String medicacion) {
        this.nombre = nombre;
        this.caracteristicas = caracteristicas;
        this.medicacion = medicacion;
    }

    public Enfermedad() {
    }

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

    public String getmedicacion() {
        return medicacion;
    }

    public void setmedicacion(String medicacion) {
        this.medicacion = medicacion;
    }

    
}
