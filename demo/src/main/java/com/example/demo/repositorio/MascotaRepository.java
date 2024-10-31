package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Estado;
import com.example.demo.model.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    // findAll()

    // findById()

    // save()

    // deleteById()

    // update whit save()

    // Busqueda de mascotas por nombre
    List<Mascota> findByNombreStartingWithIgnoreCase(String nombre);

    List<Mascota> findByNombreContainingIgnoreCase(String nombre);

    // Busqueda de mascotas por estado
    List<Mascota> findByEstado(Estado estado);

    // Busqueda de mascotas por el id del dueño
    List<Mascota> findByClienteId(Long id);

    // Consulta para contar el total de mascotas
    @Query("SELECT COUNT(m) FROM Mascota m")
    Integer countTotalPets();


    @Query("SELECT COUNT(m) FROM Mascota m WHERE m.estado.id = :estadoId")
    Integer countPetsByEstado(@Param("estadoId") Long estadoId);
}