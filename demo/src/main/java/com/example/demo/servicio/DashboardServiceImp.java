package com.example.demo.servicio;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Droga;
import com.example.demo.repositorio.DrogaRepository;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.TratamientoRepository;

@Service
public class DashboardServiceImp implements DashboardService {

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Autowired
    private DrogaRepository drogaRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    // Método para obtener el total de ventas por medicamento
    public Map<String, Double> obtenerTotalVentasPorMedicamento() {
        List<Map<String, Object>> resultados = tratamientoRepository.findTotalVentasPorMedicamento();
        Map<String, Double> totalVentasPorMedicamento = new HashMap<>();

        for (Map<String, Object> resultado : resultados) {
            String medicamento = (String) resultado.get("medicamento");
            Double totalVentas = ((Number) resultado.get("totalVentas")).doubleValue();
            totalVentasPorMedicamento.put(medicamento, totalVentas);
        }

        return totalVentasPorMedicamento;
    }

    public Integer obtenerTotalMedicamentosSuministrados() {
        return tratamientoRepository.totalMedicamentosSuministrados();
    }

    // @Transactional
    // public boolean incrementarCantidadVendida(Long drogaId) {
    // Droga droga = drogaRepository.findById(drogaId)
    // .orElseThrow(() -> new RuntimeException("Droga no encontrada"));

    // // Usar el método que reduce unidades disponibles y aumenta unidades vendidas
    // boolean actualizado = droga.actualizarUnidadesVentas();

    // if (actualizado) {
    // drogaRepository.save(droga); // Guardar si se pudo actualizar
    // }

    // return actualizado; // Devuelve si se hizo la actualización
    // }

    public Double calcularGanancias() {
        List<Droga> drogas = drogaRepository.findAll();
        double ganancias = 0;

        for (Droga droga : drogas) {
            ganancias += droga.getUnidadesVendidas() * (droga.getPrecioVenta() - droga.getPrecioCompra());
        }

        return ganancias;
    }

    // Método para obtener el total de mascotas activas
    public Long obtenerTotalMascotasActivas() {
        return mascotaRepository.countActiveMascotas();
    }

    // Método para obtener el total de mascotas
    public Long obtenerTotalMascotas() {
        return mascotaRepository.count(); // Método para contar todas las mascotas
    }

}
