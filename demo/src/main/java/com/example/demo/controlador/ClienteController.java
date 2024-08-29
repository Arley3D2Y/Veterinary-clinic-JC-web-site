package com.example.demo.controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entidad.Cliente;
import com.example.demo.entidad.Mascota;
import com.example.demo.servicio.ClienteService;

@Controller
@RequestMapping("/cliente")
public class clienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/inicio")
    public String mostrarHomeCliente(@RequestParam("cedula") String cedula, Model model) {
        Cliente cliente = clienteService.obtenerClientePorCedula(cedula);

        ArrayList<Mascota> mascotas = cliente.getMascotas();

        model.addAttribute("cliente", cliente); // Pasar la información del cliente al modelo
        model.addAttribute("mascotas", mascotas);

        return "Home_cliente";
    }
}
