package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Veterinario {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String cedula;
    private String correo;
    private String password;
    private String fotoString;

    @JsonIgnore
    @ManyToMany
    private List<Especialidad> especialidades = new ArrayList<Especialidad>();

    @JsonIgnore
    @OneToMany(mappedBy = "veterinario")
    private List<Tratamiento> tratamientos = new ArrayList<Tratamiento>();

    public Veterinario(String nombre, String cedula, String correo, String password, String fotoString) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
        this.password = password;
        this.fotoString = fotoString;

        this.especialidades = new ArrayList<Especialidad>();
        this.tratamientos = new ArrayList<Tratamiento>();
    }

    public Veterinario() { }

    // Getters y setters

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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFotoString() {
        return fotoString;
    }

    public void setFotoString(String fotoString) {
        this.fotoString = fotoString;
    }

    public List<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public List<Tratamiento> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }

    public void agregarEspecialidad(Especialidad especialidad) {
        if (!this.especialidades.contains(especialidad)) {
            this.especialidades.add(especialidad);
        }
    }

}
