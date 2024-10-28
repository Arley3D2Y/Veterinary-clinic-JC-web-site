package com.example.demo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Tratamiento;
import com.example.demo.model.Veterinario;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.repositorio.VeterinarioRepository;

@Service
public class VeterinarioServiceImp implements VeterinarioService {

    @Autowired
    VeterinarioRepository veterinarioRepo;

    @Autowired  
    TratamientoRepository tratamientoRepo;

    /* Veterinarios: Peticiones CRUD */

    // Busqueda de todos los veterinarios
    @Override
    public List<Veterinario> searchAllVeterinarios() {
        return veterinarioRepo.findAll();
    }

    // Busqueda de un veterinario por id
    @Override
    public Optional<Veterinario> searchVeterinarioById(Long id) {
        return veterinarioRepo.findById(id);
    }

    // Creacion de un nuevo veterinario
    @Override
    public Optional<Veterinario> addVeterinario(Veterinario veterinario) {
        Optional<Veterinario> veterinarioOpt = veterinarioRepo.findByCedula(veterinario.getCedula());
        // Se debe buscar especialidades seleccionadas desde el Frontend
        // Se debe crear con la lista de tratamientos vacía

        if (!veterinarioOpt.isPresent()) {
            veterinario = veterinarioRepo.save(veterinario);
            return Optional.of(veterinario);
        }
        return Optional.empty();
    }

    // Método para eliminar un veterinario sin eliminar los tratamientos asociados
    public boolean removeById(Long id) {
        Optional<Veterinario> veterinarioOpt = veterinarioRepo.findById(id);
        if (veterinarioOpt.isPresent()) {
            Veterinario veterinario = veterinarioOpt.get();

            // Desasociar tratamientos antes de eliminar el veterinario
            for (Tratamiento tratamiento : veterinario.getTratamientos()) {
                tratamiento.setVeterinario(null); // Desasociar el veterinario
                tratamientoRepo.save(tratamiento); // Guardar el cambio en cada tratamiento
            }

            veterinarioRepo.deleteById(id); // Ahora se puede eliminar el veterinario
            return true;
        }
        return false;
    }

    // Metodo para actualizar un veterinario
    @Override
    public Optional<Veterinario> updateById(Long id, Veterinario veterinario) {
        Optional<Veterinario> veterinarioOpt = veterinarioRepo.findById(id);
        if (veterinarioOpt.isPresent()) {
            Veterinario v = veterinarioOpt.get();
            veterinario.setId(v.getId());
            veterinario.setTratamientos(v.getTratamientos());

            veterinario = veterinarioRepo.save(veterinario);
            return Optional.of(veterinario);
        }
        return Optional.empty();
    }

    
    /* Busquedas - search by */

    // Busqueda de un veterinario por nombre
    @Override
    public List<Veterinario> searchByNombre(String nombre) {
        return veterinarioRepo.findByNombreStartingWithIgnoreCase(nombre);
    }

    // Busqueda de un veterinario por cedula
    @Override
    public Optional<Veterinario> searchByCedula(String cedula) {
        return veterinarioRepo.findByCedula(cedula);
    }

    // Busqueda de un veterinario por correo
    @Override
    public Optional<Veterinario> searchByCorreo(String correo) {
        return veterinarioRepo.findByCorreo(correo);
    }


    /* buscar listas del veterinario o por entidades */

    // Obtener tratamientos de un veterinario
    @Override
    public List<Tratamiento> getTratamientosVeterinario(Long id) {
        Optional<Veterinario> vetOpt = veterinarioRepo.findById(id);
        if (vetOpt.isPresent()) {
            return vetOpt.get().getTratamientos();
        }
        return null;
    }

    // Métodos no revisados

    @Override
    public long contarVeterinariosActivos() {
        return veterinarioRepo.countByEstadoTrue();
    }

    @Override
    public long contarVeterinariosInactivos() {
        return veterinarioRepo.countByEstadoFalse();
    }


}
