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
import com.example.demo.repositorio.EnfermedadRepository;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.TratamientoRepository;

@Service
public class MascotaServiceImp implements MascotaService {

    @Autowired
    MascotaRepository mascotaRep;
    @Autowired
    ClienteRepository clienteRepo;
    @Autowired
    TratamientoRepository tratamientoRepo;
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
    public Optional<Mascota> addMascota(Long idC, Long idE, Mascota mascota) {
        Optional<Cliente> clienteOpt = clienteRepo.findById(idC);
        Optional<Enfermedad> enfermedadOpt = enfermedadRepo.findById(idE);

        if (clienteOpt.isPresent() && enfermedadOpt.isPresent() && mascota.getEstado().equals(EstadoSalud.ENFERMO)) {
            Cliente cliente = clienteOpt.get();
            mascota.setCliente(cliente);
            Enfermedad enfermedad =  enfermedadOpt.get();
            mascota.setEnfermedad(enfermedad);

            mascota = mascotaRep.save(mascota); // Save es usado tanto para crear como para actualizar
            return Optional.of(mascota);
        }
        return Optional.empty();
    }

    // Eliminacion de una mascotas
    @Override
    public boolean removeById(Long id) {
        Optional<Mascota> mascotaOpt = mascotaRep.findById(id);

        if (mascotaOpt.isPresent()) {
            Mascota mascota = mascotaOpt.get();

            for (Tratamiento tratamiento : mascota.getTratamientos()) {
                tratamiento.setMascota(null); // Desasociar el veterinario
                tratamientoRepo.save(tratamiento); // Guardar el cambio en cada tratamiento
            }

            mascotaRep.deleteById(id); // Ahora se puede eliminar la mascota
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
            mascota = mascotaRep.save(mascota); // Save es usado tanto para crear como para actualizar
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

    // Obtener tratamientos de una mascota
    @Override
    public List<Tratamiento> getTratamientosMascotas(Long id) {
        Optional<Mascota> mascotaOpt = mascotaRep.findById(id);
        if (mascotaOpt.isPresent()) {
            return mascotaOpt.get().getTratamientos();
        }
        return List.of();
    }

    // Busqueda de mascotas de un cliente
    @Override
    public List<Mascota> searchByClienteId(Long id) {
        return mascotaRep.findByClienteId(id);
    }

}
