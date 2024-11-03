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

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
// @Table(name = "mascota") Corregido el nombre de la tabla
public class Mascota {

    @Id
    @GeneratedValue
    private Long id; // Cambiado de Integer a Long para coincidir con Cliente
    private String nombre;
    private String edad;
    private String raza;
    private String peso;
    private String sexo;
    private EstadoSalud estado;

    @Column(length = 2048) // Ajusta el tamaño según sea necesario
    private String fotoString;

    @ManyToOne
    private Enfermedad enfermedad;

    @JsonIgnore
    @OneToMany(mappedBy = "mascota", cascade = CascadeType.DETACH, orphanRemoval = false)
    private List<Tratamiento> tratamientos = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente; // Cambiado de duenho a cliente

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

    public boolean isTreatmentAddable(Tratamiento tratamiento) {
        return (this.estado.equals(EstadoSalud.ENFERMO)) ? true : false;
    }

    public boolean agregarTratamiento(Tratamiento tratamiento, EstadoSalud estado) {
        if (!this.tratamientos.contains(tratamiento)) {
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
    
}
