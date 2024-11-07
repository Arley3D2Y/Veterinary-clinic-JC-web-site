package com.example.demo.repositorio;

import com.example.demo.model.Enfermedad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface EnfermedadRepository extends JpaRepository<Enfermedad, Long> {

    // findAll()

    // findById()

    // save()

    // deleteById()

    // update whit save()

    // Busqueda por nombre
    List<Enfermedad> findByNombreStartingWithIgnoreCase(String nombre);


    /** Query **/

    // Contar todas las enfermedades
    @Query("SELECT COUNT(e) FROM Enfermedad e")
    Integer countTotalDiseases();

}
