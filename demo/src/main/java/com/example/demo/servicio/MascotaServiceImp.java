package com.example.demo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cliente;
import com.example.demo.model.Enfermedad;
import com.example.demo.model.EstadoSalud;
import com.example.demo.model.Mascota;
import com.example.demo.model.Tratamiento;
import com.example.demo.repositorio.ClienteRepository;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.EnfermedadRepository;

@Service
public class MascotaServiceImp implements MascotaService {

    @Autowired
    MascotaRepository mascotaRep;
    @Autowired
    ClienteRepository clienteRepo;
    @Autowired
    EnfermedadRepository enfermedadRepo;

    /* Mascotas: Peticiones CRUD */

    // Busqueda de todas las mascotas
    @Override
    public List<Mascota> searchAllMascotas() {
        return mascotaRep.findAll();
    }

    // Busqueda de una mascota por id
    @Override
    public Optional<Mascota> searchMascotaById(Long id) {
        return mascotaRep.findById(id);
    }

    // Creacion de una nueva mascota
@Override
public Optional<Mascota> addMascota(Long id, Mascota mascota) {
    Optional<Cliente> clienteOpt = clienteRepo.findById(id);
    
    // Suponiendo que tienes métodos para encontrar Estado y Enfermedad
    EstadoSalud estado = mascota.getEstado();
    Optional<Enfermedad> enfermedadOpt = enfermedadRepo.findById(mascota.getEnfermedad().getId());

    if (clienteOpt.isPresent() && estado.equals(EstadoSalud.ENFERMO) && enfermedadOpt.isPresent()) {
        Cliente cliente = clienteOpt.get();
        
        // Asocia la enfermedad y el estado a la mascota
        mascota.setEstado(EstadoSalud.ENFERMO);
        mascota.setEnfermedad(enfermedadOpt.get());

        // Asocia la mascota al cliente
        mascota.setCliente(cliente);
        mascota = mascotaRep.save(mascota);  // Save es usado tanto para crear como para actualizar
        return Optional.of(mascota);
    }
    return Optional.empty();
}
    
    // Eliminacion de una mascotas
    @Override
    public boolean removeById(Long id) {
        if (mascotaRep.existsById(id)) {
            mascotaRep.deleteById(id);
            return true;
        }
        return false;
    }

    // Actualizacion de una mascota
    @Override
    public Optional<Mascota> updateById(Long id, Mascota mascota) {
        Optional<Mascota> mascotaOpt = mascotaRep.findById(id);
        if (mascotaOpt.isPresent()) {
            Mascota m = mascotaOpt.get();
            mascota.setId(m.getId());
            mascota.setCliente(m.getCliente());
            mascota.setTratamientos(m.getTratamientos());
            mascota = mascotaRep.save(mascota);  // Save es usado tanto para crear como para actualizar
            return Optional.of(mascota);
        }
        return Optional.empty();
    }

    /* Busquedas - search by */

    // Busqueda de mascotas por nombre
    @Override
    public List<Mascota> searchByNombre(String nombre) {
        return mascotaRep.findByNombreStartingWithIgnoreCase(nombre);
    }


    /* Buscar listas del veterinario o por entidades */

    // Busqueda de mascotas de un cliente
    @Override
    public List<Mascota> searchByClienteId(Long id) {
        return mascotaRep.findByClienteId(id);
    }

   // Obtener tratamientos de una mascota
   @Override
   public List<Tratamiento> getTratamientosMascotas(Long id) {
       Optional<Mascota> mascotaOpt = mascotaRep.findById(id);
       if (mascotaOpt.isPresent()) {
           return mascotaOpt.get().getTratamientos();
       }
       return null;
   }


   
    // Métodos no revisados

    @Override
    public Number countMascotas() {
        return 0;
    }

    @Override
    public Number countMascotasEnTratamiento() {
        return 0;
    }

    
}
