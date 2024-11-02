package com.example.demo.servicio;

import java.util.Optional;

import java.util.List;

import com.example.demo.model.Enfermedad;

public interface EnfermedadService {

    public List<Enfermedad> searchAllEnfermedades();
    
    public Optional<Enfermedad> searchEnfermedadById(Long id);

    public Optional<Enfermedad> addEnfermedad(Enfermedad enfermedad);

    public boolean removeById(Long identificacion);


    public List<Enfermedad>  serchEnfermedadByNombre(String nombre);

    public List<Enfermedad> serchEnfermedadsBySintomas(String sintomas);

}
