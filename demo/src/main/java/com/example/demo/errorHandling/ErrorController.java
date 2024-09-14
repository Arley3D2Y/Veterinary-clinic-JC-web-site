package com.example.demo.errorHandling;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.model.Veterinario;
import com.example.demo.servicio.VeterinarioService;

@ControllerAdvice
public class ErrorController {
    
    private final VeterinarioService veterinarioService;

    // Inyección de dependencias en el constructor
    public ErrorController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }

    @ExceptionHandler(NotFoundException.class)
    public String error(Model model, NotFoundException ex) {
        Veterinario veterinario = veterinarioService.SearchByCorreo(ex.getCorreo());
        if (veterinario != null) {
            model.addAttribute("veterinario", veterinario);
        }

        model.addAttribute("id", ex.getId());  // Pasar el ID de la excepción al modelo
        return "pagina_error";  // Redirigir a la página de error
    }
    
}    
