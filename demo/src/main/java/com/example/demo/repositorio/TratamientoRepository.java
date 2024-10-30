package com.example.demo.repositorio;

import java.time.LocalDate;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Tratamiento;
import com.example.demo.model.Veterinario;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {

    // findAll()

    // findById()

    // save()

    // deleteById()

    // update whit save()

    // Buscar por descripcion
    List<Tratamiento> findByDescripcionIgnoreCase(String nombre);

    List<Tratamiento> findByDescripcionStartingWithIgnoreCase(String nombre);

    List<Tratamiento> findByDescripcionContainingIgnoreCase(String nombre);

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

    // Buscar tratamientos activos
    List<Tratamiento> findByActivoTrue();

    // Buscar tratamientos inactivos
    List<Tratamiento> findByActivoFalse();

    // ELIMINAR, Se debe verificar antes
    List<Tratamiento> findByVeterinarioAndActivoTrue(Veterinario veterinario);

    // Total de medicamentos suministrados
    @Query("SELECT SUM(t.droga.unidadesVendidas) FROM Tratamiento t")
    Integer totalMedicamentosSuministrados();

    @Query("SELECT t.medicamento.nombre AS medicamento, SUM(t.cantidadVendida) AS totalVentas " +
            "FROM Tratamiento t " +
            "GROUP BY t.medicamento.nombre")
    List<Map<String, Object>> findTotalVentasPorMedicamento();

}
