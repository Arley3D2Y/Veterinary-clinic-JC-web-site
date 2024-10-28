package com.example.demo.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Tratamiento {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String descripcion;
    private String observaciones;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean activo;

    @JsonIgnore
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

    public Tratamiento() {  }

    public Tratamiento(String descripcion, String observaciones, LocalDate fechaFin) {
        this.descripcion = descripcion;
        this.observaciones = observaciones;
        this.fechaInicio = LocalDate.now();
        this.fechaFin = fechaFin;
        this.activo = true;
    }

    // Método para determinar si el tratamiento está activo
    public void actualizarEstado() {
        LocalDate fechaActual = LocalDate.now();
        if (fechaFin == null || fechaFin.isAfter(fechaActual) || fechaFin.equals(fechaActual)) {
            this.activo = true;  // Tratamiento activo si la fecha fin es futura, actual o no está definida
        } else {
            this.activo = false; // Tratamiento inactivo si la fecha fin es pasada
        }
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Droga getDroga() {
        return droga;
    }

    public void setDroga(Droga droga) {
        this.droga = droga;
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

}
