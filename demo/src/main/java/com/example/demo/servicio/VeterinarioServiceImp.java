package com.example.demo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Veterinario;
import com.example.demo.repositorio.VeterinarioRepository;

@Service
public class VeterinarioServiceImp implements VeterinarioService {

    @Autowired
    VeterinarioRepository veterinarioRepo;

    @Override
    public List<Veterinario> searchAllVeterinarios() {
        return veterinarioRepo.findAll();
    }

    @Override
    public Optional<Veterinario> searchVeterinarioById(Long id) {
        return veterinarioRepo.findById(id);
    }

    @Override
    public Optional<Veterinario> addVeterinario(Veterinario veterinario) {
        Optional<Veterinario> veterinarioOpt = veterinarioRepo.findByCedula(veterinario.getCedula());
        
        if (!veterinarioOpt.isPresent()) {
            veterinario = veterinarioRepo.save(veterinario);
            return Optional.of(veterinario);
        } 
        return Optional.empty();
    }

    @Override
    public boolean removeById(Long id) {
        if (veterinarioRepo.existsById(id)) {
            veterinarioRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Veterinario> updateById(Long id, Veterinario veterinario) {
        if (veterinarioRepo.existsById(id)) {
            veterinario = veterinarioRepo.save(veterinario);
            return Optional.of(veterinario);
        } 
        return Optional.empty();
    }

    @Override
    public List<Veterinario> searchByNombre(String nombre) {
        return veterinarioRepo.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Optional<Veterinario> searchByCedula(String cedula) {
        return veterinarioRepo.findByCedula(cedula);
    }

    @Override
    public Optional<Veterinario> searchByCorreo(String correo) {
        return veterinarioRepo.findByCorreo(correo);
    }

}
