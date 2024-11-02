package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Especialidad;

import java.util.Optional;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {

    // findAll()

    // findById()

    // save()

    // deleteById()

    // update whit save()

    // Buscar por nombre
    Optional<Especialidad> findByNombreIgnoreCase(String nombre);
    // O se puede hacer una lista que devuelva todos los nombres usando:
    // List<Especialidad> findByNombreStartingWithIgnoreCase(String nombre);

    
    /** Query **/

    // Contar todas las especialidades
    @Query("SELECT COUNT(e) FROM Especialidad e")
    Integer countTotalEspecialidades();
    
}
