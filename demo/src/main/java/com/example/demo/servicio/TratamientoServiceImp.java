package com.example.demo.servicio;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.GenericoDTO;
import com.example.demo.model.Droga;
import com.example.demo.model.Mascota;
import com.example.demo.model.Tratamiento;
import com.example.demo.model.Veterinario;
import com.example.demo.repositorio.DrogaRepository;
import com.example.demo.repositorio.EstadoRepository;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.repositorio.VeterinarioRepository;

@Service
public class TratamientoServiceImp implements TratamientoService {

    @Autowired
    TratamientoRepository tratamientoRep;
    @Autowired
    MascotaRepository mascotaRep;
    @Autowired
    VeterinarioRepository veterinarioRepo;
    @Autowired
    DrogaRepository drogaRep;
    @Autowired
    EstadoRepository estadoRep;

    /* Tratamiento: Peticiones CRUD */

    // Busqueda de todos los tratamientos
    @Override
    public List<Tratamiento> searchAll() {
        return tratamientoRep.findAll();
    }

    // Busqueda de un tratamiento por id
    @Override
    public Optional<Tratamiento> searchById(Long id) {
        return tratamientoRep.findById(id);
    }

    // Creacion de un nuevo tratamiento
    @Override
    public Optional<Tratamiento> addTratamiento(Long idp, Long idv, Long idd, Tratamiento tratamiento) {
        Optional<Mascota> mascota = mascotaRep.findById(idp);
        Optional<Veterinario> veterinario = veterinarioRepo.findById(idv);
        Optional<Droga> droga = drogaRep.findById(idd);
        // Aquí se podría pasar el id de la droga tambien, esto desde la lista despegable

        if (mascota.isPresent() && veterinario.isPresent() && droga.isPresent()) {
            if (mascota.get().getEstado().getDescripcion().equals("Enfermo")) {
                if (veterinario.get().getEstado()) {
                    if (droga.get().getUnidadesDisponibles() > 0) {
                        veterinario.get().agregarTratamiento(tratamiento);
                        droga.get().agregarTratamiento(tratamiento);
                        mascota.get().agregarTratamiento(tratamiento, estadoRep.findById(3L).get());
                        return Optional.of(tratamientoRep.save(tratamiento));
                    }
                }
            }
        }
        return Optional.empty();
    }

    // Eliminacion de un tratamiento
    @Override
    public boolean removeById(Long id) {
        if (tratamientoRep.existsById(id)) {
            tratamientoRep.deleteById(id);
            return true;
        }
        return false;
    }

    // Actualizacion de un tratamiento
    @Override
    public Optional<Tratamiento> updateById(Long id, Tratamiento tratamiento) {
        Optional<Tratamiento> tratOpt = tratamientoRep.findById(id);
        if (tratOpt.isPresent()) {
            tratamiento.setMascota(tratOpt.get().getMascota());
            tratamiento.setVeterinario(tratOpt.get().getVeterinario());
            tratamiento.setDroga(tratOpt.get().getDroga());
            tratamiento.actualizarEstado();
            tratamiento = tratamientoRep.save(tratamiento);
            return Optional.of(tratamiento);
        }
        return Optional.empty();
    }


    /* Busquedas - search by */

    // Busqueda de tratamientos por nombre
    @Override
    public List<Tratamiento> searchByDescription(String description) {
        List<Tratamiento> tratamientos = tratamientoRep.findByDescripcionStartingWithIgnoreCase(description);
        return tratamientos;
    }


    /* Buscar listas del tratamiento o por entidades */

    // Busqueda de tratamientos de un veterinario
    @Override
    public List<Tratamiento> searchByVeterinarioId(Long id) {
        return tratamientoRep.findByVeterinarioId(id);
    }

    // Busqueda de tratamientos de una mascota
    @Override
    public List<Tratamiento> searchByMascotaId(Long id) {
        return tratamientoRep.findByMascotaId(id);
    }

    // Busqueda de tratamientos por droga
    @Override
    public List<Tratamiento> searchByDrogaId(Long id) {
        return tratamientoRep.findByDrogaId(id);
    }

    /* Aún no revisado */




    @Override
    public Number countTratamientosByMonth() {
        LocalDate fechaActual = LocalDate.now();
        int mesActual = fechaActual.getMonthValue(); // Obtener el mes actual

        // Buscar tratamientos por mes
        List<Tratamiento> tratamientosPorMes = tratamientoRep.findByFechaInicioBetween(
                LocalDate.of(fechaActual.getYear(), mesActual, 1),
                LocalDate.of(fechaActual.getYear(), mesActual + 1, 1));
        return tratamientosPorMes.size();
    }

    @Override
    public List<GenericoDTO> getTratamientosPorMedicamento() {
        List<Tratamiento> tratamientos = tratamientoRep.findAll();
        Map<String, Number> drogaCountMap = new HashMap<>();

        // Contar ocurrencias de cada droga
        for (Tratamiento tratamiento : tratamientos) {
            Droga droga = tratamiento.getDroga();
            if (droga != null) {
                String nombreDroga = droga.getNombre(); // Asegúrate de que 'Droga' tenga un método 'getNombre()'
                // Incrementar el conteo
                drogaCountMap.put(nombreDroga, drogaCountMap.getOrDefault(nombreDroga, 0).intValue() + 1);
            }
        }

        // Convertir el mapa a una lista de GenericoDTO
        List<GenericoDTO> result = new ArrayList<>();
        for (Map.Entry<String, Number> entry : drogaCountMap.entrySet()) {
            result.add(new GenericoDTO(entry.getKey(), entry.getValue()));
        }

        return result;
    }

    // Contar ocurrencias de cada veterinario

    @Override
    public List<Tratamiento> getTopTratamientos() {
        return List.of();
    }

}
