package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Veterinario;
import java.util.List;
import java.util.Optional;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {

    // findAll()

    // findById()

    // save()

    // deleteById()

    // update whit save()

    // Método para buscar un veterinario por nombre
    Optional<Veterinario> findByNombreIgnoreCase(String nombre);
    
    // Método para buscar veterinarios por nombre inicial
    List<Veterinario> findByNombreStartingWithIgnoreCase(String nombre);

    // Método para buscar veterinario por cédula
    Optional<Veterinario> findByCedula(String cedula);

    // Método para buscar veterinario por correo
    Optional<Veterinario> findByCorreo(String correo);


    /** Querys **/

    // Contar todos los veterinarios
    @Query("SELECT CASE WHEN v.estado THEN 'Activo' ELSE 'Inactivo' END AS estado, CAST(COUNT(v) AS int) " +
    "FROM Veterinario v GROUP BY v.estado")
    List<Object[]> countVeterinariansByStatus();

    
}
