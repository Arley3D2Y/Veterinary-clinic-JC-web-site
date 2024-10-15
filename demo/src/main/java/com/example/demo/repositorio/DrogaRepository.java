package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Droga;
import java.util.Optional;
import java.util.List;

@Repository
public interface DrogaRepository extends JpaRepository<Droga, Long> {
        
    // JpaRepository ya tiene findAll(), findById(), deleteById(), y save()
    Optional<Droga> findByNombreIgnoreCase(String nombre);

    List<Droga> findByNombreContainingIgnoreCase(String nombre);
}
