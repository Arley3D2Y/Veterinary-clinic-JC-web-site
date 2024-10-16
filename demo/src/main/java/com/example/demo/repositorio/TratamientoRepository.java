package com.example.demo.repositorio;

import java.time.LocalDate;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Droga;
import com.example.demo.model.Mascota;
import com.example.demo.model.Tratamiento;
import com.example.demo.model.Veterinario;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {

    // Buscar por fecha
    Collection<Tratamiento> findByFechaInicio(LocalDate fechaInicio);

    // Buscar por fecha
    Collection<Tratamiento> findByFechaFin(LocalDate fechaFin);

    // Buscar por mascota
    Collection<Tratamiento> findByMascota(Mascota mascota);

    // Buscar por veterinario
    Collection<Tratamiento> findByVeterianarios(Veterinario veterinario);

    // Buscar por droga
    Collection<Tratamiento> findByDrogas(Droga droga);
}
