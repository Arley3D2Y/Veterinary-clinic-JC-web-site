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

import com.example.demo.model.Tratamiento;
import com.example.demo.model.Veterinario;
import com.example.demo.servicio.VeterinarioService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/veterinarios")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;

    /* Veterinarios: Peticiones CRUD */

    // localhost:8088/veterinarios
    @GetMapping
    @Operation(summary = "Find all veterinarys")
    public ResponseEntity<List<Veterinario>> obtenerVeterinarios() {
        List<Veterinario> veterinarios = veterinarioService.searchAllVeterinarios();

        return ResponseEntity.ok(veterinarios);
    }

    // localhost:8088/veterinarios/find/{id}
    @GetMapping("/find/{id}")
    @Operation(summary = "Find veterinary by id")
    public ResponseEntity<Veterinario> obtenerVeterinarioPorId(@PathVariable Long id) {
        Optional<Veterinario> veterinario = veterinarioService.searchVeterinarioById(id);
        
        return veterinario.map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // localhost:8088/veterinarios/add
    @PostMapping("/add")
    @Operation(summary = "Add a new veterinary")
    public ResponseEntity<Veterinario> crearVeterinario(@RequestBody Veterinario veterinario) {
        Optional<Veterinario> nuevoVeterinario = veterinarioService.addVeterinario(veterinario);

        return nuevoVeterinario.map(c -> new ResponseEntity<>(c, HttpStatus.CREATED))
            .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    // localhost:8088/veterinarios/delete/{id}
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete veterinary by id")
    public ResponseEntity<Void> eliminarVeterinario(@PathVariable Long id) {
        boolean isDeleted = veterinarioService.removeById(id);

        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // localhost:8088/veterinarios/update/{id}
    @PutMapping("/update/{id}")
    @Operation(summary = "Update veterinary by id")
    public ResponseEntity<Veterinario> actualizarVeterinario(@PathVariable Long id, @RequestBody Veterinario veterinario) {
        Optional<Veterinario> veterionarioActualizado = veterinarioService.updateById(id, veterinario);
        
        return veterionarioActualizado.map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /* Busquedas - search by */

    // localhost:8088/veterinarios/search-by-name/{search}
    @GetMapping("/search-by-name/{search}")
    @Operation(summary = "Search veterinarians by name")
    public ResponseEntity<List<Veterinario>> searchByNombre(@PathVariable String search) {
        List<Veterinario> veterinarios = veterinarioService.searchByNombre(search);

        return ResponseEntity.ok(veterinarios); // 200 OK
    }

    // localhost:8088/veterinarios/search-by-document/{search}
    @GetMapping("/search-by-document/{search}")
    @Operation(summary = "Search veterinary by document")
    public ResponseEntity<Veterinario> buscarVeterinarioByCedula(@PathVariable String search) {
        Optional<Veterinario> veterinario = veterinarioService.searchByCedula(search);

        return veterinario.map(v -> new ResponseEntity<>(v, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // localhost:8088/veterinarios/search-by-email/{search}
    @GetMapping("/search-by-email/{search}")
    @Operation(summary = "Search veterinary by email")
    public ResponseEntity<Veterinario> buscarVeterinarioByCorreo(@PathVariable String search) {
        Optional<Veterinario> veterinario = veterinarioService.searchByCorreo(search);
        
        return veterinario.map(v -> new ResponseEntity<>(v, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /* buscar listas del veterinario o por entidades */

    // localhost:8088/veterinarios/tratamientos-veterinario/{id}
    @GetMapping("/tratamientos-veterinario/{id}")
    @Operation(summary = "Get treatments by veterinary")
    public ResponseEntity<List<Tratamiento>> getTratamientosVeterinario(@PathVariable Long id) {
        List<Tratamiento> tratamientos = veterinarioService.getTratamientosVeterinario(id);
        
        return (tratamientos == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
            : new ResponseEntity<>(tratamientos, HttpStatus.OK); 
    }
    


}
