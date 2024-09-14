package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Veterinario;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    
    // JpaRepository ya tiene findAll(), findById(), deleteById(), y save()

    // Método para buscar cliente por cédula
    Veterinario findByCedula(String cedula);

    // Método para buscar cliente por correo
    Veterinario findByCorreo(String correo);
}
