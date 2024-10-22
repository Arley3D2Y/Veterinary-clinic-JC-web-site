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
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Especialidad;
import com.example.demo.model.Veterinario;
import com.example.demo.servicio.EspecialidadesService;
import com.example.demo.servicio.VeterinarioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/veterinarios")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;
    @Autowired
    private EspecialidadesService especialidadService;
    /* Veterinarios */

    // localhost:8091/veterinarios
    @GetMapping
    @Operation(summary = "Find all veterinarys")
    public ResponseEntity<List<Veterinario>> obtenerVeterinarios() {
        List<Veterinario> veterinarios = veterinarioService.searchAllVeterinarios();
        if (veterinarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(veterinarios);
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Find veterinary by id")
    public ResponseEntity<Veterinario> obtenerVeterinarioPorId(@PathVariable Long id) {
        Optional<Veterinario> veterinario = veterinarioService.searchVeterinarioById(id);
        return veterinario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new veterinary")
    public ResponseEntity<Veterinario> crearVeterinario(@RequestBody Veterinario veterinario) {
        Optional<Veterinario> nuevoVeterinario = veterinarioService.addVeterinario(veterinario);
        veterinarioService.actualizarEstadoVeterinario(nuevoVeterinario.get().getId());
        return nuevoVeterinario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).body(null)); // Retorna un error 409
                                                                                         // Conflict si ya existe
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete veterinary by id")
    public ResponseEntity<Void> eliminarVeterinario(@PathVariable Long id) {
        boolean isDeleted = veterinarioService.removeById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update veterinary by id")
    public ResponseEntity<Veterinario> actualizarVeterinario(@PathVariable Long id,
            @RequestBody Veterinario veterinario) {
        Optional<Veterinario> veterionarioActualizado = veterinarioService.updateById(id, veterinario);
        veterinarioService.actualizarEstadoVeterinario(veterionarioActualizado.get().getId());
        return veterionarioActualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search-by-name/{search}")
    @Operation(summary = "Search veterinary by name")
    public ResponseEntity<List<Veterinario>> buscarVeterinarios(@PathVariable String search) {
        List<Veterinario> veterinarios = veterinarioService.searchByNombre(search);
        return ResponseEntity.ok(veterinarios); // 200 OK
    }

    @GetMapping("/search-by-document/{search}")
    @Operation(summary = "Search veterinary by document")
    public ResponseEntity<Veterinario> buscarVeterinarioByCedula(@PathVariable String search) {
        Optional<Veterinario> veterinario = veterinarioService.searchByCedula(search);
        if (veterinario.isPresent()) {
            return ResponseEntity.ok(veterinario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search-by-email/{search}")
    @Operation(summary = "Search veterinary by email")
    public ResponseEntity<Veterinario> buscarVeterinarioByCorreo(@PathVariable String search) {
        Optional<Veterinario> veterinario = veterinarioService.searchByCorreo(search);
        if (veterinario.isPresent()) {
            return ResponseEntity.ok(veterinario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Dentro del controlador de veterinario
    @GetMapping("/especialidades")
    public List<Especialidad> getAllEspecialidades() {
        return especialidadService.findAll();
    }

    @PostMapping("/especialidades")
    public Especialidad createEspecialidad(@RequestBody Especialidad especialidad) {
        return especialidadService.save(especialidad);
    }

}
