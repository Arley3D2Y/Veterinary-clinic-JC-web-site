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

import com.example.demo.model.EstadoSalud;
import com.example.demo.servicio.EstadoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/estados")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EstadoController {
    @Autowired
    private EstadoService estadoService;

    /* Tratamientos: Peticiones CRUD */

    // localhost:8088/tratamientos
    @GetMapping
    @Operation(summary = "Find all states")
    public ResponseEntity<List<EstadoSalud>> obtenerEstados() {
        List<EstadoSalud> estados = estadoService.searchAllEstados();
        
        return ResponseEntity.ok(estados);
    }

    // localhost:8088/tratamientos/find/{id}
    @GetMapping("/find/{id}")
    @Operation(summary = "Find state by id")
    public ResponseEntity<EstadoSalud> obtenerInfoEstadoPorId(@PathVariable Long id) {
        Optional<EstadoSalud> estado = estadoService.searchEstadoById(id);

        return estado.map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

