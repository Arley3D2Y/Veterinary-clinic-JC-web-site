package com.example.demo.servicio;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Droga;
import com.example.demo.model.EstadoSalud;
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
    TratamientoRepository tratamientoRep;
    @Autowired
    MascotaRepository mascotaRep;
    @Autowired
    VeterinarioRepository veterinarioRepo;
    @Autowired
    DrogaRepository drogaRep;

    /* Tratamiento: Peticiones CRUD */

    // Busqueda de todos los tratamientos
    @Override
    public List<Tratamiento> searchAll() {
        return tratamientoRep.findAll();
    }

    // Busqueda de un tratamiento por id
    @Override
    public Optional<Tratamiento> searchById(Long id) {
        return tratamientoRep.findById(id);
    }

    // Creacion de un nuevo tratamiento
    @Override
    public Optional<Tratamiento> addTratamiento(Long idp, Long idv, Long idd, Tratamiento tratamiento) {
        Optional<Mascota> mascota = mascotaRep.findById(idp);
        Optional<Veterinario> veterinario = veterinarioRepo.findById(idv);
        Optional<Droga> droga = drogaRep.findById(idd);
        EstadoSalud estado = EstadoSalud.OBSERVACION;

        if (mascota.isPresent() && veterinario.isPresent() && droga.isPresent()) {
            if (veterinario.get().getEstado() ) {
                Mascota m = mascota.get();
                Droga d = droga.get();
                Veterinario v = veterinario.get();

                if (v.isTreatmentAddable(tratamiento) && d.isTreatmentAddable(tratamiento) && m.isTreatmentAddable(tratamiento)) {
                    v.agregarTratamiento(tratamiento);
                    d.agregarTratamiento(tratamiento);
                    m.agregarTratamiento(tratamiento, estado);

                    tratamiento = tratamientoRep.save(tratamiento);
                    return Optional.of(tratamiento);
                }
            }       
        }
        return Optional.empty();
    }

    // Eliminacion de un tratamiento
    @Override
    public boolean removeById(Long id) {
        if (tratamientoRep.existsById(id)) {
            tratamientoRep.deleteById(id);
            return true;
        }
        return false;
    }

    // Actualizacion de un tratamiento
    @Override
    public Optional<Tratamiento> updateById(Long id, Tratamiento tratamiento) {
        Optional<Tratamiento> tratOpt = tratamientoRep.findById(id);
        if (tratOpt.isPresent()) {
            tratamiento.setDroga(tratamiento.getDroga());
            
            tratamiento.setMascota(tratOpt.get().getMascota());                
            tratamiento.setVeterinario(tratOpt.get().getVeterinario());
            tratamiento = tratamientoRep.save(tratamiento);
            return Optional.of(tratamiento);
        }
        return Optional.empty();
    }


    /* Busquedas - search by */

    // Busqueda de tratamientos por descripción
    @Override
    public List<Tratamiento> searchByDescription(String description) {
        List<Tratamiento> tratamientos = tratamientoRep.findByDescripcionStartingWithIgnoreCase(description);
        return tratamientos;
    }


    /* Buscar listas del tratamiento o por entidades */

    // Busqueda de veterinario por tratamiento
    @Override
    public Optional<Veterinario> obtenerVeterinarioPorTratamiento(Long tratamientoId) {
        Optional<Tratamiento> tratamiento = tratamientoRep.findById(tratamientoId);
        return tratamiento.map(Tratamiento::getVeterinario);
    }

    // Busqueda de mascota por tratamiento
    @Override
    public Optional<Mascota> obtenerMascotaPorTratamiento(Long tratamientoId) {
        Optional<Tratamiento> tratamiento = tratamientoRep.findById(tratamientoId);
        return tratamiento.map(Tratamiento::getMascota);
    }

    // Busqueda de droga por tratamiento
    @Override
    public Optional<Droga> obtenerDrogaPorTratamiento(Long tratamientoId) {
        Optional<Tratamiento> tratamiento = tratamientoRep.findById(tratamientoId);
        return tratamiento.map(Tratamiento::getDroga);
    }

}
