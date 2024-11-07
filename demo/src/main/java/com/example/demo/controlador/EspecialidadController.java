package com.example.demo.controlador;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Especialidad;
import com.example.demo.servicio.EspecialidadService;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/especialidades")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EspecialidadController {
    @Autowired
    private EspecialidadService especialidadService;

    // localhost:8088/especialidades
    @GetMapping
    @Operation(summary = "Find all treatments")
    public ResponseEntity<List<Especialidad>> obtenerEspecialidades(){
        List<Especialidad> especialidades = especialidadService.searchAllEspecialidades();

        return ResponseEntity.ok(especialidades);

    }

    // localhost:8088/especialidades/find/{id}
    @GetMapping("/find/{id}")
    @Operation(summary = "Find treatment by id")
    public ResponseEntity<Especialidad> obtenerInfoEspecialidadPorId(@PathVariable Long id) {
        Optional<Especialidad> especialidad = especialidadService.searchEspecialidadById(id);

        return especialidad.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // localhost:8088/especialidades/add
    @PostMapping("/add")
    public ResponseEntity<Especialidad> crearEspecialidad(@RequestBody Especialidad especialidad) {
        Optional<Especialidad> nuevaEspecialidad = especialidadService.addEspecialidad(especialidad);
        
        return nuevaEspecialidad
                .map(e -> new ResponseEntity<>(e, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
    }
    
    // localhost:8088/especialidades/update/{id}
    @PutMapping("/update/{id}")
    @Operation(summary = "Update specialty by id")
    private ResponseEntity<Especialidad> actualizarEspecialidad(@PathVariable Long id, @RequestBody Especialidad especialidad) {
        Optional<Especialidad> especialidadActualizada = especialidadService.updateById(id, especialidad);

        return especialidadActualizada.map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // localhost:8088/especialidades/delete/{id}
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete specialty by id")
    private ResponseEntity<Void> eliminarEspecialidad(@PathVariable Long id) {
        boolean isDeleted = especialidadService.removeById(id);

        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // localhost:8088/especialidades/search-by-name/{search}
    @GetMapping("/search-by-name/{search}")
    @Operation(summary = "Find specialty by name")
    public ResponseEntity<Optional<Especialidad>> searchByNombre(@PathVariable String search) {
        Optional<Especialidad> especialidades = especialidadService.serchEspecialidadByNombre(search);
        return ResponseEntity.ok(especialidades);
    }

}
