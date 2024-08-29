package com.example.demo.controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entidad.Cliente;
import com.example.demo.entidad.Mascota;
import com.example.demo.servicio.ClienteService;
import com.example.demo.servicio.MascotaService;

@Controller
@RequestMapping("/veterinario")
public class VeterinarioController {
    
    @Autowired
    MascotaService mascotaService;
    @Autowired
    private ClienteService clienteService;

    //localhost:8091/veterinario/inicio
    @GetMapping("/inicio")
    public String mostrarInicio(Model model) {
        model.addAttribute("clientes", clienteService.SearchAll());
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
        Cliente cliente = new Cliente(0, "", "", "", "");
        model.addAttribute("cliente", cliente);
        return "crear_cliente";
    }

    // localhost:8091/veterinario/clientes/find/{id}
    @GetMapping("/clientes/find/{id}")
    //@PathVariable("id") indica que el parametro es un id
    private String mostrarInfoClientes(Model model, @PathVariable("id") int identificacion) {

        Cliente cliente = clienteService.SearchById(identificacion);
        if (cliente != null) {
            // se agrega el estudiante al modelo para el html
            model.addAttribute("cliente", clienteService.SearchById(identificacion));
            ArrayList<Mascota> mascotas = cliente.getMascotas();

            model.addAttribute("mascotas", mascotas);    
        }
        else{
            //se lanza la excepcion NotFoundException creada anteriormente
            throw new NotFoundException(identificacion);
        }
        return "datos_cliente";
    }

    // localhost:8091/veterinario/clientes/update/{id}
    @GetMapping("/clientes/update/{id}")
    private String formularioUpdateCliente(@PathVariable("id") int identificacion, Model model) {
        model.addAttribute("cliente", clienteService.SearchById(identificacion));
        return "actualizar_cliente";
    }

    // localhost:8091/veterinario/clientes/update/{id}
    @PostMapping("/clientes/save/{id}")
    private String actualizarCliente(@PathVariable("id") int identificacion, @ModelAttribute("cliente") Cliente cliente) {
        clienteService.update(cliente);
        return "redirect:/veterinario/clientes";
    }

    // localhost:8091/veterinario/clientes/delete/{id}
    @GetMapping("/clientes/delete/{id}")
    private String borrarCliente(@PathVariable("id") int identificacion) {
        //llama al servicio y le dice que borre al usuario
        clienteService.deleteById(identificacion);
        return "redirect:/veterinario/clientes";
    }

    // localhost:8091/veterinario/clientes/agregar
    @PostMapping("/clientes/agregar")
    private String agregaCliente(@ModelAttribute("cliente") Cliente cliente) {
        //apenas llega la solicitud se llama al servicio para agregar los datos
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
    public String mostrarInfoMascota(Model model, @PathVariable("id") int identificacion) {
        Mascota mascota = mascotaService.SearchById(identificacion);
        if (mascota != null) {
            // se agrega el estudiante al modelo para el html
            model.addAttribute("mascota",mascota);
        }
        return "datos_mascotas";
    }

    // localhost:8091/veterinario/mascotas/update/{id}
    @GetMapping("/mascotas/update/{id}")
    private String formularioUpdateMascota(@PathVariable("id") int identificacion, Model model) {
        model.addAttribute("mascota", mascotaService.SearchById(identificacion));
        model.addAttribute("clientes", clienteService.SearchAll());
        return "actualizar_mascota";
    }

    // localhost:8091/veterinario/mascotas/delete/{id}
    @GetMapping("/mascotas/delete/{id}")
    private String borrarMascota(@PathVariable("id") int identificacion) {
        // llama al servicio y le dice que borre al usuario
        mascotaService.deleteById(identificacion);
        return "redirect:/veterinario/mascotas";
    }

    // localhost:8091/veterinario/mascotas/add
    @GetMapping("/mascotas/add")
    private String formularioCrearMascotas(Model model) {
        Mascota mascota = new Mascota(0, "", "", "", "", "");
        model.addAttribute("mascota", mascota);
        model.addAttribute("clientes", clienteService.SearchAll());
        return "crear_mascota";
    }

    // localhost:8091/veterinario/mascotas/agregar
    @PostMapping("/mascotas/agregar")
    private String agregaMascota(@ModelAttribute("mascota") Mascota mascota) {
        Cliente duenho = clienteService.SearchById(mascota.getDuenho().getId());
        duenho.agregarMascota(mascota);
        clienteService.update(duenho);
        mascota.setDuenho(duenho);
        mascotaService.addMascota(mascota);
        return "redirect:/veterinario/mascotas";
    }

    // localhost:8091/veterinario/mascotas/update/{id}
    @PostMapping("/mascotas/save/{id}")
    private String actualizarMascota(@PathVariable("id") int identificacion,
        @ModelAttribute("mascota") Mascota mascota) {
        Cliente duenho = clienteService.SearchById(mascota.getDuenho().getId());
        mascota.setDuenho(duenho);
        mascotaService.update(mascota);

        return "redirect:/veterinario/mascotas";
    }


    /** Tratamientos **/
    @GetMapping("/tratamientos")
    public String mostrarTratamientos(Model model) {
        return "redirect:/veterinario/mascotas";
    }
    
}
