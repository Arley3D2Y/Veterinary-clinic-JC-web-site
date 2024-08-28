package com.example.demo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Cliente;
import com.example.demo.model.Mascota;
import com.example.demo.servicio.ClienteService;
import com.example.demo.servicio.MascotaService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Controller
@RequestMapping("/mascota")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private ClienteService clienteService;

    // localhost:8091/mascota
    @GetMapping
    public String mostrarMascotas(Model model) {
        model.addAttribute("mascotas", mascotaService.SearchAll());
        return "mostrar_todas_mascotas";
    }

    // localhost:8091/mascota/find/{id}
    @GetMapping("/find/{id}")
    public String mostrarInfoMascota(Model model, @PathVariable("id") Long id) {
        Optional<Mascota> mascotaOpt = mascotaService.SearchById(id);
        if (mascotaOpt.isPresent()) {
            model.addAttribute("mascota", mascotaOpt.get());
            return "datos_mascotas";
        } else {
            // Manejar el caso cuando no se encuentra la mascota
            return "error_pagina"; // Cambiar por la página de error correspondiente
        }
    }

    // localhost:8091/mascota/add/{id}
    @GetMapping("/add/{id}")
    public String mostrarFormularioCrear(Model model, @PathVariable("id") Long id) {
        Optional<Cliente> clienteOpt = clienteService.SearchById(id);
        if (clienteOpt.isPresent()) {
            Mascota mascota = new Mascota();
            model.addAttribute("mascota", mascota);
            model.addAttribute("cliente", clienteOpt.get());
            return "crear_mascota";
        } else {
            // Manejar el caso cuando no se encuentra el cliente
            return "error_pagina"; // Cambiar por la página de error correspondiente
        }
    }

    // localhost:8091/mascota/agregar/{id}
    @PostMapping("/agregar/{id}")
    public String agregarMascota(@ModelAttribute("mascota") Mascota mascota, @PathVariable("id") Long id) {
        Optional<Cliente> clienteOpt = clienteService.SearchById(id);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            mascota.setCliente(cliente);
            cliente.agregarMascota(mascota);
            clienteService.update(cliente);
            mascotaService.addMascota(mascota);
            return "redirect:/mascota";
        } else {
            // Manejar el caso cuando no se encuentra el cliente
            return "error_pagina"; // Cambiar por la página de error correspondiente
        }
    }

    // localhost:8091/mascota/delete/{id}
    @GetMapping("/delete/{id}")
    public String borrarMascota(@PathVariable("id") Long id) {
        Optional<Mascota> mascotaOpt = mascotaService.SearchById(id);
        if (mascotaOpt.isPresent()) {
            Mascota mascota = mascotaOpt.get();
            Optional<Cliente> clienteOpt = clienteService.SearchById(mascota.getCliente().getId());
            if (clienteOpt.isPresent()) {
                Cliente cliente = clienteOpt.get();
                cliente.eliminarMascota(mascota);
                clienteService.update(cliente);
                mascotaService.deleteById(id);
            }
            return "redirect:/mascota";
        } else {
            // Manejar el caso cuando no se encuentra la mascota
            return "error_pagina"; // Cambiar por la página de error correspondiente
        }
    }

    // localhost:8091/mascota/update/{id}
    @GetMapping("/update/{id}")
    public String mostrarFormularioUpdate(@PathVariable("id") Long id, Model model) {
        Optional<Mascota> mascotaOpt = mascotaService.SearchById(id);
        if (mascotaOpt.isPresent()) {
            model.addAttribute("mascota", mascotaOpt.get());
            model.addAttribute("clientes", clienteService.SearchAll());
            return "actualizar_mascota";
        } else {
            // Manejar el caso cuando no se encuentra la mascota
            return "error_pagina"; // Cambiar por la página de error correspondiente
        }
    }

    // localhost:8091/mascota/save/{id}
    @PostMapping("/save/{id}")
    public String actualizarMascota(@PathVariable("id") Long id, @ModelAttribute("mascota") Mascota mascota) {
        Optional<Cliente> clienteOpt = clienteService.SearchById(mascota.getCliente().getId());
        if (clienteOpt.isPresent()) {
            mascota.setCliente(clienteOpt.get());
            mascotaService.update(mascota);
            return "redirect:/mascota";
        } else {
            // Manejar el caso cuando no se encuentra el cliente
            return "error_pagina"; // Cambiar por la página de error correspondiente
        }
    }
}
