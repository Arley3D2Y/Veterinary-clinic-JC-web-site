package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Veterinario;
import java.util.List;
import java.util.Optional;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {

    // JpaRepository ya tiene save(), findAll(), findById(), deleteById()

    // Método para buscar veterinario por cédula
    Optional<Veterinario> findByCedula(String cedula);

    // Método para buscar veterinario por correo
    Optional<Veterinario> findByCorreo(String correo);

    // Método para buscar veterinarios por un nombre común
    List<Veterinario> findByNombreStartingWithIgnoreCase(String nombre);

    // Método para borrar un veterinario por nombre
    Optional<Veterinario> findByNombreIgnoreCase(String nombre);

    // Contar veterinarios activos
    long countByEstadoTrue();

    // Contar veterinarios inactivos
    long countByEstadoFalse();

    // Buscar por tratamientos
    List<Veterinario> findByTratamientos_Id(Long tratamientoId);

}
