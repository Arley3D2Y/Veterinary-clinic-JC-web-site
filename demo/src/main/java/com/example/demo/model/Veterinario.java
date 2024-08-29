package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Veterinario {

    // Atributos
    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String cedula;

    private String correo;

    private String password;

    private String fotoString;

    @ManyToMany
    private List<Especialidad> especialidades = new ArrayList<Especialidad>();

    @ManyToMany
    private List<Tratamiento> tratamientos = new ArrayList<Tratamiento>();
    // Constructores

    public Veterinario(String nombre, String cedula, String correo, String password, String fotoString) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
        this.password = password;
        this.fotoString = fotoString;
    }

    public Veterinario() {
    }

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
    

}
