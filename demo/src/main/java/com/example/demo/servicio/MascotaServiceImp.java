package com.example.demo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cliente;
import com.example.demo.model.Mascota;
import com.example.demo.repositorio.ClienteRepository;
import com.example.demo.repositorio.MascotaRepository;

@Service
public class MascotaServiceImp implements MascotaService {

    @Autowired
    MascotaRepository mascotaRep;

    @Autowired
    ClienteRepository clienteRepo;

    @Override
    public List<Mascota> searchAllMascotas() {
        return mascotaRep.findAll();
    }

    @Override
    public Optional<Mascota> searchMascotaById(Long id) {
        return mascotaRep.findById(id);
    }

    @Override
    public Optional<Mascota> addMascota(Long id, Mascota mascota) {
        Optional<Cliente> clienteOpt = clienteRepo.findById(id);
        
        if (clienteOpt.isPresent()) {
            Cliente c = clienteOpt.get();
            mascota = mascotaRep.save(mascota);
            c.guardarMascota(mascota);
            clienteRepo.save(c);
            return Optional.of(mascota);
        }
        return Optional.empty();
    }
    
    @Override
    public boolean removeById(Long id) {
        if (mascotaRep.existsById(id)) {
            mascotaRep.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Mascota> updateById(Long id, Mascota mascota) {
        if (mascotaRep.existsById(id)) {
            mascota = mascotaRep.save(mascota);  // Save es usado tanto para crear como para actualizar
            return Optional.of(mascota);
        }
        return Optional.empty();
    }

    @Override
    public List<Mascota> searchByNombre(String nombre) {
        return mascotaRep.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<Mascota> searchByClienteId(Long id) {
        return mascotaRep.findByClienteId(id);
    }
}
