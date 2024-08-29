package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Droga {

    // Atributos

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private Float precioCompra;

    private Float precioVenta;

    private Integer unidadesDisponibles;

    private Integer unidadesVendidas;

    @OneToMany(mappedBy = "droga")
    private List<Tratamiento_Droga> tratamientoDrogas = new ArrayList<Tratamiento_Droga>();


    // Constructores

    public Droga() {
    }

    public Droga(String nombre, Float precioCompra, Float precioVenta, Integer unidadesDisponibles,
            Integer unidadesVendidas) {
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.unidadesDisponibles = unidadesDisponibles;
        this.unidadesVendidas = unidadesVendidas;
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

    public Float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(Integer unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public Integer getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public void setUnidadesVendidas(Integer unidadesVendidas) {
        this.unidadesVendidas = unidadesVendidas;
    }

    public List<Tratamiento_Droga> getTratamientoDrogas() {
        return tratamientoDrogas;
    }

    public void setTratamientoDrogas(List<Tratamiento_Droga> tratamientoDrogas) {
        this.tratamientoDrogas = tratamientoDrogas;
    }

    
}
