package com.example.demo.servicio;

import java.util.Optional;

import java.util.List;
import com.example.demo.model.Estado;

public interface EstadoService {
    
    public List<Estado> searchAllEstados();
    
    public Optional<Estado> searchEstadoById(Long id);

}
