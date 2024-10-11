package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Cliente;
import com.example.demo.model.Mascota;
import com.example.demo.servicio.ClienteService;
import com.example.demo.servicio.MascotaService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private MascotaService mascotaService; // Servicio para obtener los datos de la mascota

    @GetMapping("/inicio")
    public String mostrarHomeCliente(@RequestParam("cedula") String cedula, Model model) {
        Cliente cliente = clienteService.SearchByCedula(cedula);

        if (cliente == null) {
            model.addAttribute("error", "*Usuario no encontrado");
            return "login_cliente";
        }

        // Usar List<Mascota> en lugar de ArrayList<Mascota>
        List<Mascota> mascotas = cliente.getMascotas();

        model.addAttribute("cliente", cliente); // Pasar la información del cliente al modelo
        model.addAttribute("mascotas", mascotas);
        model.addAttribute("mascotaSeleccionada", null);

        return "home_cliente";
    }

    // Cargar la página principal con la lista de mascotas y la mascota seleccionada
    @GetMapping("/findMascota/{clienteId}/{mascotaId}")
    public String findMascota(@PathVariable("clienteId") Long clienteId, @PathVariable("mascotaId") Long mascotaId, Model model) {
        // Obtener el cliente por ID
        Cliente cliente = clienteService.SearchById(clienteId);
        // Obtener todas las mascotas
        List<Mascota> mascotas = cliente.getMascotas();

        // Obtener la mascota seleccionada por ID
        Mascota mascotaSeleccionada = mascotaService.SearchById(mascotaId);

        if (cliente != null && mascotaSeleccionada != null) {
            model.addAttribute("mascotas", mascotas);
            model.addAttribute("mascotaSeleccionada", mascotaSeleccionada);
            model.addAttribute("cliente", cliente);
        } else {
            model.addAttribute("mascotaSeleccionada", null);
            model.addAttribute("cliente", null);
        }

        return "home_cliente"; // Redirigir a la misma página principal
    }
}
