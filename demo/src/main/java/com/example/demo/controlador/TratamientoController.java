package com.example.demo.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin(origins = "http://localhost:4200")
public class TratamientoController {
    @Autowired
    private TratamientoService tratamientoService;

    /* Tratamientos */

    // localhost:8091/tratamientos
    @GetMapping
    @Operation(summary = "Find all treatments")
    public ResponseEntity<List<Tratamiento>> mostrarTratamientos() {
        List<Tratamiento> tratamientos = tratamientoService.searchAll();
        if (tratamientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tratamientos);
    }

    // localhost:8091/tratamientos/find/{id}
    @GetMapping("/find/{id}")
    @Operation(summary = "Find treatment by id")
    public ResponseEntity<Tratamiento> mostrarInfoTratamientoPorId(@PathVariable Long id) {
        Optional<Tratamiento> tratamiento = tratamientoService.searchById(id);
        return tratamiento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // localhost:8091/tratamientos/add
    @PostMapping("/add")
    @Operation(summary = "Add new treatment")
    private ResponseEntity<String> crearTratamiento(@RequestBody Tratamiento tratamiento) {
        boolean isAdded = tratamientoService.addTratamiento(tratamiento);
        if (isAdded) {
            return ResponseEntity.ok("Treatment added successfully");
        }
        return ResponseEntity.badRequest().body("Treatment already exists");
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
    public ResponseEntity<Void> actualizarTratamiento(@PathVariable Long id, @RequestBody Tratamiento tratamiento) {
        boolean isUpdated = tratamientoService.updateById(id, tratamiento);
        if (isUpdated) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

