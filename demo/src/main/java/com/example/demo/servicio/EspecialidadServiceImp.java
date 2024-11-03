package com.example.demo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Especialidad;
import com.example.demo.repositorio.EspecialidadRepository;

@Service
public class EspecialidadServiceImp implements EspecialidadService {

    @Autowired
    EspecialidadRepository especialidadRepo;

    // CRUD
    @Override
    public Optional<Especialidad> addEspecialidad(Especialidad especialidad) {
        Optional<Especialidad> espOptional = especialidadRepo.findById(especialidad.getId());
        if (!espOptional.isPresent()) {
            especialidad = especialidadRepo.save(especialidad);
            return Optional.of(especialidad);
        }
        return Optional.empty();
    }

    @Override
    public boolean removeById(Long identificacion) {
        if (especialidadRepo.existsById(identificacion)) {
            especialidadRepo.deleteById(identificacion);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Especialidad> updateById(Long id, Especialidad especialidad) {
        Optional<Especialidad> especialidadOpt = especialidadRepo.findById(id);
        if (especialidadOpt.isPresent()) {
            Especialidad e = especialidadOpt.get();
            especialidad.setId(e.getId()); // Asegura que el ID no cambie
            return Optional.of(especialidadRepo.save(especialidad));
        }
        return Optional.empty();
    }

    // Buscar
    @Override
    public List<Especialidad> searchAllEspecialidades() {
        return especialidadRepo.findAll();
    }

    @Override
    public Optional<Especialidad> searchEspecialidadById(Long id) {
        return especialidadRepo.findById(id);
    }

    @Override
    public Optional<Especialidad> serchEspecialidadByNombre(String nombre) {
        return especialidadRepo.findByNombreIgnoreCase(nombre);
    }

    // Querys
    @Override
    public Integer totalEspecalidadades() {
        return especialidadRepo.countTotalEspecialidades();
    }

}
