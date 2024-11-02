package com.example.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
public class Droga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Float precioCompra;
    private Float precioVenta;
    private Integer unidadesDisponibles;
    private Integer unidadesVendidas;

    @JsonIgnore
    @OneToMany(mappedBy = "droga", cascade = CascadeType.DETACH, orphanRemoval = false)
    private List<Tratamiento> tratamientos = new ArrayList<>();

    public Droga(String nombre, Float precioCompra, Float precioVenta, Integer unidadesDisponibles, Integer unidadesVendidas) {
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.unidadesDisponibles = unidadesDisponibles;
        this.unidadesVendidas = unidadesVendidas;
    }

    // Métodos para agregar tratamiento y actualizar unidades
    public boolean agregarTratamiento(Tratamiento tratamiento) {
        if (!this.tratamientos.contains(tratamiento) && this.unidadesDisponibles > 0) {
            tratamiento.setDroga(this);
            this.tratamientos.add(tratamiento);
            this.unidadesDisponibles--; // Disminuir unidades disponibles
            this.unidadesVendidas++; // Aumentar unidades vendidas
            return true; // Agregado exitosamente
        }
        return false; // El tratamiento ya está en la lista
    }

    public boolean eliminarTratamiento(Tratamiento tratamiento) {
        if (this.tratamientos.contains(tratamiento)) {
            this.tratamientos.remove(tratamiento);
            this.unidadesDisponibles++;
            this.unidadesVendidas--;
            return true; // Removido exitosamente
        }
        return false;
    }

}
