package com.example.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Estado;
import com.example.demo.repositorio.EstadoRepository;

import java.util.Optional;
import java.util.List;

@Service
public class EstadoServiceImp implements EstadoService {

    @Autowired
    EstadoRepository estadoRepo;

    /* Droga: Peticiones CRUD */

    // Busqueda de todas las drogas
    @Override
    public List<Estado> searchAllEstados() {
        return estadoRepo.findAll();
    }

    // Busqueda de una droga por ID
    @Override
    public Optional<Estado> searchEstadoById(Long id) {
        return estadoRepo.findById(id);
    }

}
