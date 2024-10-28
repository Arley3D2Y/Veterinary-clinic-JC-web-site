package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.servicio.AdministradorService;
import com.example.demo.model.Administrador;
import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/administradores")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdministradorController {
    
    @Autowired
    private AdministradorService admService;

    // localhost:8080/administradores
    @GetMapping
    @Operation(summary = "Find all administrators")
    public ResponseEntity<List<Administrador>> getAdministradores() {
        List<Administrador> administradores = admService.searchAllAdministradores();

        return ResponseEntity.ok(administradores);
    }

    // localhost:8080/administradores/find/{id}
    @GetMapping("/find/{id}")
    @Operation(summary = "Find administrator by id")
    public ResponseEntity<Administrador> getAdministradorById(@PathVariable Long id) {
        Optional<Administrador> administrador = admService.searchAdministradorById(id);

        return administrador.map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /* Busquedas - search by */

    @GetMapping("/find/search-by-username/{username}")
    @Operation(summary = "Find administrator by username")
    public ResponseEntity<Administrador> getAdministradorByUsername(@PathVariable String username) {
        Optional<Administrador> administrador = admService.searchByUser(username);
        
        return administrador.map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
