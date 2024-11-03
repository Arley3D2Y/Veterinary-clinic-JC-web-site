package com.example.demo.servicio;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Especialidad;

public interface EspecialidadService {

    public List<Especialidad> searchAllEspecialidades();

    public Optional<Especialidad> searchEspecialidadById(Long id);

    public Optional<Especialidad> addEspecialidad(Especialidad especialidad);

    public boolean removeById(Long identificacion);

    public Optional<Especialidad> updateById(Long id, Especialidad especialidad);

    public Optional<Especialidad> serchEspecialidadByNombre(String nombre);

    public Integer totalEspecalidadades();
}
