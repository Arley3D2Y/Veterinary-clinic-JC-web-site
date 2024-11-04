package com.example.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Administrador;
import com.example.demo.repositorio.AdministradorRepository;

@Service
public class AdministradorServiceImp implements AdministradorService{
    
    @Autowired
    private AdministradorRepository admRepository;

    /* Administrador: Peticiones CRUD */

    // Obtener todos los veterinarios
    public List<Administrador> searchAllAdministradores() {
        return admRepository.findAll();
    }

    // Obtener un veterinario por su ID
    public Optional<Administrador> searchAdministradorById(Long identificacion) {
        return admRepository.findById(identificacion);
    }

    /* Busquedas - search by */

    // Obtener un veterinario por su nombre
    public Optional<Administrador> searchByUser(String user) {
        return admRepository.findByUsuario(user);
    }

}
