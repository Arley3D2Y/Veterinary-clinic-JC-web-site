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

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Veterinario;
import com.example.demo.servicio.VeterinarioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/veterinarios")
@CrossOrigin(origins = "http://localhost:4200")
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;

    // localhost:8091/veterinarios
    @GetMapping
    @Operation(summary = "Find all veterinarios")
    public ResponseEntity<List<Veterinario>> mostrarVeterinarios() {
        List<Veterinario> veterinarios = veterinarioService.searchAllVeterinarios();
        if (veterinarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(veterinarios);
        }
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Find veterinario by id")
    public ResponseEntity<Veterinario> mostrarVeterinarioPorId(@PathVariable Long id) {
        Optional<Veterinario> veterinario = veterinarioService.searchVeterinarioById(id);
        return veterinario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new veterinario")
    public ResponseEntity<String> crearVeterinario(@RequestBody Veterinario veterinario) {
        boolean isAdded = veterinarioService.addVeterinario(veterinario);
        if (isAdded) {
            return ResponseEntity.ok("Veterinario added successfully");
        } else {
            return ResponseEntity.badRequest().body("Veterinario already exists");
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete veterinario by id")
    public ResponseEntity<Void> eliminarVeterinario(@PathVariable Long id) {
        boolean isDeleted = veterinarioService.removeById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update veterinario by id")
    public ResponseEntity<Void> actualizarVeterinario(@PathVariable Long id, @RequestBody Veterinario veterinario) {
        boolean isUpdated = veterinarioService.updateById(id, veterinario);
        if (!isUpdated) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/search-by-name/{search}")
    @Operation(summary = "Search pets by name")
    public ResponseEntity<List<Veterinario>> buscarVeterinarios(@PathVariable String search) {
        List<Veterinario> veterinarios = veterinarioService.searchByNombre(search);
        return ResponseEntity.ok(veterinarios); // 200 OK
    }

    @GetMapping("/search-by-document/{search}")
    @Operation(summary = "Search pets by document")
    public ResponseEntity<Veterinario> buscarVeterinarioByCedula(@PathVariable String search) {
        Optional<Veterinario> veterinario = veterinarioService.searchByCedula(search);
        if (veterinario.isPresent()) {
            return ResponseEntity.ok(veterinario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search-by-email/{search}")
    @Operation(summary = "Search pets by document")
    public ResponseEntity<Veterinario> buscarVeterinarioByCorreo(@PathVariable String search) {
        Optional<Veterinario> veterinario = veterinarioService.searchByCorreo(search);
        if (veterinario.isPresent()) {
            return ResponseEntity.ok(veterinario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
