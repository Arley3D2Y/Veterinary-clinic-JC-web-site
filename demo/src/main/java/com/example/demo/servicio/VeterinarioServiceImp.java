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

    @Override
    public List<Veterinario> searchAllVeterinarios() {
        return veterinarioRepo.findAll();
    }

    @Override
    public Optional<Veterinario> searchVeterinarioById(Long id) {
        return veterinarioRepo.findById(id);
    }

    @Override
    public Optional<Veterinario> addVeterinario(Veterinario veterinario) {
        Optional<Veterinario> veterinarioOpt = veterinarioRepo.findByCedula(veterinario.getCedula());

        if (!veterinarioOpt.isPresent()) {
            veterinario = veterinarioRepo.save(veterinario);
            return Optional.of(veterinario);
        }
        return Optional.empty();
    }

    @Override
    public boolean removeById(Long id) {
        Optional<Veterinario> veterinarioOpt = veterinarioRepo.findById(id);
        if (veterinarioOpt.isPresent()) {
            // Desasociar tratamientos antes de eliminar el veterinario
            for (Tratamiento tratamiento : veterinarioOpt.get().getTratamientos()) {
                tratamiento.setVeterinario(null); // Desasociar el veterinario
            }
            veterinarioRepo.deleteById(id); // Ahora se puede eliminar el veterinario
            return true;
        }
        return false;
    }

    @Override
    public Optional<Veterinario> updateById(Long id, Veterinario veterinario) {
        Optional<Veterinario> veterinarioOpt = veterinarioRepo.findById(id);
        if (veterinarioOpt.isPresent()) {
            veterinario.setTratamientos(veterinarioOpt.get().getTratamientos());
            veterinario.setEspecialidades(veterinarioOpt.get().getEspecialidades());

            veterinario = veterinarioRepo.save(veterinario);
            return Optional.of(veterinario);
        }
        return Optional.empty();
    }

    @Override
    public List<Veterinario> searchByNombre(String nombre) {
        return veterinarioRepo.findByNombreStartingWithIgnoreCase(nombre);
    }

    @Override
    public Optional<Veterinario> searchByCedula(String cedula) {
        return veterinarioRepo.findByCedula(cedula);
    }

    @Override
    public Optional<Veterinario> searchByCorreo(String correo) {
        return veterinarioRepo.findByCorreo(correo);
    }

    @Override
    public long contarVeterinariosActivos() {
        return veterinarioRepo.countByEstadoTrue();
    }

    @Override
    public long contarVeterinariosInactivos() {
        return veterinarioRepo.countByEstadoFalse();
    }

    @Override
    public void actualizarEstadoVeterinario(Long veterinarioId) {
        Optional<Veterinario> veterinarioOpt = veterinarioRepo.findById(veterinarioId);

        if (veterinarioOpt.isPresent()) {
            Veterinario veterinario = veterinarioOpt.get();

            // Consultar si el veterinario tiene algún tratamiento activo
            List<Tratamiento> tratamientosActivos = tratamientoRepo.findByVeterinarioAndActivoTrue(veterinario);

            if (tratamientosActivos.isEmpty()) {
                // Si no tiene tratamientos activos, se considera inactivo
                veterinario.setEstado(false);
            } else {
                // Si tiene al menos un tratamiento activo, se considera activo
                veterinario.setEstado(true);
            }

            // Guardar los cambios en el veterinario
            veterinarioRepo.save(veterinario);
        }
    }


}
