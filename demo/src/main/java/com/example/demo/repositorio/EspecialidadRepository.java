package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Especialidad;

import java.util.List;
import java.util.Optional;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {

    // findAll()

    // findById()

    // save()

    // deleteById()

    // update whit save()

    Optional<Especialidad> findByNombreIgnoreCase(String nombre);

    List<Especialidad> findByNombreContainingIgnoreCase(String nombre);

    
}
