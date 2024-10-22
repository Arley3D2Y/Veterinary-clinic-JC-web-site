package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.model.Droga;
import com.example.demo.model.Mascota;
import com.example.demo.model.Tratamiento;
import com.example.demo.model.Veterinario;
import com.example.demo.repositorio.DrogaRepository;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.repositorio.VeterinarioRepository;
import com.example.demo.servicio.TratamientoServiceImp;
import com.example.demo.servicio.VeterinarioService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
class TratamientoServiceTestNaive {

    @Mock
    private TratamientoRepository tratamientoRepo;

    @Mock
    private MascotaRepository mascotaRepo;

    @Mock
    private VeterinarioRepository veterinarioRepo;

    @Mock
    private DrogaRepository drogaRepo;

    @Mock
    private VeterinarioService veterinarioService;

    @InjectMocks
    private TratamientoServiceImp tratamientoService; // Usar la implementación concreta

    private Mascota testMascota;
    private Veterinario testVeterinario;
    private Tratamiento nuevoTratamiento;

    @BeforeEach
    public void setUp() {
        // Inicializamos los mocks y el servicio
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void TratamientoService_createTratamiento_Tratamiento() {
        // arrange
        nuevoTratamiento = new Tratamiento("Cirugía menor", "Procedimientos quirúrgicos menores", LocalDate.of(2023, 9, 8));

        // act: Crear el tratamiento utilizando el servicio
        Optional<Tratamiento> newTratamiento = tratamientoService.addTratamiento(testMascota.getId(), testVeterinario.getId(), nuevoTratamiento);

        // assert: Verificar que el nuevo tratamiento fue creado correctamente
        Assertions.assertThat(newTratamiento).isPresent();
    }

    // Prueba para el método addTratamiento
    @Test
    public void TratamientoService_addTratamiento_Tratamiento() {
        // Datos de entrada
        Long idMascota = 1L;
        Long idVeterinario = 1L;
        Tratamiento tratamiento = new Tratamiento();
        Droga droga = new Droga();
        droga.setId(1L);

        tratamiento.setDroga(droga);

        // Mocks de datos existentes
        Mascota mockMascota = new Mascota();
        Veterinario mockVeterinario = new Veterinario();

        // Simulamos la respuesta de los repositorios
        when(mascotaRepo.findById(idMascota)).thenReturn(Optional.of(mockMascota));
        when(veterinarioRepo.findById(idVeterinario)).thenReturn(Optional.of(mockVeterinario));
        when(drogaRepo.findById(1L)).thenReturn(Optional.of(droga));
        when(tratamientoRepo.save(any(Tratamiento.class))).thenReturn(tratamiento);

        // Llamada al servicio que estamos probando
        Optional<Tratamiento> resultado = tratamientoService.addTratamiento(idMascota, idVeterinario, tratamiento);

        // Verificación de que el resultado es correcto
        assertTrue(resultado.isPresent());
        assertEquals(tratamiento, resultado.get());

        // Verificar que los métodos en los mocks fueron llamados
        verify(mascotaRepo).findById(idMascota);
        verify(veterinarioRepo).findById(idVeterinario);
        verify(drogaRepo).findById(1L);
        verify(tratamientoRepo).save(tratamiento);
    }

    // Prueba para el método removeById
    @Test
    public void testRemoveById() {
        Long idTratamiento = 1L;

        // Simulamos que el tratamiento existe
        when(tratamientoRepo.existsById(idTratamiento)).thenReturn(true);

        // Llamada al servicio que estamos probando
        boolean resultado = tratamientoService.removeById(idTratamiento);

        // Verificamos que el tratamiento fue eliminado correctamente
        assertTrue(resultado);

        // Verificar que se llamó al método deleteById en el repositorio
        verify(tratamientoRepo).deleteById(idTratamiento);
    }

    // Prueba para el método updateById
    @Test
    void testUpdateById() {
        Tratamiento tratamientoExistente = new Tratamiento();
        tratamientoExistente.setId(1L);
        tratamientoExistente.setVeterinario(new Veterinario());
        tratamientoExistente.getVeterinario().setId(1L);
        tratamientoExistente.setMascota(new Mascota());
        tratamientoExistente.setDroga(new Droga());
        
        
        Tratamiento tratamientoNuevo = new Tratamiento();
        tratamientoNuevo.setId(1L); // El ID debe ser el mismo que el existente
        tratamientoNuevo.setVeterinario(tratamientoExistente.getVeterinario());
        tratamientoNuevo.setMascota(tratamientoExistente.getMascota());
        tratamientoNuevo.setDroga(tratamientoExistente.getDroga());
        
       
        when(tratamientoRepo.findById(1L)).thenReturn(Optional.of(tratamientoExistente));
        when(tratamientoRepo.save(any(Tratamiento.class))).thenReturn(tratamientoNuevo);
    
        Optional<Tratamiento> resultado = tratamientoService.updateById(1L, tratamientoNuevo);
    
        verify(tratamientoRepo).findById(1L);
        verify(tratamientoRepo).save(tratamientoNuevo);
    
        // Verifica que el resultado sea el esperado
        assertTrue(resultado.isPresent());
        assertEquals(tratamientoNuevo, resultado.get());
    }
    

}