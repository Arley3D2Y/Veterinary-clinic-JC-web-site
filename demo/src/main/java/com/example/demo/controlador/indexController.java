package com.example.demo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class indexController {

    //http://localhost:8080/inicio
    @GetMapping
    public String mostrarIncio() {
        return "index";
    }
}
