package com.example.demo.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Tratamiento;
import com.example.demo.servicio.TratamientoService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/tratamientos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TratamientoController {
    @Autowired
    private TratamientoService tratamientoService;

    /* Tratamientos */

    // localhost:8091/tratamientos
    @GetMapping
    @Operation(summary = "Find all treatments")
    public ResponseEntity<List<Tratamiento>> mostrarTratamientos() {
        List<Tratamiento> tratamientos = tratamientoService.searchAll();
        
        ResponseEntity<List<Tratamiento>> response = new ResponseEntity<>(tratamientos, HttpStatus.OK);
        return response;
    }

    // localhost:8091/tratamientos/find/{id}
    @GetMapping("/find/{id}")
    @Operation(summary = "Find treatment by id")
    public ResponseEntity<Tratamiento> mostrarInfoTratamientoPorId(@PathVariable Long id) {
        Optional<Tratamiento> tratamiento = tratamientoService.searchById(id);
        return tratamiento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // localhost:8091/tratamientos/add
    @PostMapping("/add/mascota-id/{petId}/veterinario-id/{vetId}")
    @Operation(summary = "Add new treatment")
    private ResponseEntity<Tratamiento> crearTratamiento(@PathVariable("petId") Long pId, @PathVariable("vetId") Long vId, @RequestBody Tratamiento tratamiento) {
        Optional<Tratamiento> nuevoTratamiento = tratamientoService.addTratamiento(pId, vId, tratamiento);
        return nuevoTratamiento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // localhost:8091/tratamientos/delete/{id}
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete treatment by id")
    public ResponseEntity<Void> eliminarTratamiento(@PathVariable Long id) {
        boolean isDeleted = tratamientoService.removeById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update treatment by id")
    public ResponseEntity<Tratamiento> actualizarTratamiento(@PathVariable Long id, @RequestBody Tratamiento tratamiento) {
        Optional<Tratamiento> tratamientoActualizado = tratamientoService.updateById(id, tratamiento);
        return tratamientoActualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search-by-name/{name}")
    @Operation(summary = "Search treatments by name")
    public ResponseEntity<List<Tratamiento>> mostrarTratamientoPorNombre(@PathVariable String name) {
        List<Tratamiento> tratamientos = tratamientoService.searchByNombre(name);
        if (tratamientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tratamientos);
    }

    @GetMapping("/search-by-veterinario_id/{id}")
    @Operation(summary = "Search treatments by veterinarian id")
    public ResponseEntity<List<Tratamiento>> mostrarTratamientoPorVeterinario(@PathVariable Long id) {
        List<Tratamiento> tratamientos = tratamientoService.getTratamientosPorVeterinario(id);
        return ResponseEntity.ok(tratamientos);
    }

    @GetMapping("/search-by-mascota_id/{id}")
    @Operation(summary = "Search treatments by pet id")
    public ResponseEntity<List<Tratamiento>> mostrarTratamientoPorMascota(@PathVariable Long id) {
        List<Tratamiento> tratamientos = tratamientoService.getTratamientosPorMascota(id);
        return ResponseEntity.ok(tratamientos);
    }
    
}

