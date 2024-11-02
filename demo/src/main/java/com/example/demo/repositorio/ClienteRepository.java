package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cliente;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // findAll()

    // findById()

    // save()

    // deleteById()

    // update whit save()
    
    // Buscar por cedula
    Optional<Cliente> findByCedula(String cedula);

    // Buscar por nombre iniciales
    List<Cliente> findByNombreStartingWithIgnoreCase(String nombre);


    /** Querys **/
    
    // Contar todos los clientes
    @Query("SELECT COUNT(c) FROM Cliente c")
    Integer countTotalClients();


}
