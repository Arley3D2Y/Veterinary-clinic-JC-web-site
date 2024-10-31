package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.servicio.DashboardService;

import com.example.demo.DTO.TratamientoDrogaDTO;
import com.example.demo.DTO.EstadoMascotasDTO;
import com.example.demo.DTO.EstadoVeterinariosDTO;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    /** Dashboard Endpoints **/

    // localhost:8088/dashboard/total-treatments/last-month
    @GetMapping("/total-treatments/last-month")
    @Operation(summary = "Get total treatments by month")
    public ResponseEntity<Integer> getTotalTreatmentsLastMonth() {
        Integer totalTreatments = dashboardService.countTreatmentsLastMonth();

        return ResponseEntity.ok(totalTreatments);
    }

    // localhost:8088/dashboard/treatments-by-medication/last-month
    @GetMapping("/treatments-by-medication/last-month")
    @Operation(summary = "Get treatments by medication by last month")
    public ResponseEntity<List<TratamientoDrogaDTO>> getTreatmentsByMedicationLastMonth() {
        List<TratamientoDrogaDTO> estadisticas = dashboardService.countTreatmentsByDrug();

        return ResponseEntity.ok(estadisticas);
    }

    // localhost:8088/dashboard/count-veterinarians/states
    @GetMapping("/count-veterinarians/states")
    @Operation(summary = "Get veterinaries by month")
    public ResponseEntity<List<EstadoVeterinariosDTO>> getVeterinariesLastMonth() {
        List<EstadoVeterinariosDTO> estadosVeterinarios = dashboardService.countStatesVeterinarians();

        return ResponseEntity.ok(estadosVeterinarios);
    }

    // localhost:8088/dashboard/total-pets
    @GetMapping("/total-pets/count")
    @Operation(summary = "Get total number of pets count")
    public ResponseEntity<Integer> getTotalPetsCount() {
        Integer countPets = dashboardService.countTotalPets();

        return ResponseEntity.ok(countPets);
    }


    // localhost:8088/dashboard/total-pets/count-active
    @GetMapping("/total-pets/count-states")
    @Operation(summary = "Get total number of active pets count")
    public ResponseEntity<List<EstadoMascotasDTO>> getActivePetsCount() {
        List<EstadoMascotasDTO> countPets = dashboardService.countPetsByEstado();

        return ResponseEntity.ok(countPets);
    }

    // localhost:8088/dashboard/total-sales
    @GetMapping("/total-sales")
    @Operation(summary = "Get total sales")
    public ResponseEntity<Double> getTotalSales() {
        Double totalSales = dashboardService.calculateTotalSales();

        return ResponseEntity.ok(totalSales);
    }

    // localhost:8088/dashboard/total-sales
    @GetMapping("/total-profits")
    @Operation(summary = "Get total profits")
    public ResponseEntity<Double> getTotalProfits() {
        Double totalProfits  = dashboardService.calculateTotalProfits();

        return ResponseEntity.ok(totalProfits);
    }

}
