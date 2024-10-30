package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "mascota") // Corregido el nombre de la tabla
public class Mascota {

    @Id
    @GeneratedValue
    private Long id; // Cambiado de Integer a Long para coincidir con Cliente
    private String nombre;
    private String edad;
    private String raza;
    private String peso;
    private String sexo;

    @Column(length = 2048) // Ajusta el tamaño según sea necesario
    private String fotoString;

    @ManyToOne
    private Enfermedad enfermedad;

    @ManyToOne
    private Estado estado;

    @JsonIgnore
    @OneToMany(mappedBy = "mascota", cascade = CascadeType.DETACH, orphanRemoval = false)
    private List<Tratamiento> tratamientos = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente; // Cambiado de duenho a cliente

    public Mascota() {
    }

    public Mascota(String nombre, String edad, String peso, String raza, String sexo, String fotoString) {
        this.nombre = nombre;
        this.edad = edad;
        this.raza = raza;
        this.peso = peso;
        this.sexo = sexo;
        this.fotoString = fotoString;
        this.enfermedad = null;
        this.estado = null;
        this.tratamientos = new ArrayList<>();
    }

    // Métodos funcionales de listas de tratamientos

    public boolean agregarTratamiento(Tratamiento tratamiento, Estado estado) {
        if (!this.tratamientos.contains(tratamiento) && this.estado.getDescripcion().equals("Enfermo") || estado.getDescripcion().equals("Medicado")) {
            tratamiento.setMascota(this);
            this.estado = estado;
            this.tratamientos.add(tratamiento);
            return true;
        }
        return false;
    }

    public boolean eliminarTratamiento(Tratamiento tratamiento) {
        if (this.tratamientos.contains(tratamiento)) {
            this.tratamientos.remove(tratamiento);
            return true;
        }
        return false;
    }
    
    // Getter and Setter
    
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

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFotoString() {
        return fotoString;
    }

    public void setFotoString(String fotoString) {
        this.fotoString = fotoString;
    }

    public Enfermedad getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(Enfermedad enfermedad) {
        this.enfermedad = enfermedad;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Tratamiento> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
