package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cedula;
    private String password;
    private String nombre;
    private String correo;
    private String fotoString;
    private Integer cantidadAtenciones;
    private boolean estado;

    @ManyToOne
    Especialidad especialidad;

    @JsonIgnore
    @OneToMany(mappedBy = "veterinario", cascade = CascadeType.DETACH, orphanRemoval = false)
    private List<Tratamiento> tratamientos = new ArrayList<Tratamiento>();


    public Veterinario() { }

    public Veterinario(String nombre, String cedula,  String correo, String password, String fotoString, Boolean estado) {
        this.cedula = cedula;
        this.password = password;
        this.nombre = nombre;
        this.correo = correo;
        this.fotoString = fotoString;
        this.cantidadAtenciones = 0;
        this.estado = estado;
    }

    // Métodos

    public boolean agregarTratamiento(Tratamiento tratamiento) {
        if (!this.tratamientos.contains(tratamiento)) {
            tratamiento.setVeterinario(this);
            this.cantidadAtenciones++;
            this.tratamientos.add(tratamiento);
            return true;
        }
        return false;
    }

    public boolean eliminarTratamiento(Tratamiento tratamiento) {
        if (this.tratamientos.contains(tratamiento) && this.estado) {
            this.cantidadAtenciones--;
            this.tratamientos.remove(tratamiento);
            return true;
        }
        return false;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFotoString() {
        return fotoString;
    }

    public void setFotoString(String fotoString) {
        this.fotoString = fotoString;
    }

    public Number getCantidadAtenciones() {
        return cantidadAtenciones;
    }

    public void setCantidadAtenciones(Integer cantidadAtenciones) {
        this.cantidadAtenciones = cantidadAtenciones;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public List<Tratamiento> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }

}
