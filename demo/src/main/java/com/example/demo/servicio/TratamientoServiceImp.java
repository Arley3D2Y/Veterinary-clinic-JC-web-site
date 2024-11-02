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
        EstadoSalud estado = EstadoSalud.SANO;

        if (mascota.isPresent() && veterinario.isPresent() && droga.isPresent()) {
            if (veterinario.get().getEstado() ) {
                Mascota m = mascota.get();
                Droga d = droga.get();
                Veterinario v = veterinario.get();

                if (v.agregarTratamiento(tratamiento) && m.agregarTratamiento(tratamiento, estado) && d.agregarTratamiento(tratamiento)) {
                    return Optional.of(tratamientoRep.save(tratamiento)); // Guardar el tratamiento
                } else {
                    v.eliminarTratamiento(tratamiento);
                    m.eliminarTratamiento(tratamiento);
                    d.eliminarTratamiento(tratamiento);

                    return Optional.empty();
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
            tratamiento.setMascota(tratOpt.get().getMascota());
            tratamiento.setVeterinario(tratOpt.get().getVeterinario());
            tratamiento.setDroga(tratOpt.get().getDroga());
            tratamiento = tratamientoRep.save(tratamiento);
            return Optional.of(tratamiento);
        }
        return Optional.empty();
    }


    /* Busquedas - search by */

    // Busqueda de tratamientos por nombre
    @Override
    public List<Tratamiento> searchByDescription(String description) {
        List<Tratamiento> tratamientos = tratamientoRep.findByDescripcionStartingWithIgnoreCase(description);
        return tratamientos;
    }


    /* Buscar listas del tratamiento o por entidades */

    // Busqueda de tratamientos de un veterinario
    @Override
    public List<Tratamiento> searchByVeterinarioId(Long id) {
        return tratamientoRep.findByVeterinarioId(id);
    }

    // Busqueda de tratamientos de una mascota
    @Override
    public List<Tratamiento> searchByMascotaId(Long id) {
        return tratamientoRep.findByMascotaId(id);
    }

    // Busqueda de tratamientos por droga
    @Override
    public List<Tratamiento> searchByDrogaId(Long id) {
        return tratamientoRep.findByDrogaId(id);
    }

    /* Aún no revisado */

}
