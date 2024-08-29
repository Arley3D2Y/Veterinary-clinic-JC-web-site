package com.example.demo.model;

import java.util.ArrayList;

import jakarta.persistence.Entity;

@Entity
public class Cliente {
    // atributos
    private Long id;
    private String nombre;
    private String cedula;
    private String correo;
    private String celular;
    private ArrayList<Mascota> mascotas;

    // constructor
    public Cliente(String nombre, String cedula, String correo, String celular) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
        this.celular = celular;
        this.mascotas = new ArrayList<>();
    }

    public Cliente() {
        this.mascotas = new ArrayList<>();
    }

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    // metodos
    public void agregarMascota(Mascota mascota) {
        if (this.mascotas == null) {
            this.mascotas = new ArrayList<>();
        }
        if (!this.mascotas.contains(mascota)) {
            this.mascotas.add(mascota);
        }
    }
    

    public void eliminarMascota(Mascota mascota) {
        this.mascotas.remove(mascota);
    }

    public void modificarMascota(Mascota mascota) {
        this.mascotas.remove(mascota);
        this.mascotas.add(mascota);
    }

}