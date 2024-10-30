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

    /* Droga: Peticiones CRUD */

    // Busqueda de todas las drogas
    @Override
    public List<Enfermedad> searchAllEnfermedades() {
        return enfermedadRepo.findAll();
    }

    // Busqueda de una droga por ID
    @Override
    public Optional<Enfermedad> searchEnfermedadById(Long id) {
        return enfermedadRepo.findById(id);
    }

}
