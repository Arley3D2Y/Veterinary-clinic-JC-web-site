package com.example.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Enfermedad;
import com.example.demo.repositorio.EnfermedadRepository;

import java.util.Optional;
import java.util.List;

@Service
public class EnfermedadServiceImp implements EnfermedadService {

    @Autowired
    EnfermedadRepository enfermedadRepo;

    /* Enfermedades: Peticiones CRUD */

    // Busqueda de todas las drogas
    @Override
    public List<Enfermedad> searchAllEnfermedades() {
        return enfermedadRepo.findAll();
    }

    // Busqueda de una enfermedad por ID
    @Override
    public Optional<Enfermedad> searchEnfermedadById(Long id) {
        return enfermedadRepo.findById(id);
    }

    // Creacion de una nueva enfermedad
    @Override
    public Optional<Enfermedad> addEnfermedad(Enfermedad enfermedad) {
        Optional<Enfermedad> enfOptional = enfermedadRepo.findById(enfermedad.getId());

        if (!enfOptional.isPresent()) {
            enfermedad = enfermedadRepo.save(enfermedad);
            return Optional.of(enfermedad);
        }
        return Optional.empty();
    }

    // Busqueda de enfermedades por nombre
    @Override
    public List<Enfermedad> serchEnfermedadByNombre(String nombre) {

        return enfermedadRepo.findByNombreStartingWithIgnoreCase(nombre);
    }

    @Override
    public List<Enfermedad> serchEnfermedadsBySintomas(String sintomas) {

        return enfermedadRepo.findBySintomas(sintomas);
    }

    // Método para eliminar una enfermedad
    @Override
    public boolean removeById(Long identificacion) {
        if (enfermedadRepo.existsById(identificacion)) {
            enfermedadRepo.deleteById(identificacion);
            return true;
        }
        return false;
    }

}
