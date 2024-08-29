package com.example.demo.servicio;

import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Mascota;
import com.example.demo.repositorio.MascotaRepository;

@Service
public class MascotaServiceImpl implements MascotaService {

    @Autowired
    MascotaRepository MascotaRep;

    @Override
    public Optional<Mascota> SearchById(Long id) {
        return MascotaRep.findById(id);
    }

    @Override
    public Collection<Mascota> SearchAll() {
        return MascotaRep.findAll();
    }

    @Override
    public void addMascota(Mascota mascota) {
        MascotaRep.save(mascota);
    }

    @Override
    public void deleteById(Long id) {
        MascotaRep.deleteById(id);
    }

    @Override
    public void update(Mascota mascota) {
        MascotaRep.save(mascota);
    }
}
