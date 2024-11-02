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

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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
    }

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

}
