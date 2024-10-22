package com.example.demo.service;

// Ejecutar una instancia de la aplicación
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Droga;
import com.example.demo.model.Mascota;
import com.example.demo.model.Tratamiento;
import com.example.demo.model.Veterinario;
import com.example.demo.repositorio.DrogaRepository;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.repositorio.VeterinarioRepository;
import com.example.demo.servicio.TratamientoService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
class TratamientoServiceTestNaive {

    @Autowired
    private TratamientoService tratamientoService;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private DrogaRepository drogaRepository;

    @Autowired
    private TratamientoRepository tratamientoRepository;

    private Mascota testMascota;
    private Veterinario testVeterinario;
    private Droga testDroga;
    private Tratamiento testTratamiento;


    @BeforeEach
    public void init() {
        // Crear datos de prueba
        testVeterinario = new Veterinario("Arley", "11111", "a@lol.com", "1234", "url_imagen");
        testMascota = new Mascota("Ginger", "5", "4.7", "Sano", "Ninguna", "Macho", "Burmés", "url_imagen_gato");
        testDroga = new Droga("Cefalexina", 3.54f, 23.4f, 12, 1);
        testTratamiento = new Tratamiento("Medicamento antiinflamatorio", "Reducción de inflamación y dolor", LocalDate.of(2023, 9, 1));

        // Guardar datos en los repositorios
        veterinarioRepository.save(testVeterinario);
        mascotaRepository.save(testMascota);
        drogaRepository.save(testDroga);

        // Asignar relaciones a Tratamiento
        testTratamiento.setVeterinario(testVeterinario);
        testTratamiento.setMascota(testMascota);
        testTratamiento.setDroga(testDroga);

        // Guardar tratamiento en el repositorio
        tratamientoRepository.save(testTratamiento);
    }

    @Test
    public void TratamientoService_createTratamiento_Tratamiento() {
        // arrange: Configurar un nuevo Tratamiento
        Tratamiento nuevoTratamiento = new Tratamiento("Nueva medicación", "Tratamiento para el dolor", LocalDate.of(2024, 10, 20));
        nuevoTratamiento.setDroga(testDroga);

        // act: Crear el tratamiento utilizando el servicio
        Optional<Tratamiento> newTratamiento = tratamientoService.addTratamiento(testMascota.getId(), testVeterinario.getId(), nuevoTratamiento);

        // assert: Verificar que el nuevo tratamiento fue creado correctamente
        Assertions.assertThat(newTratamiento).isPresent();
        Assertions.assertThat(newTratamiento.get().getMascota().getNombre()).isEqualTo("Ginger");
    }

    @Test
    public void TratamientoService_findAll_TratamientoList() {
        // arrange

        // act: Buscar todos los tratamientos
        List<Tratamiento> tratamientos = tratamientoService.searchAll();

        // assert: Verificar que hay 1 tratamiento en la lista
        Assertions.assertThat(tratamientos).isNotEmpty();
        Assertions.assertThat(tratamientos.size()).isEqualTo(1);
    }

}