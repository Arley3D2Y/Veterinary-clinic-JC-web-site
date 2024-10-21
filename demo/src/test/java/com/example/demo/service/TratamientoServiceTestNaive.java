package com.example.demo.service;

// Ejecutar una instancia de la aplicación
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Tratamiento;
import com.example.demo.servicio.TratamientoService;

import java.time.LocalDate;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@SpringBootTest
class TratamientoServiceTestNaive {

    @Autowired
    private TratamientoService tratamientoService;

    @Test
    public void TratamientoService_createTratamiento_Tratamiento() {
        // arrange
        Tratamiento t = new Tratamiento("Cirugía menor", "Procedimientos quirúrgicos menores", LocalDate.of(2023, 9, 8));

        // act
        Optional<Tratamiento> newTratamiento = tratamientoService.addTratamiento(1l, 1l, t);

        // assert
        Assertions.assertThat(newTratamiento).isPresent();
    }

}