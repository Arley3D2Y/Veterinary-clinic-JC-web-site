package com.example.demo.servicio;

import java.util.Optional;

import java.util.List;
import com.example.demo.model.EstadoSalud;

public interface EstadoService {
    
    public List<EstadoSalud> searchAllEstados();
    
    public Optional<EstadoSalud> searchEstadoById(Long id);

}
