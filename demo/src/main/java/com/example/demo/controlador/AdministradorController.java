package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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

import com.example.demo.servicio.AdministradorService;

import com.example.demo.model.Administrador;
import com.example.demo.security.JWTGenerator;

import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/administradores")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdministradorController {

    @Autowired
    private AdministradorService admService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTGenerator jwtGenerator;

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

    // localhost:8080/administradores/login
    @PostMapping("/login")
    public ResponseEntity loginAdmin(@RequestBody Administrador administrador) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(administrador.getUsuario(), administrador.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        return new ResponseEntity<String>(token, HttpStatus.OK);
    }
}