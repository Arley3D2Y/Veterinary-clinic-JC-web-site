package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Cliente;
import com.example.demo.model.Mascota;
import com.example.demo.model.Tratamiento;
import com.example.demo.servicio.ClienteService;
import com.example.demo.servicio.MascotaService;
import com.example.demo.servicio.TratamientoService;

import io.swagger.v3.oas.annotations.Operation;

import com.example.demo.errorHandling.NotFoundException;

@RestController
@RequestMapping("/veterinario")
@CrossOrigin(origins = "http://localhost:4200")
public class VeterinarioController {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TratamientoService tratamientoService;

    /** Clientes **/

    /** localhost:8091/veterinario/clientes **/
    @GetMapping("/clientes")
    @Operation(summary = "Find all clients")
    public List<Cliente> mostrarClientes(Model model) {
        return clienteService.SearchAll();
    }

    // localhost:8091/veterinario/clientes/find/{id}
    @GetMapping("/clientes/find/{id}")
    @Operation(summary = "Find client by id")
    private Cliente mostrarInfoCliente(@PathVariable("id") Long identificacion) {
        Cliente cliente = clienteService.SearchById(identificacion);
        return cliente;
    }

    // localhost:8091/veterinario/clientes/search-by-document/{document}
    @GetMapping("/clientes/search-by-document/{document}")
    @Operation(summary = "Find client by document")
    public Cliente buscarClienteByCedula(@PathVariable("document") String cedula) {
        Cliente cliente = clienteService.SearchByCedula(cedula);
        return cliente;
    }

    // localhost:8091/veterinario/clientes/agregar
    @PostMapping("/clientes/add")
    @Operation(summary = "Add a new client")
    private void agregarCliente(@RequestBody Cliente cliente) {
        clienteService.addCliente(cliente);
    }

    // localhost:8091/veterinario/clientes/delete/{id}
    @DeleteMapping("/clientes/delete/{id}")
    @Operation(summary = "Delete client by id")
    private void eliminarCliente(@PathVariable("id") Long identificacion) {
        clienteService.deleteById(identificacion);
    }

    // localhost:8091/veterinario/clientes/update/{id}
    @PutMapping("/clientes/update/{id}")
    @Operation(summary = "Update client by id")
    private void actualizarCliente(@PathVariable("id") Long identificacion, @RequestBody Cliente cliente) {
        clienteService.update(cliente);
    }

    // localhost:8091/veterinario/clientes/search-by-name
    @GetMapping("/clientes/search-by-name")
    @Operation(summary = "Find client by name")
    public List<Cliente> buscarClientes(@RequestParam("search") String search) {
        List<Cliente> clientes = clienteService.buscarPorNombre(search);
        return clientes; 
    }



    /** Mascotas **/

    /** localhost:8091/veterinario/mascotas **/
    @GetMapping("/mascotas")
    @Operation(summary = "Find all pets")
    public List<Mascota> mostrarMascotas() {
        List<Mascota> mascota = mascotaService.SearchAll();
        return mascota;
    }

    // localhost:8091/veterinario/mascotas/find/{id}
    @GetMapping("/mascotas/find/{id}")
    public Mascota mostrarInfoMascota(@PathVariable("id") Long id) {
        Mascota mascota = mascotaService.SearchById(id);
        return mascota;
    }

    // localhost:8091/veterinario/mascotas/add
    @PostMapping("/mascotas/add/cliente/{id}")
    @Operation(summary = "Add a new pet")
    public void agregarMascota(@PathVariable("id") Long id, @RequestBody Mascota mascota) {
        mascotaService.addMascota(mascota);
    }

    // localhost:8091/veterinario/mascotas/update/{id}
    @PutMapping("/mascotas/update/{id}")
    @Operation(summary = "Update pet by id")
    public void actualizarMascota(@PathVariable("id") Long id, @RequestBody Mascota mascota) {
        mascotaService.update(mascota);
    }

    // localhost:8091/veterinario/mascotas/delete/{id}
    @DeleteMapping("/mascotas/delete/{id}")
    @Operation(summary = "Delete pet by id")
    public void eliminarMascota(@PathVariable("id") Long identificacion) {
        mascotaService.deleteById(identificacion);
    }

    @GetMapping("/mascotas/search-by-name/{search}")
    @Operation(summary = "Delete pet by id")
    public List<Mascota> buscarMascotas(@RequestParam String search) {
        List<Mascota> mascotas = mascotaService.buscarPorNombre(search);
        return mascotas; // Redirigir a la vista de clientes con los resultados de búsqueda
    }

    @GetMapping("/mascotas/search-by-client/{id}")
    @Operation(summary = "Find pet by client")
    public List<Mascota> buscarMascotasByClienteId(@PathVariable Long id) {
        List<Mascota> mascotas = mascotaService.buscarPorClienteId(id);
        return mascotas;
    }





    @GetMapping("/mascotas/{id}/tratamientos")
    public void obtenerTratamientosPorMascota(@PathVariable Long id) {
    
    }

    /** Tratamientos **/
    @GetMapping("/tratamientos")
    public String mostrarTratamientos(Model model) {
        throw new NotFoundException();
    }

    @PostMapping("/tratamientos/agregar")
    private String agregaTratamiento(@ModelAttribute("tratamiento") Tratamiento tratamiento) {
        tratamientoService.addTratamiento(tratamiento);

        return "redirect:/veterinario/tratamientos";

    }

    @PutMapping("taratamiento/update/{id}")
    public void actualizarTratamiento(@PathVariable("id") Long id,
            @ModelAttribute("tratamiento") Tratamiento tratamiento) {
        tratamientoService.update(tratamiento);
    }
}
