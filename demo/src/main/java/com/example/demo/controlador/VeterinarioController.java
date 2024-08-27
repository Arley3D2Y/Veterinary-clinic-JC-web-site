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

@Controller
@RequestMapping("/veterinario")
public class VeterinarioController {

    @Autowired
    private ClienteService clienteService;

    //localhost:8091/cliente
    @GetMapping("/inicio")
    public String mostrarClientes(Model model) {
        model.addAttribute("clientes", clienteService.SearchAll());
        return "Home_veterinario";
    }

    // localhost:8091/cliente/find{id}
    @GetMapping("/find{id}")
    //@PathVariable("id") indica que el parametro es un id
    private String mostrarInfoCliente(Model model, @PathVariable("id") int identificacion) {

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

    //localhost:8091/cliente/add
    @GetMapping("/add")
    private String mostrarFormularioCrear(Model model) {

        Cliente cliente = new Cliente(0, "", "", "", "");
        model.addAttribute("cliente", cliente);

        return "crear_cliente";
    }

    // localhost:8091/cliente/agregar
    @PostMapping("/agregar")
    private String agregaCliente(@ModelAttribute("cliente") Cliente cliente) {
        //apenas llega la solicitud se llama al servicio para agregar los datos
        clienteService.addCliente(cliente);

        return "redirect:/cliente";
    }

    // localhost:8091/cliente/delete/1234
    @GetMapping("/delete/{id}")
    private String borrarCliente(@PathVariable("id") int identificacion) {
        //llama al servicio y le dice que borre al usuario
        clienteService.deleteById(identificacion);
        return "redirect:/cliente";
    }

    // localhost:8091/cliente/update/1234
    @GetMapping("/update/{id}")
    private String mostrarFormularioUpdate(@PathVariable("id") int identificacion, Model model) {
       
        model.addAttribute("cliente", clienteService.SearchById(identificacion));
       
        return "actualizar_cliente";
    }

    // localhost:8091/cliente/update/1234
    @PostMapping("/save{id}")
    private String actualizarCliente(@PathVariable("id") int identificacion, @ModelAttribute("cliente") Cliente cliente) {
       
        clienteService.update(cliente);

        return "redirect:/cliente";
    }


}
