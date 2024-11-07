package com.example.demo.servicio;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.EstadoMascotasDTO;
import com.example.demo.DTO.EstadoVeterinariosDTO;
import com.example.demo.DTO.TratamientoDrogaDTO;
import com.example.demo.model.EstadoSalud;
import com.example.demo.repositorio.DrogaRepository;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.repositorio.VeterinarioRepository;

@Service
public class DashboardServiceImp implements DashboardService {

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private DrogaRepository drogaRepository;

    // Método para contar tratamientos del mes actual
    @Override
    public Integer countTreatmentsLastMonth() {
        LocalDate fechaActual = LocalDate.now();
        // Primer día del mes actual
        LocalDate primerDiaDelMes = LocalDate.of(fechaActual.getYear(), fechaActual.getMonth(), 1);

        // Contar tratamientos del mes actual hasta la fecha actual
        Integer count = tratamientoRepository.countTreatmentsCurrentMonth(primerDiaDelMes, fechaActual);
        return count;
    }

    // Método para contar tratamientos por medicamento en el mes actual
    @Override
    public List<TratamientoDrogaDTO> countTreatmentsByDrug() {
        LocalDate fechaActual = LocalDate.now();
        LocalDate primerDiaDelMes = LocalDate.of(fechaActual.getYear(), fechaActual.getMonth(), 1);

        List<Object[]> resultados = tratamientoRepository.countTreatmentsByMedication(primerDiaDelMes, fechaActual);
        List<TratamientoDrogaDTO> tratamientosPorDrogaDTO = new ArrayList<>();

        for (Object[] resultado : resultados) {
            String nombreDroga = (String) resultado[0];
            Integer cantidad = ((Number) resultado[1]).intValue();
            tratamientosPorDrogaDTO.add(new TratamientoDrogaDTO(nombreDroga, cantidad));
        }

        return tratamientosPorDrogaDTO;
    }

    @Override
    public List<EstadoVeterinariosDTO> countStatesVeterinarians() {
        List<Object[]> resultados = veterinarioRepository.countVeterinariansByStatus();
        List<EstadoVeterinariosDTO> estadosVeterinariosDTO = new ArrayList<>();

        for (Object[] resultado : resultados) {
            String estado = (String) resultado[0]; // Esto ahora funcionará
            Integer cantidad = ((Integer) resultado[1]).intValue(); // Cambia a Long si prefieres
            estadosVeterinariosDTO.add(new EstadoVeterinariosDTO(estado, cantidad));
        }

        return estadosVeterinariosDTO;
    }

    @Override
    public Integer countTotalPets() {
        return mascotaRepository.countTotalPets();
    }

    @Override
    public List<EstadoMascotasDTO> countPetsByEstado() {
        List<EstadoMascotasDTO> estadoMascotaDTOs = new ArrayList<>();

        // Contar las mascotas por estado
        for (EstadoSalud estado : EstadoSalud.values()) {
            Integer count = mascotaRepository.countPetsByEstado(estado);
            estadoMascotaDTOs.add(new EstadoMascotasDTO(estado.name(), count));
        }

        return estadoMascotaDTOs;
    }

    @Override
    public Double calculateTotalSales() {
        // Llamar al método del repositorio para obtener el total de ventas
        return tratamientoRepository.calculateTotalSales();
    }

    @Override
    public Double calculateTotalProfits() {
        // Llamar al método del repositorio para obtener el total de ganancias
        return drogaRepository.calculateTotalProfits();
    }

}