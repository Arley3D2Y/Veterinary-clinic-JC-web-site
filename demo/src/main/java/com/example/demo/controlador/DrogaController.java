package com.example.demo.controlador;

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

import com.example.demo.model.Droga;
import com.example.demo.model.Tratamiento;
import com.example.demo.servicio.DrogaService;

import io.swagger.v3.oas.annotations.Operation;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/drogas")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DrogaController {
    @Autowired
    private DrogaService drogaService;


    /* Drogas: Peticiones CRUD */

    // localhost:8088/drogas
    @GetMapping
    @Operation(summary = "Find all drugs")
    public ResponseEntity<List<Droga>> obteneDrogas() {
        List<Droga> drogas = drogaService.searchAllDrogas();

        return ResponseEntity.ok(drogas);
    }

    // localhost:8088/drogas/find/{id}
    @GetMapping("/find/{id}")
    @Operation(summary = "Find drug by id")
    public ResponseEntity<Droga> obtenerDrogaPorId(@PathVariable Long id) {
        Optional<Droga> droga = drogaService.searchDrogaById(id);

        return droga.map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // localhost:8088/drogas/add
    @PostMapping("/add")
    @Operation(summary = "Add a new drug")
    public ResponseEntity<Droga> crearDroga(@RequestBody Droga droga) {
        Optional<Droga> nuevaDroga = drogaService.addDroga(droga);

        return nuevaDroga.map(d -> new ResponseEntity<>(d, HttpStatus.CREATED))
            .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    // localhost:8088/drogas/delete/{id}
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete drug by id")
    public ResponseEntity<Void> eliminarDroga(@PathVariable Long id) {
        boolean isDeleted = drogaService.removeById(id);

        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // localhost:8088/drogas/update/{id}
    @PutMapping("/update/{id}")
    @Operation(summary = "Update drug by id")
    public ResponseEntity<Droga> actualizarDroga(@PathVariable Long id, @RequestBody Droga droga) {
        Optional<Droga> drogaActualizada = drogaService.updateById(id, droga);
        
        return drogaActualizada.map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /* Busquedas - search by */
    
    // localhost:8088/drogas/search-by-name/{search}
    @GetMapping("/search-by-name/{search}")
    @Operation(summary = "Find drug by name")
    public ResponseEntity<List<Droga>> buscarDrogas(@PathVariable String search) {
        List<Droga> drogas = drogaService.searchByNombre(search);
        
        return ResponseEntity.ok(drogas); 
    }


    /* Buscar listas del veterinario o por entidades */

    // localhost:8088/drogas/tratamientos-droga/{id}
    @GetMapping("/tratamientos-droga/{id}")
    @Operation(summary = "Get treatments by drug")
    public ResponseEntity<List<Tratamiento>> getTratamientosDroga(@PathVariable Long id) {
        List<Tratamiento> tratamientos = drogaService.getTratamientosDroga(id);
        
        return ResponseEntity.ok(tratamientos);
    }

}
