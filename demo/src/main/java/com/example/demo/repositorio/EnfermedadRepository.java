package com.example.demo.repositorio;

import com.example.demo.model.Enfermedad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnfermedadRepository extends JpaRepository<Enfermedad, Long> {

    // findAll()

    // findById()

    // save()

    // deleteById()

    // update whit save()

    Optional<Enfermedad> findByNombre(String nombre);

    List<Enfermedad> findByNombreStartingWithIgnoreCase(String nombre);

    List<Enfermedad> findByNombreContainingIgnoreCase(String nombre);

}
