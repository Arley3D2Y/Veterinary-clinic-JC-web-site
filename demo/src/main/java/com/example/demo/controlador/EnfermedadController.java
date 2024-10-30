package com.example.demo.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Enfermedad;
import com.example.demo.servicio.EnfermedadService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/enfermedades")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EnfermedadController {
    @Autowired
    private EnfermedadService enfermedadService;

    /* Tratamientos: Peticiones CRUD */

    // localhost:8088/tratamientos
    @GetMapping
    @Operation(summary = "Find all treatments")
    public ResponseEntity<List<Enfermedad>> obtenerTratamientos() {
        List<Enfermedad> enfermedades = enfermedadService.searchAllEnfermedades();
        
        return ResponseEntity.ok(enfermedades);
    }

    // localhost:8088/tratamientos/find/{id}
    @GetMapping("/find/{id}")
    @Operation(summary = "Find treatment by id")
    public ResponseEntity<Enfermedad> obtenerInfoEnfermedadPorId(@PathVariable Long id) {
        Optional<Enfermedad> enfermedad = enfermedadService.searchEnfermedadById(id);

        return enfermedad.map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
}

