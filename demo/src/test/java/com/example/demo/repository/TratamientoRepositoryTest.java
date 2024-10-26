package com.example.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.model.Mascota;
import com.example.demo.model.Tratamiento;
import com.example.demo.model.Veterinario;
import com.example.demo.repositorio.TratamientoRepository;

@RunWith(MockitoJUnitRunner.class)
public class TratamientoRepositoryTest {

    @Mock
    private TratamientoRepository tratamientoRepository;

    private Veterinario veterinario;
    private Mascota mascota;
    private Tratamiento tratamientoActivo;
    private Tratamiento tratamientoInactivo;

    @Before
    public void setUp() {
        
        // Datos de prueba
        veterinario = new Veterinario();
        veterinario.setNombre("Dr. Kevin");

        mascota = new Mascota();
        mascota.setNombre("Firulais");

        // Tratamiento activo
        tratamientoActivo = new Tratamiento();
        tratamientoActivo.setDescripcion("Tratamiento activo para Firulais");
        tratamientoActivo.setVeterinario(veterinario);
        tratamientoActivo.setMascota(mascota);
        tratamientoActivo.setFechaInicio(LocalDate.of(2023, 1, 1));
        tratamientoActivo.setFechaFin(LocalDate.of(2024, 12, 31));
        tratamientoActivo.actualizarEstado();  // Este tratamiento debería ser activo

        // Tratamiento inactivo
        tratamientoInactivo = new Tratamiento();
        tratamientoInactivo.setDescripcion("Tratamiento inactivo para Firulais");
        tratamientoInactivo.setVeterinario(veterinario);
        tratamientoInactivo.setMascota(mascota);
        tratamientoInactivo.setFechaInicio(LocalDate.of(2022, 1, 1));
        tratamientoInactivo.setFechaFin(LocalDate.of(2022, 12, 31));
        tratamientoInactivo.actualizarEstado();  // Este tratamiento debería ser inactivo
    }

    @Test
    public void testFindByDescripcionStartingWithIgnoreCase() {
        String descripcion = "Tratamiento";
        List<Tratamiento> tratamientos = Arrays.asList(tratamientoActivo);
        
        when(tratamientoRepository.findByDescripcionStartingWithIgnoreCase(descripcion))
            .thenReturn(tratamientos);

        List<Tratamiento> result = tratamientoRepository.findByDescripcionStartingWithIgnoreCase(descripcion);
        
        assertEquals(1, result.size());
        assertEquals("Tratamiento activo para Firulais", result.get(0).getDescripcion());
    }

    @Test
    public void testFindByVeterinario() {
        List<Tratamiento> tratamientos = Arrays.asList(tratamientoActivo);
        
        when(tratamientoRepository.findByVeterinario(veterinario))
            .thenReturn(tratamientos);

        List<Tratamiento> result = tratamientoRepository.findByVeterinario(veterinario);
        
        assertEquals(1, result.size());
        assertEquals(veterinario.getNombre(), result.get(0).getVeterinario().getNombre());
    }

    @Test
    public void testFindByFechaInicio() {
        LocalDate fechaInicio = LocalDate.of(2023, 1, 1);
        List<Tratamiento> tratamientos = Arrays.asList(tratamientoActivo);
        
        when(tratamientoRepository.findByFechaInicio(fechaInicio))
            .thenReturn(tratamientos);

        List<Tratamiento> result = tratamientoRepository.findByFechaInicio(fechaInicio);
        
        assertEquals(1, result.size());
        assertEquals(fechaInicio, result.get(0).getFechaInicio());
    }

    @Test
    public void testFindByActivoTrue() {
        List<Tratamiento> tratamientos = Arrays.asList(tratamientoActivo);
        
        when(tratamientoRepository.findByActivoTrue())
            .thenReturn(tratamientos);

        List<Tratamiento> result = tratamientoRepository.findByActivoTrue();
        
        assertEquals(1, result.size());
        assertEquals(true, result.get(0).isActivo());
    }

    @Test
    public void testFindByActivoFalse() {
        List<Tratamiento> tratamientos = Arrays.asList(tratamientoInactivo);
        
        when(tratamientoRepository.findByActivoFalse())
            .thenReturn(tratamientos);

        List<Tratamiento> result = tratamientoRepository.findByActivoFalse();
        
        assertEquals(1, result.size());
        assertEquals(false, result.get(0).isActivo());
    }

    @Test
    public void testFindByVeterinarioAndActivoTrue() {
        List<Tratamiento> tratamientos = Arrays.asList(tratamientoActivo);
        
        when(tratamientoRepository.findByVeterinarioAndActivoTrue(veterinario)).thenReturn(tratamientos);

        List<Tratamiento> result = tratamientoRepository.findByVeterinarioAndActivoTrue(veterinario);
        
        assertEquals(1, result.size());
        assertEquals(veterinario.getNombre(), result.get(0).getVeterinario().getNombre());
        assertEquals(true, result.get(0).isActivo());
    }

    @Test
    public void testFindByFechaInicioBetween() {
        LocalDate fechaInicio = LocalDate.of(2023, 1, 1);
        LocalDate fechaFin = LocalDate.of(2024, 12, 31);
        List<Tratamiento> tratamientos = Arrays.asList(tratamientoActivo);
        
        when(tratamientoRepository.findByFechaInicioBetween(fechaInicio, fechaFin))
            .thenReturn(tratamientos);

        List<Tratamiento> result = tratamientoRepository.findByFechaInicioBetween(fechaInicio, fechaFin);
        
        assertEquals(1, result.size());
        assertEquals(fechaInicio, result.get(0).getFechaInicio());
    }
}
