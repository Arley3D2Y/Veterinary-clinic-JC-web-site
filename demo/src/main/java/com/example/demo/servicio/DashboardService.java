package com.example.demo.servicio;

import com.example.demo.DTO.EstadoMascotasDTO;
import com.example.demo.DTO.EstadoVeterinariosDTO;
import com.example.demo.DTO.TratamientoDrogaDTO;

import java.util.List;

public interface DashboardService {

    // Método para obtener el total de tratamientos del último mes
    public Integer countTreatmentsLastMonth();

    // Método para obtener el total de tratamientos por droga
    public List<TratamientoDrogaDTO> countTreatmentsByDrug();

    // Método para obtener el total de tratamientos por estado
    public List<EstadoVeterinariosDTO> countStatesVeterinarians();

    // Método para obtener el total de mascotas
    public Integer countTotalPets();

    // Método para obtener el total de mascotas por estado
    public List<EstadoMascotasDTO> countPetsByEstado();

    // Método para obtener el total de ventas
    public Integer calculateTotalSales();

    // Método para obtener el total de ganancias
    public Double calculateTotalProfits();

}
