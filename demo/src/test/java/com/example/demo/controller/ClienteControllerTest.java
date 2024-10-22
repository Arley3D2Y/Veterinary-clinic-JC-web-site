package com.example.demo.controller;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.demo.controlador.ClienteController;
import com.example.demo.model.Cliente;
import com.example.demo.servicio.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;

// Pruebas unitarias
// Pruebas de integración

@WebMvcTest(controllers = ClienteController.class) 
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class ClienteControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void ClienteController_crearCliente_Cliente() throws Exception {
        Cliente c = new Cliente("Arley", "123445", "a@m.com", "121212", "Dg 46.", "https://www.clarin.com/img/2024/07/04/uteodLeuh_600x600__1.jpg");

        when(clienteService.addCliente(Mockito.any(Cliente.class))).thenReturn(Optional.of(c));
    
        ResultActions response = mockMvc.perform(
            post("/clientes/add")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(c)));

        response.andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(c)))
        .andExpect(jsonPath("$.nombre").value(c.getNombre()));
    }

    @Test
    public void ClienteController_obtenerClientes_Clientes() throws Exception {
        when(clienteService.searchAllClientes()).thenReturn(
            List.of(
                new Cliente("Arley", "123445", "a@m.com", "121212", "Dg 46.", "https://www.clarin.com/img/2024/07/04/uteodLeuh_600x600__1.jpg"),
                new Cliente("Arley", "123445", "a@m.com", "121212", "Dg 46.", "https://www.clarin.com/img/2024/07/04/uteodLeuh_600x600__1.jpg")
            )
        );


        ResultActions response = mockMvc.perform(
            get("/clientes")
        );

        response.andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.size()").value(2));

    }

    @Test
    public void ClienteController_obtenerClientePorId_Cliente() throws Exception {
        when(clienteService.searchClienteById(1L)).thenReturn(Optional.of(
            new Cliente("Arley", "123445", "a@m.com", "121212", "Dg 46.", "https://www.clarin.com/img/2024/07/04/uteodLeuh_600x600__1.jpg")
        ));

        ResultActions response = mockMvc.perform(
            get("/clientes/find/1")
        );

        response.andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.nombre").value("Arley"));
    }

    @Test
    public void ClienteController_actualizarCliente_Cliente() throws Exception {
        // Usar Mockito.eq(1L) en lugar de 1L directamente
        when(clienteService.updateById(Mockito.eq(1L), Mockito.any(Cliente.class))).thenReturn(Optional.of(
            new Cliente("Arley Actualizado", "123445", "a@m.com", "121212", "Dg 46.", "https://www.clarin.com/img/2024/07/04/uteodLeuh_600x600__1.jpg")

        ));
    
        // Asegurarse de que el endpoint coincide con el controlador (incluyendo el {id})
        ResultActions response = mockMvc.perform(
            put("/clientes/update/1") // Añadir el ID en la URL
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(
                    new Cliente("Arley", "123445", "a@m.com", "121212", "Dg 46.", "https://www.clarin.com/img/2024/07/04/uteodLeuh_600x600__1.jpg")

                ))
        );
    
        // Validar que el estado sea OK y el contenido sea JSON
        response.andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.nombre").value("Arley Actualizado"));
    }
    

    @Test
    public void ClienteController_eliminarCliente_Cliente() throws Exception {
        when(clienteService.removeById(1L)).thenReturn(true);


        ResultActions response = mockMvc.perform(
            delete("/clientes/delete/1")
        );

        response.andExpect(status().isNoContent());

    }
}
