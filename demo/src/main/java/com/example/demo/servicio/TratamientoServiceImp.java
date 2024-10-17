package com.example.demo.servicio;

import java.util.*;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Droga;
import com.example.demo.model.Mascota;
import com.example.demo.model.Tratamiento;
import com.example.demo.model.Veterinario;
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
    public Optional<Tratamiento> addTratamiento(Long idp, Long idv, Tratamiento tratamiento) {
        Optional<Mascota> pet = petRep.findById(idp);
        Optional<Veterinario> vet = vetRep.findById(idv);
        Optional<Droga> droga = drogaRep.findById(tratamiento.getDroga().getId());

        if (pet.isPresent() && vet.isPresent() && droga.isPresent()) {
            tratamiento.setMascota(pet.get());
            tratamiento.setVeterinario(vet.get());
            tratamiento = trataRepo.save(tratamiento);
            return Optional.of(tratamiento);
        }
        return Optional.empty();
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
        Optional<Tratamiento> tratOpt = trataRepo.findById(id);
        if (tratOpt.isPresent()) {
            tratamiento.setMascota(tratOpt.get().getMascota());
            tratamiento.setVeterinario(tratOpt.get().getVeterinario());
            tratamiento.setDroga(tratOpt.get().getDroga());
            
            tratamiento = trataRepo.save(tratamiento);
            return Optional.of(tratamiento);
        }
        return Optional.empty();
    }

    @Override
    public List<Tratamiento> searchByNombre(String name) {
        List<Tratamiento> tratamientos = trataRepo.findByDescripcionStartingWithIgnoreCase(name);
        return tratamientos;
    }

    @Override
    public List<Tratamiento> getTratamientosPorVeterinario(Long id) {
        Optional<Veterinario> vet = vetRep.findById(id);
        if (vet.isPresent()) {
            List<Tratamiento> tratamientos = trataRepo.findByVeterinario(vet.get());
            return tratamientos;
        } else {
            return List.of();
        }
    }

    @Override
    public List<Tratamiento> getTratamientosPorMascota(Long id) {
        Optional<Mascota> pet = petRep.findById(id);
        if (pet.isPresent()) {
            List<Tratamiento> tratamientos = trataRepo.findByMascota(pet.get());
            return tratamientos;
        } else {
            return List.of();
        }
    }





    @Override
    public Number Count(Date o) {

        return 0;
    }

    @Override
    public List<Tratamiento> getTratamientosPorMedicamento() {

        return List.of();
    }

    @Override
    public List<Tratamiento> getTopTratamientos() {
        return List.of();
    }


}
