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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Veterinario {
    
    // Security
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cedula;

    @Transient
    private String password;

    private String nombre;
    private String correo;
    private String fotoString;
    private Integer cantidadAtenciones;
    private Boolean estado;

    @ManyToOne
    Especialidad especialidad;

    @JsonIgnore
    @OneToMany(mappedBy = "veterinario", cascade = CascadeType.DETACH, orphanRemoval = false)
    private List<Tratamiento> tratamientos = new ArrayList<Tratamiento>();

    public Veterinario(String nombre, String cedula,  String correo, String password, String fotoString, Boolean estado) {
        this.cedula = cedula;
        this.password = password;
        this.nombre = nombre;
        this.correo = correo;
        this.fotoString = fotoString;
        this.cantidadAtenciones = 0;
        this.estado = estado;
        this.especialidad = null;
    }

    public boolean isTreatmentAddable(Tratamiento tratamiento) {
        return (this.estado && this.especialidad != null) ? true : false;
    }

    public void agregarTratamiento(Tratamiento tratamiento) {
        if (!this.tratamientos.contains(tratamiento)) {
            tratamiento.setVeterinario(this);
            this.cantidadAtenciones++;
            this.tratamientos.add(tratamiento);
        }
    }

    public boolean eliminarTratamiento(Tratamiento tratamiento) {
        if (this.tratamientos.contains(tratamiento)) {
            this.cantidadAtenciones--;
            this.tratamientos.remove(tratamiento);
            return true;
        }
        return false;
    }
}
