package com.example.demo.controlador;

import java.util.List;
import java.util.Optional;

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
import com.example.demo.model.Veterinario;
import com.example.demo.servicio.ClienteService;
import com.example.demo.servicio.MascotaService;
import com.example.demo.servicio.VeterinarioService;
import com.example.demo.errorHandling.NotFoundException;

@Controller
@RequestMapping("/veterinario")
public class VeterinarioController {
    
    @Autowired
    MascotaService mascotaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VeterinarioService veterinarioService;

    //localhost:8091/veterinario/inicio

    @GetMapping("/inicio")
    public String mostrarHomeVeterinario(@RequestParam("cedula") String cedula, Model model) {
        Optional<Veterinario> veterinario = veterinarioService.SearchByCedula(cedula);
        if (veterinario.isEmpty()) {
            model.addAttribute("error", "*Usuario no encontrado");
            return "login_veterinario";
        }

        // Usar List<Mascota> en lugar de ArrayList<Mascota>
        model.addAttribute("veterinario", veterinario.get()); // Pasar la información del cliente al modelo

        return "home_veterinario";
    }

    /** Clientes **/

    /** localhost:8091/veterinario/clientes **/
    @GetMapping("/clientes")
    public String mostrarClientes(Model model) {
        model.addAttribute("clientes", clienteService.SearchAll());
        return "clientes_veterinario";
    }

    //localhost:8091/veterinario/clientes/add
    @GetMapping("/clientes/add")
    private String formularioCrearCliente(Model model) {
        Cliente cliente = new Cliente("", "", "", "", "");
        model.addAttribute("cliente", cliente);
        return "crear_cliente";
    }

    // localhost:8091/veterinario/clientes/find/{id}
    @GetMapping("/clientes/find/{id}")
    //@PathVariable("id") indica que el parametro es un id
    private String mostrarInfoCliente(Model model, @PathVariable("id") Long identificacion) {

        Optional<Cliente> clienteOpt = clienteService.SearchById(identificacion);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            model.addAttribute("cliente", cliente);
            List<Mascota> mascotas = cliente.getMascotas();
            model.addAttribute("mascotas", mascotas);
        } else {
            throw new NotFoundException(identificacion);
        }
        return "datos_cliente";
    }

    // localhost:8091/veterinario/clientes/update/{id}
    @GetMapping("/clientes/update/{id}")
    private String formularioUpdateCliente(@PathVariable("id") Long identificacion, Model model) {
        Optional<Cliente> clienteOpt = clienteService.SearchById(identificacion);
        if (clienteOpt.isPresent()) {
            model.addAttribute("cliente", clienteOpt.get());
        } else {
            return "pagina_error"; // Maneja el caso donde el cliente no existe
        }
        return "actualizar_cliente";
    }

    // localhost:8091/veterinario/clientes/update/{id}
    @PostMapping("/clientes/save/{id}")
    private String actualizarCliente(@PathVariable("id") Long identificacion, @ModelAttribute("cliente") Cliente cliente) {
        Optional<Cliente> clienteExistenteOpt = clienteService.SearchById(identificacion);
        if (clienteExistenteOpt.isPresent()) {
            cliente.setMascotas(clienteExistenteOpt.get().getMascotas());
            clienteService.update(cliente);
        } else {
            return "pagina_error"; // Maneja el caso donde el cliente no existe
        }
        return "redirect:/veterinario/clientes";
    }

    // localhost:8091/veterinario/clientes/delete/{id}
    @GetMapping("/clientes/delete/{id}")
    private String borrarCliente(@PathVariable("id") Long identificacion) {
        Optional<Cliente> clienteOpt = clienteService.SearchById(identificacion);
        if (clienteOpt.isPresent()) {
            clienteService.deleteById(identificacion);
        } else {
            return "pagina_error"; // Maneja el caso donde el cliente no existe
        }
        return "redirect:/veterinario/clientes";
    }

    // localhost:8091/veterinario/clientes/agregar
    @PostMapping("/clientes/agregar")
    private String agregaCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.addCliente(cliente);

        return "redirect:/veterinario/clientes";
    }

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
        Optional<Mascota> mascotaOpt = mascotaService.SearchById(id);
        if (mascotaOpt.isPresent()) {
            model.addAttribute("mascota", mascotaOpt.get());
            return "datos_mascotas";
        } else {
            // Manejar el caso cuando no se encuentra la mascota
            return "pagina_error"; // Cambiar por la página de error correspondiente
        }
    }

    // localhost:8091/veterinario/mascotas/update/{id}
    @GetMapping("/mascotas/update/{id}")
    public String mostrarFormularioUpdate(@PathVariable("id") Long id, Model model) {
        Optional<Mascota> mascotaOpt = mascotaService.SearchById(id);
        if (mascotaOpt.isPresent()) {
            model.addAttribute("mascota", mascotaOpt.get());
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
            return "redirect:/veterinario/mascotas";
        } else {
            // Manejar el caso cuando no se encuentra la mascota
            return "pagina_error"; // Cambiar por la página de error correspondiente
        }
    }

    // localhost:8091/veterinario/mascotas/add
    @GetMapping("/mascotas/add/{id}")
    public String mostrarFormularioCrear(Model model, @PathVariable("id") Long id) {
        Optional<Cliente> clienteOpt = clienteService.SearchById(id);
        if (clienteOpt.isPresent()) {
            Mascota mascota = new Mascota();
            model.addAttribute("mascota", mascota);
            model.addAttribute("cliente", clienteOpt.get());
            return "crear_mascota";
        } else {
            // Manejar el caso cuando no se encuentra el cliente
            return "pagina_error"; // Cambiar por la página de error correspondiente
        }
    }

    // localhost:8091/veterinario/mascotas/agregar
    @PostMapping("/mascotas/agregar/{id}")
    public String agregarMascota(@ModelAttribute("mascota") Mascota mascota, @PathVariable("id") Long id) {
        Optional<Cliente> clienteOpt = clienteService.SearchById(id);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            mascota.setCliente(cliente);
            cliente.agregarMascota(mascota);
            clienteService.update(cliente);
            mascotaService.addMascota(mascota);
            return "redirect:/veterinario/mascotas";
        } else {
            // Manejar el caso cuando no se encuentra el cliente
            return "error_pagina"; // Cambiar por la página de error correspondiente
        }
    }

    // localhost:8091/veterinario/mascotas/update/{id}
    @PostMapping("/mascotas/save/{id}")
    public String actualizarMascota(@PathVariable("id") Long id, @ModelAttribute("mascota") Mascota mascota) {
        Optional<Cliente> clienteOpt = clienteService.SearchById(mascota.getCliente().getId());
        if (clienteOpt.isPresent()) {
            mascota.setCliente(clienteOpt.get());
            mascotaService.update(mascota);
            return "redirect:/veterinario/mascotas";
        } else {
            // Manejar el caso cuando no se encuentra el cliente
            return "pagina_error"; // Cambiar por la página de error correspondiente
        }
    }

    /** Tratamientos **/
    @GetMapping("/tratamientos")
    public String mostrarTratamientos(Model model) {
        throw new NotFoundException();
    }
    
}
