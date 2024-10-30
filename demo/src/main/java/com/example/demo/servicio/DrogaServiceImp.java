package com.example.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Droga;
import com.example.demo.model.Tratamiento;
import com.example.demo.repositorio.DrogaRepository;
import com.example.demo.repositorio.TratamientoRepository;

import java.util.Optional;
import java.util.List;

@Service
public class DrogaServiceImp implements DrogaService {

    @Autowired
    DrogaRepository drogaRepo;

    @Autowired
    TratamientoRepository tratamientoRepo;

    /* Droga: Peticiones CRUD */

    // Busqueda de todas las drogas
    @Override
    public List<Droga> searchAllDrogas() {
        return drogaRepo.findAll();
    }

    // Busqueda de una droga por ID
    @Override
    public Optional<Droga> searchDrogaById(Long id) {
        return drogaRepo.findById(id);
    }

    // Creacion de una nueva droga
    @Override
    public Optional<Droga> addDroga(Droga droga) {
        Optional<Droga> drogaOpt = drogaRepo.findByNombreIgnoreCase(droga.getNombre());
        
        if (!drogaOpt.isPresent()) {
            droga = drogaRepo.save(droga);
            return Optional.of(droga);     
        }
        return Optional.empty();
    }

    // Eliminacion de una droga
    @Override
    public boolean removeById(Long id) {
        Optional<Droga> drogaOpt = drogaRepo.findById(id);

        if (drogaOpt.isPresent()) {
            Droga droga = drogaOpt.get();

            for (Tratamiento t : droga.getTratamientos()) {
                t.setDroga(null);
                tratamientoRepo.save(t);
            }
            drogaRepo.deleteById(id);
            return true;
        }
        return false;
    }

    // Actualizacion de una droga
    @Override
    public Optional<Droga> updateById(Long id, Droga droga) {
        Optional<Droga> drogaOpt = drogaRepo.findById(id);
        if (drogaOpt.isPresent()) {
            Droga d = drogaOpt.get();
            droga.setId(d.getId());
            droga.setTratamientos(d.getTratamientos());
            return Optional.of(drogaRepo.save(droga));
        }
        return Optional.empty();
    }


    /* Busquedas - search by */

    // Busqueda de drogas por nombre
    @Override
    public List<Droga> searchByNombre(String nombre) {
        return drogaRepo.findByNombreStartingWithIgnoreCase(nombre);
    }


    /* buscar listas del veterinario o por entidades */

    // Obtener tratamientos de una mascota
    @Override
    public List<Tratamiento> getTratamientosDroga(Long id) {
        Optional<Droga> dorgOpt = drogaRepo.findById(id);
        if (dorgOpt.isPresent()) {
           return dorgOpt.get().getTratamientos();
        }
        return null;
    }

    // Servicio raro no visto para tratamiento

    @Override
    public boolean decreaseDrugQuantity(Long id) {
        Optional<Droga> drogaOpt = drogaRepo.findById(id);
        if (drogaOpt.isPresent()) {
            Droga droga = drogaOpt.get();
            droga.setUnidadesDisponibles(droga.getUnidadesDisponibles() - 1);
            droga.setUnidadesVendidas(droga.getUnidadesVendidas() + 1);
            drogaRepo.save(droga);
            return true;
        }
        return false;
    }
}
