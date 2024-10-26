package com.example.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositorio.AdministradorRepository;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Administrador;

@Service
public class AdministradorServiceImp implements AdministradorService{
    
    @Autowired
    private AdministradorRepository admRepository;

    public List<Administrador> searchAllAdministradores() {
        return admRepository.findAll();
    }

    public Optional<Administrador> searchAdministradorById(Long identificacion) {
        return admRepository.findById(identificacion);
    }

    public Optional<Administrador> searchByUser(String user) {
        return admRepository.findByUsuario(user);
    }

}
