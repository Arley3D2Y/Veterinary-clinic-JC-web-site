package com.example.demo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.servicio.MascotaService;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/mascota")
public class MascotaController {

    @Autowired
    MascotaService mascotaService;

    //localhost:8080/mascota/all
    @GetMapping("/all")
    public String mostrarMascotas(Model model){

        model.addAttribute("mascotas", mascotaService.SearchAll());
        return "mostrar_todas_mascotas";
    }

    //localhost:8080/mascota/find?id=1
    @GetMapping("/find{id}")
    public String mostrarInfoMascota(Model model, @PathVariable("id") int identificacion) {
    
        model.addAttribute("mascota", mascotaService.SearchById(identificacion));
        return "datos_mascotas";
    }

}
