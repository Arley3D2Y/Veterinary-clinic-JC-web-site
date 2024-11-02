package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Droga;
import java.util.Optional;
import java.util.List;

@Repository
public interface DrogaRepository extends JpaRepository<Droga, Long> {

    // findAll()

    // findById()

    // save()

    // deleteById()

    // update whit save()

    // Buscar droga por nombre
    Optional<Droga> findByNombreIgnoreCase(String nombre);

    // Buscar drogas por nombre inicial
    List<Droga> findByNombreStartingWithIgnoreCase(String nombre);

    // Buscar drogas de un tratamiento Id
    List<Droga> findByTratamientoId(Long id);

    /** Query **/

    // Contar todas las drogas
    @Query("SELECT COUNT(d) FROM Droga d")
    Integer countTotalDrugs();

    // Obtener el total de ganancias
    @Query("SELECT SUM((d.precioVenta - d.precioCompra) * d.unidadesVendidas) FROM Droga d WHERE d.unidadesVendidas > 0")
    Double calculateTotalProfits();

    // Obtener el total de unidades vendidas
    @Query("SELECT SUM(d.unidadesVendidas) FROM Droga d WHERE d.unidadesVendidas IS NOT NULL")
    Integer calculateTotalUnitsSold();

}
