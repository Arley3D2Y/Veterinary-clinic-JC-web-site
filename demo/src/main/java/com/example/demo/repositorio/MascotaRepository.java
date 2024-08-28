package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Mascota;
import java.util.Optional;
import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    // JpaRepository ya tiene findById() y findAll()

    // Método para buscar una mascota por id
    Optional<Mascota> findById(Long id);

    // Método para obtener la información de todas las mascotas
    List<Mascota> findAll();

    // JpaRepository ya tiene métodos para guardar (insertar/actualizar) y eliminar

}