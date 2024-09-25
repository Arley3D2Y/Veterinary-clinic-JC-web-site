package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Cliente;
import com.example.demo.model.Mascota;
import com.example.demo.model.Tratamiento;
import com.example.demo.model.Veterinario;
import com.example.demo.servicio.ClienteService;
import com.example.demo.servicio.MascotaService;
import com.example.demo.servicio.TratamientoService;
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

    @Autowired
    private TratamientoService tratamientoService;

    // HoLa

    // localhost:8091/veterinario/inicio

    @GetMapping("/inicio")
    public String mostrarHomeVeterinario(@RequestParam("correo") String correo, Model model) {
        Veterinario veterinario = veterinarioService.SearchByCorreo(correo);
        // Usar List<Mascota> en lugar de ArrayList<Mascota>
        model.addAttribute("clientes", clienteService.SearchAll());
        model.addAttribute("veterinario", veterinario); // Pasar la información del cliente al modelo

        return "clientes_veterinario";
    }

    /** Clientes **/

    /** localhost:8091/veterinario/clientes **/
    @GetMapping("/clientes")
    public String mostrarClientes(Model model) {
        model.addAttribute("clientes", clienteService.SearchAll());
        return "clientes_veterinario";
    }

    // localhost:8091/veterinario/clientes/add
    @GetMapping("/clientes/add")
    private String formularioCrearCliente(Model model) {
        Cliente cliente = new Cliente("", "", "", "", "", "");
        model.addAttribute("cliente", cliente);
        return "crear_cliente";
    }

    // localhost:8091/veterinario/clientes/find/{id}
    @GetMapping("/clientes/find/{id}")
    // @PathVariable("id") indica que el parametro es un id
    private String mostrarInfoCliente(Model model, @PathVariable("id") Long identificacion) {

        Cliente clienteOpt = clienteService.SearchById(identificacion);
        if (clienteOpt != null) {
            Cliente cliente = clienteOpt;
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
        Cliente clienteOpt = clienteService.SearchById(identificacion);
        if (clienteOpt != null) {
            model.addAttribute("cliente", clienteOpt);
        } else {
            return "pagina_error"; // Maneja el caso donde el cliente no existe
        }
        return "actualizar_cliente";
    }

    // localhost:8091/veterinario/clientes/update/{id}
    @PostMapping("/clientes/save/{id}")
    private String actualizarCliente(@PathVariable("id") Long identificacion,
            @ModelAttribute("cliente") Cliente cliente) {
        Cliente clienteExistenteOpt = clienteService.SearchById(identificacion);
        if (clienteExistenteOpt != null) {
            cliente.setMascotas(clienteExistenteOpt.getMascotas());
            clienteService.update(cliente);
        } else {
            return "pagina_error"; // Maneja el caso donde el cliente no existe
        }
        return "redirect:/veterinario/clientes/find/{id}";
    }

    // localhost:8091/veterinario/clientes/delete/{id}
    @GetMapping("/clientes/delete/{id}")
    private String borrarCliente(@PathVariable("id") Long identificacion) {
        Cliente clienteOpt = clienteService.SearchById(identificacion);
        if (clienteOpt != null) {
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

    @GetMapping("/clientes/buscar")
    public String buscarClientes(@RequestParam("search") String search, Model model) {
        // Llamar al servicio para buscar clientes por nombre
        List<Cliente> clientes = clienteService.buscarPorNombre(search);
        model.addAttribute("clientes", clientes);
        return "clientes_veterinario"; // Redirigir a la vista de clientes con los resultados de búsqueda
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

    @GetMapping("/mascotas/{id}/tratamientos")
    public void obtenerTratamientosPorMascota(@PathVariable Long id) {
        List<Tratamiento> tratamientos = (List<Tratamiento>) tratamientoService.SearchByMascota(id);
    
    }

    /** Tratamientos **/
    @GetMapping("/tratamientos")
    public String mostrarTratamientos(Model model) {
        throw new NotFoundException();
    }

    @PostMapping("/tratamientos/agregar")
    private String agregaTratamiento(@ModelAttribute("tratamiento") Tratamiento tratamiento) {
        tratamientoService.addTratamiento(tratamiento);

        return "redirect:/veterinario/tratamientos";

    }

    @PutMapping("taratamiento/update/{id}")
    public void actualizarTratamiento(@PathVariable("id") Long id,
            @ModelAttribute("tratamiento") Tratamiento tratamiento) {
        tratamientoService.update(tratamiento);
    }
}
