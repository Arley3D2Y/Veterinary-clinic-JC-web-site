package com.example.demo.controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entidad.Cliente;
import com.example.demo.entidad.Mascota;
import com.example.demo.servicio.ClienteService;

@Controller
@RequestMapping("/login")
public class DuenhoController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/cliente")
    public String mostrarLogin(Model model) {
        model.addAttribute("txtCedula", "");  // Limpia el campo de cédula
        model.addAttribute("error", "");      // Limpia el mensaje de error
        return "login";
    }

    @PostMapping("/cliente")
    public String loginCliente(@RequestParam("cedula") String cedula, Model model) {
        Cliente cliente = clienteService.obtenerClientePorCedula(cedula);
        if (cliente != null) {
            return "redirect:/login/cliente/home?cedula=" + cedula;
        } else {
            model.addAttribute("txtCedula", cedula); // Mantener el valor ingresado por el usuario
            model.addAttribute("error", "*Usuario no registrado"); // Mostrar el mensaje de error
            return "login";
        }
    }

    @GetMapping("/cliente/home")
    public String mostrarHomeCliente(@RequestParam("cedula") String cedula, Model model) {
        Cliente cliente = clienteService.obtenerClientePorCedula(cedula);

        ArrayList<Mascota> mascotas = cliente.getMascotas();

        model.addAttribute("cliente", cliente); // Pasar la información del cliente al modelo
        model.addAttribute("mascotas", mascotas);

        return "home_cliente";
    }

}
