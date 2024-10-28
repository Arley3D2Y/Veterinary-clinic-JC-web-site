package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Estado;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
    
    // findAll()

    // findById()

    // save()

    // deleteById()

    // update whit save()

    Optional<Estado> findByDescripcionIgnoreCase(String descripcion);

    List<Estado> findByDescripcionStartingWithIgnoreCase(String descripcion);

    List<Estado> findByDescripcionContainingIgnoreCase(String descripcion);
    
}
