package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Enfermedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String sintomas;
    private String medicamentos;

    public Enfermedad(String nombre, String sintomas, String medicamentos) {
        this.nombre = nombre;
        this.sintomas = sintomas;
        this.medicamentos = medicamentos;
    }
    
}
