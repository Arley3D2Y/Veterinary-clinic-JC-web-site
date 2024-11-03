package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EstadoSalud;
import com.example.demo.model.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    // findAll()

    // findById()

    // save()

    // deleteById()

    // update whit save()

    // Buscar por nombre
    List<Mascota> findByNombreStartingWithIgnoreCase(String nombre);

    // Busqueda de mascotas por el id del dueño
    List<Mascota> findByClienteId(Long id);

    /** Querys **/

    // Contar todas las mascotas
    @Query("SELECT COUNT(m) FROM Mascota m")
    Integer countTotalPets();

    // Contar mascotas por estado
    @Query("SELECT COUNT(m) FROM Mascota m WHERE m.estado = :estado")
    Integer countPetsByEstado(@Param("estado") EstadoSalud estado);
}