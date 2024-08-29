package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Tratamiento_Droga {

    // Atributos

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tratamiento_id")
    private Tratamiento tratamiento;

    @ManyToOne
    @JoinColumn(name = "droga_id")
    private Droga droga;

    private Integer unidadesVendidas;

    // Constructores
    public Tratamiento_Droga() {
    }

    public Tratamiento_Droga(Tratamiento tratamiento, Droga droga, Integer unidadesVendidas) {
        this.tratamiento = tratamiento;
        this.droga = droga;
        this.unidadesVendidas = unidadesVendidas;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Droga getDroga() {
        return droga;
    }

    public void setDroga(Droga droga) {
        this.droga = droga;
    }

    public Integer getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public void setUnidadesVendidas(Integer unidadesVendidas) {
        this.unidadesVendidas = unidadesVendidas;
    }
}
