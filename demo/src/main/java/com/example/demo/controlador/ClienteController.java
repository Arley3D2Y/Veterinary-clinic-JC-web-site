package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Cliente;
import com.example.demo.model.Mascota;
import com.example.demo.servicio.ClienteService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/inicio")
    public String mostrarHomeCliente(@RequestParam("cedula") String cedula, Model model) {
        Optional<Cliente> cliente = clienteService.SearchByCedula(cedula);

        if (cliente.isEmpty()) {
            model.addAttribute("error", "*Usuario no encontrado");
            return "login_cliente";
        }

        // Usar List<Mascota> en lugar de ArrayList<Mascota>
        List<Mascota> mascotas = cliente.get().getMascotas();

        model.addAttribute("cliente", cliente.get()); // Pasar la información del cliente al modelo
        model.addAttribute("mascotas", mascotas);

        return "home_cliente";
    }
}
