package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.model.Droga;
import com.example.demo.servicio.DrogaService;

import io.swagger.v3.oas.annotations.Operation;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/drogas")
@CrossOrigin(origins = "localhost:4200")
public class DrogaController {
    @Autowired
    private DrogaService drogaService;

    /* Drogas */

    // localhost:4200/drogas
    @GetMapping
    @Operation(summary = "Get all drugs")
    public ResponseEntity<List<Droga>> obteneDrogas() {
        List<Droga> drogas = drogaService.searchAll();
        if (drogas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(drogas);
    }

    // localhost:8091/drogas/find/{id}
    @GetMapping("/find/{id}")
    @Operation(summary = "Get drug by id")
    public ResponseEntity<Droga> obtenerDrogaPorId(@PathVariable Long id) {
        Optional<Droga> droga = drogaService.searchById(id);
        return droga.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // localhost:8091/drogas/add
    @PostMapping("/add")
    @Operation(summary = "Add a new drug")
    public ResponseEntity<String> crearDroga(@RequestBody Droga droga) {
        boolean isAdded = drogaService.addDroga(droga);
        if (isAdded) {
            return ResponseEntity.ok("Drug added successfully");
        }
        return ResponseEntity.badRequest().body("Drug already exists");
    }

    // localhost:8091/drogas/delete/{id}
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete drug by id")
    public ResponseEntity<Void> eliminarDroga(@PathVariable Long id) {
        boolean isDeleted = drogaService.removeById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update drug by id")
    public ResponseEntity<Void> actualizarDroga(@PathVariable Long id, @RequestBody Droga droga) {
        boolean isUpdated = drogaService.updateById(id, droga);
        if (!isUpdated) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search-by-name/{search}")
    @Operation(summary = "Find client by name")
    public ResponseEntity<List<Droga>> buscarDrogas(@PathVariable String search) {
        List<Droga> drogas = drogaService.searchByNombre(search);
        return ResponseEntity.ok(drogas); 
    }

}
