package com.example.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Droga;
import com.example.demo.repositorio.DrogaRepository;

import java.util.Optional;
import java.util.List;

@Service
public class DrogaServiceImp implements DrogaService {
    @Autowired
    DrogaRepository drogaRepo;

    @Override
    public List<Droga> searchAllDrogas() {
        return drogaRepo.findAll();
    }

    @Override
    public Optional<Droga> searchDrogaById(Long id) {
        return drogaRepo.findById(id);
    }

    @Override
    public Optional<Droga> addDroga(Droga droga) {
        Optional<Droga> drogaOpt = drogaRepo.findByNombreIgnoreCase(droga.getNombre());
        
        if (!drogaOpt.isPresent()) {
            droga = drogaRepo.save(droga);
            return Optional.of(droga);     
        }
        return Optional.empty();
    }

    @Override
    public boolean removeById(Long id) {
        if (drogaRepo.existsById(id)) {
            drogaRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Droga> updateById(Long id, Droga droga) {
        if (drogaRepo.existsById(id)) {
            droga = drogaRepo.save(droga);  // Save es usado tanto para crear como para actualizar
            return Optional.of(droga);
        }
        return Optional.empty();
    }

    @Override
    public List<Droga> searchByNombre(String nombre) {
        return drogaRepo.findByNombreContainingIgnoreCase(nombre);
    }

}
