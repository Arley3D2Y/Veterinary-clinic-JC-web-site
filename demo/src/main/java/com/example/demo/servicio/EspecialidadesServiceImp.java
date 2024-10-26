package com.example.demo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Especialidad;
import com.example.demo.repositorio.EspecialidadRepository;

@Service
public class EspecialidadesServiceImp implements EspecialidadesService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Override
    public List<Especialidad> findAll() {
        return especialidadRepository.findAll();
    }

    @Override
    public Optional<Especialidad> findById(Long id) {
        return especialidadRepository.findById(id);
    }

    @Override
    public Especialidad save(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    @Override
    public void deleteById(Long id) {
        especialidadRepository.deleteById(id);
    }

}
