package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Mascota;


@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    // JpaRepository ya tiene findById() y findAll()

}