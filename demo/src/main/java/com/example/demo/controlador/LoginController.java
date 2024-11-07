package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.example.demo.model.UserEntity;
import com.example.demo.security.JWTGenerator;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoginController {
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTGenerator jwtGenerator;

    // localhost:8088/login/cliente
    @PostMapping("/cliente")
    @Operation(summary = "Ingresar como cliente")
    public ResponseEntity<?> loginCliente(@RequestBody UserEntity userEntity) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userEntity.getUsername(), "1234")
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

    // localhost:8088/login/veterinario
    @PostMapping("/veterinario")
    public ResponseEntity<?> loginVeterinary(@RequestBody UserEntity userEntity) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

    // localhost:8088/login/administrador
    @PostMapping("/administrador")
    public ResponseEntity<?> loginAdmin(@RequestBody UserEntity userEntity) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

    // JWT
    // sjapdgmfkhlnskñdmkjsdbavsouwriourgjlgd
    // Servidor: 12345
    // sjapdgmfkhlnskñdmkjsdbavsouwriourgjlgd = pedro@javeriana.edu.co, 1234, Veterinario
}
