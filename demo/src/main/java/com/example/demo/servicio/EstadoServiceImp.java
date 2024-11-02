package com.example.demo.servicio;

import org.springframework.stereotype.Service;

import com.example.demo.model.EstadoSalud;

import java.util.Optional;
import java.util.List;

@Service
public class EstadoServiceImp implements EstadoService {

    /* Droga: Peticiones CRUD */

    // Busqueda de todas las drogas
    @Override
    public List<EstadoSalud> searchAllEstados() {
        List<EstadoSalud> estados = List.of(EstadoSalud.SANO, EstadoSalud.ENFERMO, EstadoSalud.OBSERVACION);

        return estados;
    }

    // Busqueda de una droga por ID
    @Override
    public Optional<EstadoSalud> searchEstadoById(Long id) {
        if (id == 1) {
            return Optional.of(EstadoSalud.SANO);
        } else if (id == 2) {
            return Optional.of(EstadoSalud.ENFERMO);
        } else if (id == 3) {
            return Optional.of(EstadoSalud.OBSERVACION);
        } else {
            return Optional.empty();
        }
    }

}
