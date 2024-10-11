package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Cliente;
import com.example.demo.model.Mascota;
import com.example.demo.servicio.ClienteService;
import com.example.demo.servicio.MascotaService;
import com.example.demo.errorHandling.NotFoundException;

@Controller
@RequestMapping("/veterinario")
public class VeterinarioController {

    @Autowired
    MascotaService mascotaService;

    @Autowired
    private ClienteService clienteService;

    /** Mascotas **/

    /** localhost:8091/veterinario/mascotas **/
    @GetMapping("/mascotas")
    public String mostrarMascotas(Model model) {
        model.addAttribute("mascotas", mascotaService.SearchAll());
        return "mascotas_veterinario";
    }

    // localhost:8091/veterinario/mascotas/find/{id}
    @GetMapping("/mascotas/find/{id}")
    public String mostrarInfoMascota(Model model, @PathVariable("id") Long id) {
        Mascota mascotaOpt = mascotaService.SearchById(id);
        if (mascotaOpt != null) {
            model.addAttribute("mascota", mascotaOpt);
            return "datos_mascotas";
        } else {
            // Manejar el caso cuando no se encuentra la mascota
            return "pagina_error"; // Cambiar por la página de error correspondiente
        }
    }

    // localhost:8091/veterinario/mascotas/update/{id}
    @GetMapping("/mascotas/update/{id}")
    public String mostrarFormularioUpdate(@PathVariable("id") Long id, Model model) {
        Mascota mascota = mascotaService.SearchById(id);
        if (mascota != null) {
            model.addAttribute("mascota", mascota);
            model.addAttribute("clientes", clienteService.SearchAll());
            return "actualizar_mascota";
        } else {
            // Manejar el caso cuando no se encuentra la mascota
            return "pagina_error"; // Cambiar por la página de error correspondiente
        }
    }

    // localhost:8091/veterinario/mascotas/delete/{id}
    @GetMapping("/mascotas/delete/{id}")
    public String borrarMascota(@PathVariable("id") Long id) {
        Mascota mascota = mascotaService.SearchById(id);
        if (mascota != null) {
            Cliente clienteOpt = clienteService.SearchById(mascota.getCliente().getId());
            if (clienteOpt != null) {
                Cliente cliente = clienteOpt;
                cliente.eliminarMascota(mascota);
                clienteService.update(cliente);
                mascotaService.deleteById(id);
            }
            return "redirect:/veterinario/mascotas";
        } else {
            // Manejar el caso cuando no se encuentra la mascota
            return "pagina_error"; // Cambiar por la página de error correspondiente
        }
    }

    // localhost:8091/veterinario/mascotas/save/{id}

    // localhost:8091/veterinario/mascotas/add
    @GetMapping("/mascotas/add/{id}")
    public String mostrarFormularioCrear(Model model, @PathVariable("id") Long id) {
        Cliente clienteOpt = clienteService.SearchById(id);
        if (clienteOpt != null) {
            Mascota mascota = new Mascota();
            model.addAttribute("mascota", mascota);
            model.addAttribute("cliente", clienteOpt);
            return "crear_mascota";
        } else {
            // Manejar el caso cuando no se encuentra el cliente
            return "pagina_error"; // Cambiar por la página de error correspondiente
        }
    }

    // localhost:8091/veterinario/mascotas/agregar
    @PostMapping("/mascotas/agregar/{id}")
    public String agregarMascota(@ModelAttribute("mascota") Mascota mascota, @PathVariable("id") Long id) {
        Cliente clienteOpt = clienteService.SearchById(id);
        if (clienteOpt != null) {
            Cliente cliente = clienteOpt;
            mascota.setCliente(cliente);
            cliente.agregarMascota(mascota);

            // Aquí solo necesitamos actualizar el cliente, que en cascada guardará la
            // mascota
            clienteService.update(cliente);

            return "redirect:/veterinario/mascotas";
        } else {
            // Manejar el caso cuando no se encuentra el cliente
            return "error_pagina"; // Cambiar por la página de error correspondiente
        }
    }

    // localhost:8091/veterinario/mascotas/update/{id}
    @PostMapping("/mascotas/save/{id}")
    public String actualizarMascota(@PathVariable("id") Long id, @ModelAttribute("mascota") Mascota mascota) {
        Cliente clienteOpt = clienteService.SearchById(mascota.getCliente().getId());
        if (clienteOpt != null) {
            mascota.setCliente(clienteOpt);
            mascotaService.update(mascota);
            return "redirect:/veterinario/mascotas/find/{id}";
        } else {
            // Manejar el caso cuando no se encuentra el cliente
            return "pagina_error"; // Cambiar por la página de error correspondiente
        }
    }

    @GetMapping("/mascotas/buscar")
    public String buscarMascotas(@RequestParam("search") String search, Model model) {
        // Llamar al servicio para buscar clientes por nombre
        List<Mascota> mascotas = mascotaService.buscarPorNombre(search);
        model.addAttribute("mascotas", mascotas);
        return "mascotas_veterinario"; // Redirigir a la vista de clientes con los resultados de búsqueda
    }
    
    /** Tratamientos **/
    @GetMapping("/tratamientos")
    public String mostrarTratamientos(Model model) {
        throw new NotFoundException();
    }

}
