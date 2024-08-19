package com.example.demo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entidad.Cliente;
import com.example.demo.entidad.Mascota;
import com.example.demo.servicio.ClienteService;
import com.example.demo.servicio.MascotaService;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/mascota")
public class MascotaController {

    @Autowired
    MascotaService mascotaService;
    @Autowired
    ClienteService clienteService; // Agregar el servicio de clientes

    // localhost:8091/mascota
    @GetMapping
    public String mostrarMascotas(Model model) {

        model.addAttribute("mascotas", mascotaService.SearchAll());
        return "mostrar_todas_mascotas";
    }

    // localhost:8091/mascota/find{id}
    @GetMapping("/find{id}")
    public String mostrarInfoMascota(Model model, @PathVariable("id") int identificacion) {

        Mascota mascota = mascotaService.SearchById(identificacion);
        if (mascota != null) {
            // se agrega el estudiante al modelo para el html
            model.addAttribute("mascota",mascota);
        }

        // else{
        // //se lanza la excepcion NotFoundException creada anteriormente
        // throw new NotFoundException(identificacion);
        // }
        return "datos_mascotas";
    }

    // localhost:8091/mascota/add
    @GetMapping("/add")
    private String mostrarFormularioCrear(Model model) {

        Mascota mascota = new Mascota(0, "", "", "", "", "");
        model.addAttribute("mascota", mascota);

        // Obtener la lista de clientes y agregar al modelo
        model.addAttribute("clientes", clienteService.SearchAll());

        return "crear_mascota";
    }

    // localhost:8091/mascota/agregar
    @PostMapping("/agregar")
    private String agregaMascota(@ModelAttribute("mascota") Mascota mascota) {

        Cliente duenho = clienteService.SearchById(mascota.getDuenho().getId());

        duenho.agregarMascota(mascota);
        clienteService.update(duenho);

        mascota.setDuenho(duenho);
        mascotaService.addMascota(mascota);

        return "redirect:/mascota";
    }

    // localhost:8091/mascota/delete/1234
    @GetMapping("/delete/{id}")
    private String borrarMascota(@PathVariable("id") int identificacion) {
        // llama al servicio y le dice que borre al usuario
        mascotaService.deleteById(identificacion);
        return "redirect:/mascota";
    }

    // localhost:8091/mascota/update/1234
    @GetMapping("/update/{id}")
    private String mostrarFormularioUpdate(@PathVariable("id") int identificacion, Model model) {

        model.addAttribute("mascota", mascotaService.SearchById(identificacion));

        model.addAttribute("clientes", clienteService.SearchAll());

        return "actualizar_mascota";
    }

    // localhost:8091/mascota/update/1234
    @PostMapping("/update/{id}")
    private String actualizarMascota(@PathVariable("id") int identificacion,
            @ModelAttribute("mascota") Mascota mascota) {
        Cliente duenho = clienteService.SearchById(mascota.getDuenho().getId());
        mascota.setDuenho(duenho);
        mascotaService.update(mascota);

        return "redirect:/mascota";
    }
}
