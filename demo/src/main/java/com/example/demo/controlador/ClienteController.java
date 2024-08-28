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

import com.example.demo.model.Cliente;
import com.example.demo.model.Mascota;
import com.example.demo.servicio.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // localhost:8091/cliente
    @GetMapping
    public String mostrarClientes(Model model) {
        model.addAttribute("clientes", clienteService.SearchAll());
        return "mostrar_todos_clientes";
    }

    // localhost:8091/cliente/find{id}
    @GetMapping("/find{id}")
    private String mostrarInfoCliente(Model model, @PathVariable("id") Long identificacion) {

        Optional<Cliente> clienteOpt = clienteService.SearchById(identificacion);
        
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            model.addAttribute("cliente", cliente);
            List<Mascota> mascotas = cliente.getMascotas();
            model.addAttribute("mascotas", mascotas);
        } else {
            return "error_cliente_no_encontrado"; // Puedes crear una página de error personalizada
        }
        return "datos_cliente";
    }

    // localhost:8091/cliente/add
    @GetMapping("/add")
    private String mostrarFormularioCrear(Model model) {
        Cliente cliente = new Cliente("", "", "", "", "");
        model.addAttribute("cliente", cliente);
        return "crear_cliente";
    }

    // localhost:8091/cliente/agregar
    @PostMapping("/agregar")
    private String agregaCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.addCliente(cliente);
        return "redirect:/cliente";
    }

    // localhost:8091/cliente/delete/{id}
    @GetMapping("/delete/{id}")
    private String borrarCliente(@PathVariable("id") Long identificacion) {
        Optional<Cliente> clienteOpt = clienteService.SearchById(identificacion);
        if (clienteOpt.isPresent()) {
            clienteService.deleteById(identificacion);
        } else {
            return "error_cliente_no_encontrado"; // Maneja el caso donde el cliente no existe
        }
        return "redirect:/cliente";
    }

    // localhost:8091/cliente/update/{id}
    @GetMapping("/update/{id}")
    private String mostrarFormularioUpdate(@PathVariable("id") Long identificacion, Model model) {
        Optional<Cliente> clienteOpt = clienteService.SearchById(identificacion);
        if (clienteOpt.isPresent()) {
            model.addAttribute("cliente", clienteOpt.get());
        } else {
            return "error_cliente_no_encontrado"; // Maneja el caso donde el cliente no existe
        }
        return "actualizar_cliente";
    }

    // localhost:8091/cliente/update/1234
    @PostMapping("/save{id}")
    private String actualizarCliente(@PathVariable("id") Long identificacion, @ModelAttribute("cliente") Cliente cliente) {
        Optional<Cliente> clienteExistenteOpt = clienteService.SearchById(identificacion);
        if (clienteExistenteOpt.isPresent()) {
            clienteService.update(cliente);
        } else {
            return "error_cliente_no_encontrado"; // Maneja el caso donde el cliente no existe
        }
        return "redirect:/cliente";
    }
}
