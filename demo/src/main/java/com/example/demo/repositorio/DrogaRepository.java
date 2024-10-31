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

    Optional<Droga> findByNombreIgnoreCase(String nombre);

    List<Droga> findByNombreStartingWithIgnoreCase(String nombre);

    List<Droga> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT SUM((d.precioVenta - d.precioCompra) * d.unidadesVendidas) " +
            "FROM Droga d WHERE d.unidadesVendidas > 0")
    Double calculateTotalProfits();

    @Query("SELECT SUM(d.unidadesVendidas) FROM Droga d WHERE d.unidadesVendidas IS NOT NULL")
    Integer calculateTotalUnitsSold();

}
