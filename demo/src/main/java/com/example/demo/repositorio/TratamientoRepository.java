package com.example.demo.repositorio;

import java.time.LocalDate;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Mascota;
import com.example.demo.model.Tratamiento;
import com.example.demo.model.Veterinario;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {

    // JpaRepository ya tiene findAll(), findById(), deleteById(), y save()

    List<Tratamiento> findByDescripcionStartingWithIgnoreCase(String nombre);

    List<Tratamiento> findByVeterinario(Veterinario veterinario);
    
    // Buscar por fecha
    List<Tratamiento> findByFechaInicio(LocalDate fechaInicio);

    // Buscar por fecha
    List<Tratamiento> findByFechaFin(LocalDate fechaFin);

    // Buscar por mascota
    List<Tratamiento> findByMascota(Mascota mascota);

}
