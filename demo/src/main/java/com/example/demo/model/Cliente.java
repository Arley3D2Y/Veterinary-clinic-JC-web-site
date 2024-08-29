package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.util.List;

import java.util.ArrayList;

@Entity
@Table(name = "cliente") // Corregido el nombre de la tabla
public class Cliente {
    // atributos

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NAME")
    private String nombre;

    private String cedula;

    private String correo;

    private String celular;

    @Column(length = 2048)  // Ajusta el tamaño según sea necesario
    private String fotoString;

    @OneToMany(mappedBy = "cliente")
    private List<Mascota> mascotas = new ArrayList<>();;

    public Cliente(String nombre, String cedula, String correo, String celular, String fotoString) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
        this.celular = celular;
        this.fotoString = fotoString;
    }

    public Cliente() {
    }

    // getters and setters
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
    
    
    public String getFotoString() {
        return fotoString;
    }

    public void setFotoString(String fotoString) {
        this.fotoString = fotoString;
    }


    // métodos
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
