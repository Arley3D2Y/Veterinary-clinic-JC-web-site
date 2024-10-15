package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Mascota;


@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    // JpaRepository ya tiene findAll(), findById(), deleteById(), y save()

    // Busqueda de mascotas por nombre
    List<Mascota> findByNombreContainingIgnoreCase(String nombre);

    // Busqueda de mascotas por el id del dueño
    List<Mascota> findByClienteId(Long id);

}