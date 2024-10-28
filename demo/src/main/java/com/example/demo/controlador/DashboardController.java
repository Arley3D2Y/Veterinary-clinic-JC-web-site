package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.servicio.TratamientoService;
import com.example.demo.servicio.VeterinarioService;
import com.example.demo.DTO.GenericoDTO;
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


    /** Dashboard Endpoints **/

    // localhost:8091/api/dashboard/total-tratamientos
    @GetMapping("/total-tratamientos-mes")
    @Operation(summary = "Get total number of treatments")
    public ResponseEntity<Number> getTotalTratamientos() {
        Number totalTratamientos = tratamientoService.countTratamientosByMonth();
        return ResponseEntity.ok(totalTratamientos);
    }

    // localhost:8091/api/dashboard/tratamientos-por-medicamento
    @GetMapping("/tratamientos-por-medicamento")
    @Operation(summary = "Get treatments grouped by medication")
    public ResponseEntity<List<GenericoDTO>> getTratamientosPorMedicamento() {
        List<GenericoDTO> tratamientos = tratamientoService.getTratamientosPorMedicamento();
        return ResponseEntity.ok(tratamientos);
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


     
    // localhost:8091/api/dashboard/top-tratamientos
     /* // localhost:8091/api/dashboard/mascotas
     * 
     * @GetMapping("/mascotas")
     * 
     * @Operation(summary = "Get total number of pets and active pets count")
     * public ResponseEntity<MascotasDTO> getMascotas() {
     * MascotasDTO mascotas = mascotaService.getMascotasCount();
     * return ResponseEntity.ok(mascotas);
     * }
     * 
     * @GetMapping("/top-tratamientos")
     * 
     * @Operation(summary = "Get top treatments")
     * public ResponseEntity<List<TratamientoDTO>> getTopTratamientos() {
     * List<TratamientoDTO> topTratamientos =
     * tratamientoService.getTopTratamientos();
     * return ResponseEntity.ok(topTratamientos);
     * }
     */
}
