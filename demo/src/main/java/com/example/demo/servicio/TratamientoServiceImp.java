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
    public boolean addTratamiento(Tratamiento tratamiento) {
        if (!trataRepo.existsById(tratamiento.getId())) {
            trataRepo.save(tratamiento);
            return true;
        }
        return false;
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
    public boolean updateById(Long id, Tratamiento tratamiento) {
        if (trataRepo.existsById(id)) {
            trataRepo.save(tratamiento);
            return true;
        }
        return false;
    }


}
