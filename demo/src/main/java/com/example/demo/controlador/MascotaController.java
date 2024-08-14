package com.example.demo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/mascota")
public class MascotaController {

    @Autowired
    MascotaService mascotaService;

    @GetMapping("/all")
    public String mostrarMascotas(Model model){

        model.addAttribute(addAttribute:"mascotas", mascotaService.SearchAll());
        return "mostrar_todas_mascotas";
    }

    @GetMapping("/find")
    public String mostrarInfoMascota() {

        return "mostrar_mascota";
    }

}
