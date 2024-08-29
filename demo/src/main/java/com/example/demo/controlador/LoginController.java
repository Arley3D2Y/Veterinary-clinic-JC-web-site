package com.example.demo.controlador;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Cliente;
import com.example.demo.servicio.ClienteService;

@Controller
@RequestMapping("/login")
public class LoginController {
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
        if (cedula == null || cedula.trim().isEmpty()) {
            model.addAttribute("error", "*Campo requerido");
            return "login";
        }
        
        Optional<Cliente> cliente = clienteService.SearchByCedula(cedula);
        if (cliente.isPresent()) {
            return "redirect:/cliente/inicio?cedula=" + cedula;
        } else {
            model.addAttribute("txtCedula", cedula); // Mantener el valor ingresado por el usuario
            model.addAttribute("error", "*Usuario no registrado"); // Mostrar el mensaje de error
            return "login";
        }
    }


}
