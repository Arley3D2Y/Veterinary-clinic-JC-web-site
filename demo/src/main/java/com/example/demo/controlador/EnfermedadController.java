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
import org.springframework.web.bind.annotation.RequestBody;
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

    // localhost:8088/enfermedades
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

    // localhost:8088/enfermedades/add
    @PostMapping("/add")
    @Operation(summary = "Add a new disease")
    private ResponseEntity<Enfermedad> crearEnfermedad(@RequestBody Enfermedad enfermedad) {
        Optional<Enfermedad> nuevaEnfermedad = enfermedadService.addEnfermedad(enfermedad);

        return nuevaEnfermedad.map(e -> new ResponseEntity<>(e, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    // localhost:8088/enfermedad/update/{id}
    @PutMapping("/update/{id}")
    @Operation(summary = "Update disease by id")
    private ResponseEntity<Enfermedad> actualizarEnfermedad(@PathVariable Long id, @RequestBody Enfermedad enfermedad) {
        Optional<Enfermedad> enfermerdadActualizada = enfermedadService.updateById(id, enfermedad);

        return enfermerdadActualizada.map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // localhost:8088/enfermedades/delete/{id}
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete disease by id")
    private ResponseEntity<Void> eliminarEnfermedad(@PathVariable Long id) {
        boolean isDeleted = enfermedadService.removeById(id);

        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // localhost:8088/enfermedades/search-by-name/{search}
    @GetMapping("/search-by-name/{search}")
    @Operation(summary = "Find disease by name")
    public ResponseEntity<List<Enfermedad>> searchByNombre(@PathVariable String search) {
        List<Enfermedad> enfermedades = enfermedadService.serchEnfermedadByNombre(search);
        return ResponseEntity.ok(enfermedades);
    }

    // localhost:8088/enfermedades/search-by-name/{search}
    @GetMapping("/search-by-sintomas/{search}")
    @Operation(summary = "Find disease by sintoma")
    public ResponseEntity<List<Enfermedad>> searchBySintomas(@PathVariable String search) {
        List<Enfermedad> enfermedades = enfermedadService.serchEnfermedadsBySintomas(search);
        return ResponseEntity.ok(enfermedades);
    }
    
}
