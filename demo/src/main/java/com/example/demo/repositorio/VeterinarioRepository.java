package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Veterinario;
import java.util.List;
import java.util.Optional;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {

    // findAll()

    // findById()

    // save()

    // deleteById()

    // update whit save()

    // Método para buscar veterinario por cédula
    Optional<Veterinario> findByCedula(String cedula);

    // Método para buscar veterinario por correo
    Optional<Veterinario> findByCorreo(String correo);


    // Método para buscar un veterinario por nombre
    Optional<Veterinario> findByNombreIgnoreCase(String nombre);

    List<Veterinario> findByNombreStartingWithIgnoreCase(String nombre);

    List<Veterinario> findByNombreContainingIgnoreCase(String nombre);


    
    // Cosas a eliminar

    // Contar veterinarios activos
    long countByEstadoTrue();

    // Contar veterinarios inactivos
    long countByEstadoFalse();

    // Buscar por tratamientos
    List<Veterinario> findByTratamientos_Id(Long tratamientoId);

}
