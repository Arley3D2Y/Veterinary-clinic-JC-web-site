package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Tratamiento {

    // Atributos

    @Id
    @GeneratedValue
    private Long id;

    private String descripcion;

    private String observaciones;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @ManyToMany
    @JoinTable(name = "tratamiento_droga", joinColumns = @JoinColumn(name = "tratamiento_id"), inverseJoinColumns = @JoinColumn(name = "droga_id"))
    private List<Droga> drogas = new ArrayList<>();

    @ManyToMany(mappedBy = "tratamientos")
    private List<Veterinario> veterianarios = new ArrayList<Veterinario>();

    @ManyToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    // Constructores

    public Tratamiento() {
    }

    public Tratamiento(String descripcion, String observaciones, LocalDate fechaInicio) {
        this.descripcion = descripcion;
        this.observaciones = observaciones;
        this.fechaInicio = fechaInicio;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Veterinario> getVeterianarios() {
        return veterianarios;
    }

    public void setVeterianarios(List<Veterinario> veterianarios) {
        this.veterianarios = veterianarios;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public List<Droga> getDrogas() {
        return drogas;
    }

    public void setDrogas(List<Droga> drogas) {
        this.drogas = drogas;
    }

}
