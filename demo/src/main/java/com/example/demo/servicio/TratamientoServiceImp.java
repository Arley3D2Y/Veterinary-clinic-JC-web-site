package com.example.demo.servicio;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Droga;
import com.example.demo.model.FinanzasDTO;
import com.example.demo.model.GenericoDTO;
import com.example.demo.model.Mascota;
import com.example.demo.model.Tratamiento;
import com.example.demo.model.Veterinario;
import com.example.demo.repositorio.DrogaRepository;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.repositorio.VeterinarioRepository;

@Service
public class TratamientoServiceImp implements TratamientoService {

    @Autowired
    TratamientoRepository trataRepo;
    @Autowired
    MascotaRepository petRep;
    @Autowired
    VeterinarioRepository vetRep;
    @Autowired
    DrogaRepository drogaRep;

    @Override
    public List<Tratamiento> searchAll() {
        return trataRepo.findAll();
    }

    // implementacion de los metodos
    @Override
    public Optional<Tratamiento> searchById(Long id) {
        return trataRepo.findById(id);
    }

    @Override
    public Optional<Tratamiento> addTratamiento(Long idp, Long idv, Tratamiento tratamiento) {
        Optional<Mascota> pet = petRep.findById(idp);
        Optional<Veterinario> vet = vetRep.findById(idv);
        Optional<Droga> droga = drogaRep.findById(tratamiento.getDroga().getId());

        if (pet.isPresent() && vet.isPresent() && droga.isPresent()) {
            tratamiento.setMascota(pet.get());
            tratamiento.setVeterinario(vet.get());
            tratamiento = trataRepo.save(tratamiento);
            return Optional.of(tratamiento);
        }
        return Optional.empty();
    }

    @Override
    public boolean removeById(Long id) {
        if (trataRepo.existsById(id)) {
            trataRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Tratamiento> updateById(Long id, Tratamiento tratamiento) {
        Optional<Tratamiento> tratOpt = trataRepo.findById(id);
        if (tratOpt.isPresent()) {
            tratamiento.setMascota(tratOpt.get().getMascota());
            tratamiento.setVeterinario(tratOpt.get().getVeterinario());
            tratamiento.setDroga(tratOpt.get().getDroga());
            
            tratamiento = trataRepo.save(tratamiento);
            return Optional.of(tratamiento);
        }
        return Optional.empty();
    }

    @Override
    public List<Tratamiento> searchByNombre(String name) {
        List<Tratamiento> tratamientos = trataRepo.findByDescripcionStartingWithIgnoreCase(name);
        return tratamientos;
    }

    @Override
    public List<Tratamiento> getTratamientosPorVeterinario(Long id) {
        Optional<Veterinario> vet = vetRep.findById(id);
        if (vet.isPresent()) {
            List<Tratamiento> tratamientos = trataRepo.findByVeterinario(vet.get());
            return tratamientos;
        } else {
            return List.of();
        }
    }

    @Override
    public List<Tratamiento> getTratamientosPorMascota(Long id) {
        Optional<Mascota> pet = petRep.findById(id);
        if (pet.isPresent()) {
            List<Tratamiento> tratamientos = trataRepo.findByMascota(pet.get());
            return tratamientos;
        } else {
            return List.of();
        }
    }





    @Override
    public Number countTratamientosByMonth() {
        LocalDate fechaActual = LocalDate.now();
        int mesActual = fechaActual.getMonthValue(); // Obtener el mes actual

        // Buscar tratamientos por mes
        List<Tratamiento> tratamientosPorMes = trataRepo.findByFechaInicioBetween(
                LocalDate.of(fechaActual.getYear(), mesActual, 1),
                LocalDate.of(fechaActual.getYear(), mesActual + 1, 1)
        );
        return tratamientosPorMes.size();
    }

@Override
public List<GenericoDTO> getTratamientosPorMedicamento() {
    List<Tratamiento> tratamientos = trataRepo.findAll();
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

@Override
public FinanzasDTO getFinanzas() {
    List<Tratamiento> tratamientos = trataRepo.findAll();

    double ventasTotales = 0.0;
    double costoTotal = 0.0;

    for (Tratamiento tratamiento : tratamientos) {
        Droga droga = tratamiento.getDroga();
        if (droga != null) {
            double precioDroga = droga.getPrecioVenta();
            ventasTotales += precioDroga;
            costoTotal += precioDroga; // Si el costo es diferente, ajustarlo aquí
        }
    }

    double gananciasTotales = ventasTotales - costoTotal;

    // Retornar el DTO con las ventas y ganancias
    return new FinanzasDTO(ventasTotales, gananciasTotales);
}

        // Contar ocurrencias de cada veterinario

    @Override
    public List<Tratamiento> getTopTratamientos() {
        return List.of();
    }


}
