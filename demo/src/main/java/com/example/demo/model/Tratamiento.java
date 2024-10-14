package com.example.demo.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne
    @JoinColumn(name = "droga_id")
    private Droga droga;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    // Constructores

    public Tratamiento() {
    }

    public Tratamiento(String descripcion, String observaciones, LocalDate fechaInicio) {
        this.descripcion = descripcion;
        this.observaciones = observaciones;
        this.fechaInicio = fechaInicio;
        this.fechaFin = LocalDate.of(2020, 1, 1);
        this.mascota = null;
        this.veterinario = null;
        this.droga = null;
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

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Droga getDroga() {
        return droga;
    }

    public void setDroga(Droga droga) {
        this.droga = droga;
    }

}
