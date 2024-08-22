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
@RequestMapping("/user")
public class DuenhoController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("txtCedula", ""); // Asegurarse de que el campo esté vacío al cargar la página
        return "login_cliente";
    }

    @PostMapping("/login")
    public String loginCliente(@RequestParam("cedula") String cedula, Model model) {
        if (cedula == null || cedula.trim().isEmpty()) {
            model.addAttribute("error", "*Campo requerido");
            return "login_cliente";
        }
        
        Cliente cliente = clienteService.obtenerClientePorCedula(cedula);
        if (cliente != null) {
            return "redirect:/user/home?cedula=" + cedula;
        } else {
            model.addAttribute("error", "*Usuario no registrado");
            return "login_cliente";
        }
    }

    @GetMapping("/home")
    public String mostrarHomeCliente(@RequestParam("cedula") String cedula, Model model) {
        Cliente cliente = clienteService.obtenerClientePorCedula(cedula);

        if (cliente == null) {
            model.addAttribute("error", "*Usuario no encontrado");
            return "login_cliente";
        }
        ArrayList<Mascota> mascotas = cliente.getMascotas();

        model.addAttribute("cliente", cliente); // Pasar la información del cliente al modelo
        model.addAttribute("mascotas", mascotas);

        return "home_cliente";
    }

}
