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

import com.example.demo.errorHandling.NotFoundException;
import com.example.demo.model.Cliente;
import com.example.demo.model.Mascota;
import com.example.demo.servicio.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    /** Clientes **/

    /** localhost:8091/veterinario/clientes **/
    @GetMapping("/findAll")
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


}
