package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Especialidad {

    // Atributos
    
    @Id
    @GeneratedValue
    private Long id;

    private String nombreEspecialidad;

    @ManyToMany(mappedBy = "especialidades")
    private List<Veterinario> veterinarios = new ArrayList<Veterinario>();

    // Constructores

    public Especialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public Especialidad() {
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public List<Veterinario> getVeterinarios() {
        return veterinarios;
    }

    public void setVeterinarios(List<Veterinario> veterinarios) {
        this.veterinarios = veterinarios;
    }

}
