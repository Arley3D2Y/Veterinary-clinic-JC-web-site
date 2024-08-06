package com.example.demo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/inicio")
public class indexController {

    @GetMapping
    public String mostrarIncio() {
        return "index";
    }
}