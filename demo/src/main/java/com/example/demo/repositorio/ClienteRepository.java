package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cliente;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    // JpaRepository ya tiene findAll(), findById(), deleteById(), y save()

    // Método para buscar cliente por cédula
    Cliente findByCedula(String cedula);

    List<Cliente> findByNombreContainingIgnoreCase(String nombre);

}
