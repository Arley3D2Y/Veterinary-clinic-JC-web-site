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
    public boolean addMascota(Long id, Mascota mascota) {
        Optional<Cliente> cliente = clienteRepo.findById(id);
        if (cliente.isPresent()) {
            cliente.get().guardarMascota(mascota);
            mascotaRep.save(mascota);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean removeById(Long id) {
        Optional<Mascota> mascota = mascotaRep.findById(id);
        if (mascota.isPresent()) {
            mascotaRep.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateById(Long id, Mascota mascota) {
        Optional<Mascota> mascotaOpt = mascotaRep.findById(id);
        if (mascotaOpt.isPresent()) {
            mascotaRep.save(mascota);  // Save es usado tanto para crear como para actualizar
            return true;
        }
        return false;
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
