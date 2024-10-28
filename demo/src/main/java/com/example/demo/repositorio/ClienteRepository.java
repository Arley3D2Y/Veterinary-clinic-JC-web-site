package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cliente;
import com.example.demo.model.Estado;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // findAll()

    // findById()

    // save()

    // deleteById()

    // update whit save()
    
    Optional<Cliente> findByCedula(String cedula);

    List<Cliente> findByNombreStartingWithIgnoreCase(String nombre);

    List<Estado> findByNombreContainingIgnoreCase(String descripcion);

}
