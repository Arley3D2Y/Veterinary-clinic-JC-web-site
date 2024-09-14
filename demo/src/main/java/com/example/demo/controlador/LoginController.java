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
import com.example.demo.model.Veterinario;
import com.example.demo.servicio.ClienteService;
import com.example.demo.servicio.VeterinarioService;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private VeterinarioService veterinarioService;

    @GetMapping("/cliente")
    public String mostrarLoginCliente(Model model) {
        model.addAttribute("txtCedula", "");  // Limpia el campo de cédula
        model.addAttribute("error", "");      // Limpia el mensaje de error
        return "login";
    }

    @GetMapping("/veterinario")
    public String mostrarLoginVeterinario(Model model) {
        model.addAttribute("txtCedula", "");  // Limpia el campo de cédula
        model.addAttribute("error", "");      // Limpia el mensaje de error
        return "login_veterinario";
    }

    @PostMapping("/cliente")
    public String loginCliente(@RequestParam("cedula") String cedula, Model model) {
        Optional<Cliente> cliente = clienteService.SearchByCedula(cedula);
        if (cliente.isPresent()) {
            return "redirect:/cliente/inicio?cedula=" + cedula;
        } else {
            model.addAttribute("txtCedula", cedula); // Mantener el valor ingresado por el usuario
            model.addAttribute("error", "*Usuario no registrado"); // Mostrar el mensaje de error
            return "login";
        }
    }

    @PostMapping("/veterinario")
    public String loginVeterinario(@RequestParam("correo") String correo, @RequestParam("password") String password, Model model) {
        Veterinario veterinario = veterinarioService.SearchByCorreo(correo);
        if (veterinario != null) {
            if (veterinario.getPassword().equals(password)) {
                return "redirect:/veterinario/inicio?correo=" + veterinario.getCorreo();
            } else {
                model.addAttribute("txtCorreo", correo); // Mantener el valor ingresado por el usuario
                model.addAttribute("txtPassword", password);
                model.addAttribute("passwordError", "*Contraseña incorrecta"); // Mostrar el mensaje de error
                return "login_veterinario";
            }
        } else {
            model.addAttribute("txtCorreo", correo); // Mantener el valor ingresado por el usuario
            model.addAttribute("txtPassword", password);
            model.addAttribute("emailError", "*Usuario no registrado");
            return "login_veterinario";
        }
    }
    


}
