package com.example.demo.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Tratamiento {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String descripcion;
    private String observaciones;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

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
    public Tratamiento(String descripcion, String observaciones, LocalDate fechaFin) {
        this.descripcion = descripcion;
        this.observaciones = observaciones;
        this.fechaInicio = LocalDate.now();
        this.fechaFin = fechaFin;
    }

}
