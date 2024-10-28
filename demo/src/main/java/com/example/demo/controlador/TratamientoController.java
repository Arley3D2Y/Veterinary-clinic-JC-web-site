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

    /* Tratamientos: Peticiones CRUD */

    // localhost:8088/tratamientos
    @GetMapping
    @Operation(summary = "Find all treatments")
    public ResponseEntity<List<Tratamiento>> obtenerTratamientos() {
        List<Tratamiento> tratamientos = tratamientoService.searchAll();
        
        return ResponseEntity.ok(tratamientos);
    }

    // localhost:8088/tratamientos/find/{id}
    @GetMapping("/find/{id}")
    @Operation(summary = "Find treatment by id")
    public ResponseEntity<Tratamiento> obtenerInfoTratamientoPorId(@PathVariable Long id) {
        Optional<Tratamiento> tratamiento = tratamientoService.searchById(id);

        return tratamiento.map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // localhost:8088/tratamientos/add
    @PostMapping("/add/mascota/{petId}/veterinario/{vetId}/droga/{drugId}")
    @Operation(summary = "Add new treatment")
    private ResponseEntity<Tratamiento> crearTratamiento(@PathVariable("petId") Long mascotaId, @PathVariable("vetId") Long veterinarioId,
            @PathVariable("drugId") Long drogaId, @RequestBody Tratamiento tratamiento) {
        
        try {
            Optional<Tratamiento> nuevoTratamiento = tratamientoService.addTratamiento(mascotaId, veterinarioId, drogaId, tratamiento);
            return nuevoTratamiento.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // localhost:8088/tratamientos/delete/{id}
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete treatment by id")
    public ResponseEntity<Void> eliminarTratamiento(@PathVariable Long id) {
        boolean isDeleted = tratamientoService.removeById(id);
        
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // localhost:8088/tratamientos/update/{id}
    @PutMapping("/update/{id}")
    @Operation(summary = "Update treatment by id")
    public ResponseEntity<Tratamiento> actualizarTratamiento(@PathVariable Long id, @RequestBody Tratamiento tratamiento) {
        Optional<Tratamiento> tratamientoActualizado = tratamientoService.updateById(id, tratamiento);
        
        return tratamientoActualizado.map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /* Busquedas - search by */
    
    // localhost:8088/tratamientos/search-by-description/{search}
    @GetMapping("/search-by-description/{search}")
    @Operation(summary = "Search treatments by name")
    public ResponseEntity<List<Tratamiento>> obtenerTratamientosPorNombre(@PathVariable String search) {
        List<Tratamiento> tratamientos = tratamientoService.searchByDescription(search);
        
        return ResponseEntity.ok(tratamientos);
    }


    /* Buscar listas del vtratamiento o por entidades */

    // localhost:8088/tratamientos/search-by-veterinario_id/{id}
    @GetMapping("/search-by-veterinario_id/{id}")
    @Operation(summary = "Search treatments by veterinarian id")
    public ResponseEntity<List<Tratamiento>> buscarTratamientosByVeterinarioId(@PathVariable Long id) {
        List<Tratamiento> tratamientos = tratamientoService.searchByVeterinarioId(id);
        
        return ResponseEntity.ok(tratamientos);
    }

    // localhost:8088/tratamientos/search-by-mascota_id/{id}
    @GetMapping("/search-by-mascota_id/{id}")
    @Operation(summary = "Search treatments by pet id")
    public ResponseEntity<List<Tratamiento>> buscarTratamientosByMascotaId(@PathVariable Long id) {
        List<Tratamiento> tratamientos = tratamientoService.searchByMascotaId(id);
        
        return ResponseEntity.ok(tratamientos);
    }

    // localhost:8088/tratamientos/search-by-droga_id/{id}
    @GetMapping("/search-by-droga_id/{id}")
    @Operation(summary = "Search treatments by pet id")
    public ResponseEntity<List<Tratamiento>> buscarTratamientosByDrogaId(@PathVariable Long id) {
        List<Tratamiento> tratamientos = tratamientoService.searchByDrogaId(id);
        
        return ResponseEntity.ok(tratamientos);
    }
    
}

