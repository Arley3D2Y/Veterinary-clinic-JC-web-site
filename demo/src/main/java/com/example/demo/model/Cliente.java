package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String cedula;
    private String correo;
    private String celular;
    private String direccion;

    @Column(length = 2048) // Ajusta el tamaño según sea necesario
    private String fotoString;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mascota> mascotas = new ArrayList<>();

    public Cliente(String nombre, String cedula, String correo, String celular, String direccion, String fotoString) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
        this.celular = celular;
        this.direccion = direccion;
        this.fotoString = fotoString;
        this.mascotas = new ArrayList<>();
    }

    public Cliente() {
        
    }

    // métodos
    public void guardarMascota(Mascota mascota) {
        if (!this.mascotas.contains(mascota)) {
            mascota.setCliente(this);
            this.mascotas.add(mascota);
        }
    }

    public void eliminarMascota(Mascota mascota) {
        if (this.mascotas.contains(mascota)) {
            this.mascotas.remove(mascota);
        }
    }

    public void actualizarMascota(Long id, Mascota mascotaActualizada) {
        for (int i = 0; i < mascotas.size(); i++) {
            Mascota mascota = mascotas.get(i);
            if (mascota.getId().equals(id)) { // Asumiendo que Mascota tiene un método getId()
                mascotas.set(i, mascotaActualizada);
                mascotaActualizada.setCliente(this); // Asegúrate de establecer la relacion inversa
                return;
            }
        }
    }

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFotoString() {
        return fotoString;
    }

    public void setFotoString(String fotoString) {
        this.fotoString = fotoString;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }


    // Getters y setters
        


}