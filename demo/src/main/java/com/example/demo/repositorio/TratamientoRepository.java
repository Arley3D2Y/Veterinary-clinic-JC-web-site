package com.example.demo.repositorio;

import java.time.LocalDate;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Tratamiento;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {

    // findAll()

    // findById()

    // save()

    // deleteById()

    // update whit save()

    // Buscar por descripcion
    List<Tratamiento> findByDescripcionStartingWithIgnoreCase(String nombre);

    // Buscar por veterinario
    List<Tratamiento> findByVeterinarioId(Long id);

    // Buscar por mascota
    List<Tratamiento> findByMascotaId(Long id);

    // Buscar por droga
    List<Tratamiento> findByDrogaId(Long id);

    // Buscar por fecha inicio
    List<Tratamiento> findByFechaInicio(LocalDate fechaInicio);

    // Buscar por fecha fin
    List<Tratamiento> findByFechaFin(LocalDate fechaFin);

    // Buscar por fecha inicio y fin
    List<Tratamiento> findByFechaInicioBetween(LocalDate fechaInicio, LocalDate fechaFin);


    /** Querys **/

    // Contar todos los tratamientos
    @Query("SELECT COUNT(t) FROM Tratamiento t")
    Integer countAllTreatments();

    // Contar tratamientos del mes actual desde el 1 hasta la fecha actual
    @Query("SELECT COUNT(t) FROM Tratamiento t WHERE t.fechaInicio BETWEEN :startDate AND :endDate")
    Integer countTreatmentsCurrentMonth(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Obtener tratamientos por droga en el mes actual
    @Query("SELECT t.droga.nombre, COUNT(t) FROM Tratamiento t WHERE t.fechaInicio BETWEEN :startDate AND :endDate GROUP BY t.droga.nombre")
    List<Object[]> countTreatmentsByMedication(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Calcular el total de ventas sumando el precio de cada tratamiento
    @Query("SELECT SUM(d.precioVenta) FROM Tratamiento t JOIN t.droga d")
    Double calculateTotalSales();

}
