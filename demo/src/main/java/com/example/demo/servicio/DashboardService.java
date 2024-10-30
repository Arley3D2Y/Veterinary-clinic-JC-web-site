package com.example.demo.servicio;

import java.util.*;

public interface DashboardService {

    public Map<String, Double> obtenerTotalVentasPorMedicamento();

    // Método para obtener el total de medicamentos suministrados
    public Integer obtenerTotalMedicamentosSuministrados();

    // Método para calcular las ganancias generadas por ventas de tratamientos
    public Double calcularGanancias();

    // public boolean incrementarCantidadVendida(Long drogaId);

    // Método para obtener el total de mascotas
    public Long obtenerTotalMascotas();
}
