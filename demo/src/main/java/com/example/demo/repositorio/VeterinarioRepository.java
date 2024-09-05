package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Veterinario;
import java.util.Optional;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    
    // JpaRepository ya tiene findAll(), findById(), deleteById(), y save()

    // Método para buscar cliente por cédula
    Optional<Veterinario> findByCedula(String cedula);

    // Método para buscar cliente por correo
    Optional<Veterinario> findByCorreo(String correo);
}
