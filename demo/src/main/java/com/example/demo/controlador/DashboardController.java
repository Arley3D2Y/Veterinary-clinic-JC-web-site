package com.example.demo.controlador;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.servicio.TratamientoService;
import com.example.demo.servicio.VeterinarioService;
import com.example.demo.servicio.DashboardService;
import com.example.demo.DTO.MascotasDTO;
import com.example.demo.DTO.VeterinarioEstadisticasDTO;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DashboardController {

    @Autowired
    private TratamientoService tratamientoService;

    @Autowired
    private VeterinarioService veterinarioService;

    @Autowired
    private DashboardService dashboardService;

    /** Dashboard Endpoints **/

    // localhost:8091/api/dashboard/total-tratamientos
    @GetMapping("/total-tratamientos-mes")
    @Operation(summary = "Get total number of treatments")
    public ResponseEntity<Number> getTotalTratamientos() {
        Number totalTratamientos = tratamientoService.countTratamientosByMonth();
        return ResponseEntity.ok(totalTratamientos);
    }

    // localhost:8091/api/dashboard/veterinarios
    @GetMapping("/veterinarios-estados")
    @Operation(summary = "Get active and inactive veterinarians count")
    public ResponseEntity<VeterinarioEstadisticasDTO> getVeterinarios() {
        Number veterinariosActivos = veterinarioService.contarVeterinariosActivos();
        Number veterinariosInactivos = veterinarioService.contarVeterinariosInactivos(); // Asegúrate de tener este

        VeterinarioEstadisticasDTO estadisticas = new VeterinarioEstadisticasDTO(veterinariosActivos,
                veterinariosInactivos);
        return ResponseEntity.ok(estadisticas);
    }

    // localhost:8091/api/dashboard/total-ventas-por-medicamento
    @GetMapping("/total-ventas-por-medicamento")
    @Operation(summary = "Get total sales by medication")
    public ResponseEntity<Map<String, Double>> getTotalVentasPorMedicamento() {
        Map<String, Double> totalVentas = dashboardService.obtenerTotalVentasPorMedicamento();
        return ResponseEntity.ok(totalVentas);
    }

    @GetMapping("/ganancias")
    public ResponseEntity<Double> getGanancias() {
        Double ganancias = dashboardService.calcularGanancias();
        return ResponseEntity.ok(ganancias);
    }

    
    @GetMapping("/mascotas")
    @Operation(summary = "Get total number of pets and active pets count")
    public ResponseEntity<MascotasDTO> getMascotas() {
        Long totalMascotas = dashboardService.obtenerTotalMascotas(); // Método que obtendrá el total
        Long totalMascotasActivas = dashboardService.obtenerTotalMascotasActivas(); // Método que obtendrá el total activo
    
        MascotasDTO mascotasDTO = new MascotasDTO(totalMascotas, totalMascotasActivas);
        return ResponseEntity.ok(mascotasDTO);
    }

    // // localhost:8091/api/dashboard/total-medicamentos-suministrados
    // @GetMapping("/total-medicamentos-suministrados")
    // public ResponseEntity<Integer> getTotalMedicamentosSuministrados() {
    //     Integer totalMedicamentos = dashboardService.obtenerTotalMedicamentosSuministrados();
    //     return ResponseEntity.ok(totalMedicamentos);
    // }
    // @GetMapping("/finanzas")
    // @Operation(summary = "Get total sales and earnings")
    // public ResponseEntity<Map<String, Double>> getFinanzas() {
    // double ventasTotales = dashboardService.calcularVentasTotales(); //
    // Implementa este método en tu servicio
    // double gananciasTotales = dashboardService.calcularGananciasTotales(); //
    // Implementa este método en tu servicio

    // Map<String, Double> finanzas = new HashMap<>();
    // finanzas.put("ventasTotales", ventasTotales);
    // finanzas.put("gananciasTotales", gananciasTotales);

    // return ResponseEntity.ok(finanzas);
    // }

    // localhost:8091/api/dashboard/mascotas

    // localhost:8091/api/dashboard/top-tratamientos
    // @GetMapping("/top-tratamientos")

    // @Operation(summary = "Get top treatments")
    // public ResponseEntity<List<TratamientoDTO>> getTopTratamientos() {
    //     List<TratamientoDTO> topTratamientos = tratamientoService.getTopTratamientos();
    //     return ResponseEntity.ok(topTratamientos);
    // }

    // @PostMapping("/incrementar-cantidad-vendida/{drogaId}")
    // public ResponseEntity<String> incrementarCantidadVendida(@PathVariable Long
    // drogaId) {
    // boolean actualizado = dashboardService.incrementarCantidadVendida(drogaId);

    // if (actualizado) {
    // return ResponseEntity.ok("Cantidad vendida incrementada exitosamente");
    // } else {
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    // .body("No hay suficientes unidades disponibles para la venta");
    // }
    // }

}
