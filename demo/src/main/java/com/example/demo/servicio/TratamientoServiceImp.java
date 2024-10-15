package com.example.demo.servicio;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Tratamiento;
import com.example.demo.repositorio.DrogaRepository;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.repositorio.VeterinarioRepository;

@Service
public class TratamientoServiceImp implements TratamientoService {

    @Autowired
    TratamientoRepository trataRepo;
    @Autowired
    MascotaRepository petRep;
    @Autowired
    VeterinarioRepository vetRep;
    @Autowired
    DrogaRepository drogaRep;

    @Override
    public List<Tratamiento> searchAll() {
        return trataRepo.findAll();
    }

    // implementacion de los metodos
    @Override
    public Optional<Tratamiento> searchById(Long id) {
        return trataRepo.findById(id);
    }

    @Override
    public Optional<Tratamiento> addTratamiento(Tratamiento tratamiento) {
        tratamiento = trataRepo.save(tratamiento);
        return Optional.of(tratamiento);
    }

    @Override
    public boolean removeById(Long id) {
        if (trataRepo.existsById(id)) {
            trataRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Tratamiento> updateById(Long id, Tratamiento tratamiento) {
        if (trataRepo.existsById(id)) {
            tratamiento = trataRepo.save(tratamiento);
            return Optional.of(tratamiento);
        }
        return Optional.empty();
    }

}
