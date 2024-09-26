package com.example.demo.servicio;

import java.time.LocalDate;
import java.util.*;

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
    TratamientoRepository repo;
    @Autowired
    MascotaRepository petRep;
    @Autowired
    VeterinarioRepository vetRep;
    @Autowired
    DrogaRepository drogaRep;

    // implementacion de los metodos
    @Override
    public Tratamiento SearchById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Collection<Tratamiento> SearchByStartDate(LocalDate startDate) {
        Collection<Tratamiento> result = new ArrayList<Tratamiento>();
        for (Tratamiento tratamiento : repo.findByDate(startDate)) {
            if (tratamiento.getFechaInicio() != null) {
                if (tratamiento.getFechaInicio() == LocalDate.from(startDate)) {
                    result.add(tratamiento);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<Tratamiento> SearchByEndDate(LocalDate startDate) {
        Collection<Tratamiento> result = new ArrayList<Tratamiento>();
        for (Tratamiento tratamiento : repo.findByDate(startDate)) {
            if (tratamiento.getFechaFin() != null) {
                if (tratamiento.getFechaInicio() == LocalDate.from(startDate)) {
                    result.add(tratamiento);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<Tratamiento> SearchByMascota(Long mascotaId) {
        Optional<Mascota> optionalMascota = petRep.findById(mascotaId);
        if (optionalMascota.isPresent()) {
            Mascota mascota = optionalMascota.get();
            return repo.findByMascota(mascota);
        } else {

            return Collections.emptyList();
        }
    }

    @Override
    public Collection<Tratamiento> SearchByVeterinario(Long veterianrioId) {
        Optional<Veterinario> optionalVeterianrio = vetRep.findById(veterianrioId); 
        if (optionalVeterianrio.isPresent()) {
            Veterinario veterinario = optionalVeterianrio.get();
            return repo.findByVeterianario(veterinario);
        } else {

            return Collections.emptyList();
        }
    }

    @Override
    public Collection<Tratamiento> SearchByDroga(Long drograId) {
        Optional<Droga> optionalDroga = drogaRep.findById(drograId);
        if (optionalDroga.isPresent()) {
            Droga droga = optionalDroga.get();
            return repo.findByDroga(droga);
        } else {

            return Collections.emptyList();
        }
    }

    @Override
    public Collection<Tratamiento> SearchAll() {
        return repo.findAll();
    }

    @Override
    public void addTratamiento(Tratamiento tratamiento) {
        repo.save(tratamiento);
    }

    @Override
    public void deleteById(Long identificacion) {
        repo.deleteById(identificacion);
    }

    @Override
    public void update(Tratamiento tratamiento) {
        repo.save(tratamiento);
    }

}
