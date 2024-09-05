package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Mascota;


@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    // JpaRepository ya tiene findById() y findAll()
    List<Mascota> findByNombreContainingIgnoreCase(String nombre);

}