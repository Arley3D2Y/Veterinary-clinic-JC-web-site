package com.example.demo.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Mascota;
import com.example.demo.repositorio.MascotaRepository;

@Service
public class MascotaServiceImpl implements MascotaService {

    @Autowired
    MascotaRepository MascotaRep;

    @Override
    public Mascota SearchById(Long id) {
        return MascotaRep.findById(id).orElse(null);
    }

    @Override
    public List<Mascota> SearchAll() {
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

    @Override
    public List<Mascota> buscarPorNombre(String nombre) {
        return MascotaRep.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<Mascota> buscarPorClienteId(Long id) {
        return MascotaRep.findByClienteId(id);
    }
}
