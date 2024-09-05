package com.example.demo.servicio;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Veterinario;
import com.example.demo.repositorio.VeterinarioRepository;

@Service
public class VeterinarioServiceImpl implements VeterinarioService {

    @Autowired
    VeterinarioRepository repo;

     // Implementacion de los metodos
    @Override
    public Veterinario SearchById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Veterinario SearchByCedula(String cedula) {
        return repo.findByCedula(cedula);
    }

    @Override
    public Veterinario SearchByCorreo(String correo) {
        return repo.findByCorreo(correo);
    }

    @Override
    public Collection<Veterinario> SearchAll() {
        return repo.findAll();
    }

    @Override
    public void addCliente(Veterinario cliente) {
        repo.save(cliente);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void update(Veterinario cliente) {
        repo.save(cliente);
    }
}
