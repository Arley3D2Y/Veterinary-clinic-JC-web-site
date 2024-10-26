package com.example.demo.servicio;

import java.util.Optional;

import java.util.List;
import com.example.demo.model.Especialidad;

public interface EspecialidadesService {
    public List<Especialidad> findAll();

    public Optional<Especialidad> findById(Long id);

    public Especialidad save(Especialidad especialidad);

    public void deleteById(Long id);
}
