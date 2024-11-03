package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cliente;
import com.example.demo.model.Enfermedad;
import com.example.demo.model.Mascota;
import com.example.demo.model.Tratamiento;
import com.example.demo.servicio.MascotaService;
import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/mascotas")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    /*
     * 200 OK: Para solicitudes exitosas que devuelven datos.
     * 201 Created: Para crear un nuevo recurso.
     * 204 No Content: Para operaciones exitosas sin contenido en la respuesta.
     * 400 Bad Request: Para solicitudes malformadas.
     * 404 Not Found: Para recursos que no se encontraron.
     */

    /* Mascotas: Peticiones CRUD */

    // localhost:8088/mascotas
    @GetMapping
    @Operation(summary = "Find all pets")
    public ResponseEntity<List<Mascota>> obtenerMascotas() {
        List<Mascota> mascotas = mascotaService.searchAllMascotas();
    
        return ResponseEntity.ok(mascotas);
    }

    // localhost:8088/mascotas/find/{id}
    @GetMapping("/find/{id}")
    @Operation(summary = "Find pet by id")
    public ResponseEntity<Mascota> obtenerMascotaPorId(@PathVariable Long id) {
        Optional<Mascota> mascota = mascotaService.searchMascotaById(id);
        
        return mascota.map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // localhost:8088/mascotas/add/cliente-id/{idC}/enfermedad-id/{idE}
    @PostMapping("/add/cliente-id/{idC}/enfermedad-id/{idE}")
    @Operation(summary = "Add a new pet by client id")
    public ResponseEntity<Mascota> crearMascota(@PathVariable("idC") Long clienteId, @PathVariable("idE") Long enfermedadId, @RequestBody Mascota mascota) {
        Optional<Mascota> nuevaMascota = mascotaService.addMascota(clienteId, enfermedadId, mascota);

        return nuevaMascota.map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // localhost:8088/mascotas/delete/{id}
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete pet by id")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        boolean isDeleted = mascotaService.removeById(id);
        
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // localhost:8088/mascotas/update/{id}
    @PutMapping("/update/{id}")
    @Operation(summary = "Update pet by id")
    public ResponseEntity<Mascota> actualizarMascota(@PathVariable Long id, @RequestBody Mascota mascota) {
        Optional<Mascota> mascotaActualizada = mascotaService.updateById(id, mascota);

        return mascotaActualizada.map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /* Busquedas - search by */

    // localhost:8088/mascotas/search-by-name/{search}
    @GetMapping("/search-by-name/{search}")
    @Operation(summary = "Find pets by name")
    public ResponseEntity<List<Mascota>> searchByNombre(@PathVariable String search) {
        List<Mascota> mascotas = mascotaService.searchByNombre(search);

        return ResponseEntity.ok(mascotas);
    }


    /* Buscar listas del veterinario o por entidades */

    // localhost:8088/mascotas/{id}/tratamientos
    @GetMapping("/{id}/tratamientos")
    @Operation(summary = "Get treatments by pet id")
    public ResponseEntity<List<Tratamiento>> getTratamientosMascota(@PathVariable Long id) {
        Optional<Mascota> mascotaOpt = mascotaService.searchMascotaById(id);

        return mascotaOpt.map(mascota -> ResponseEntity.ok(mascota.getTratamientos()))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // localhost:8088/mascotas/{id}/cliente
    @GetMapping("/{id}/cliente")
    @Operation(summary = "Get client by pet id")
    public ResponseEntity<Cliente> getClienteMascota(@PathVariable Long id) {
        Optional<Mascota> mascotaOpt = mascotaService.searchMascotaById(id);

        return mascotaOpt.map(mascota -> ResponseEntity.ok(mascota.getCliente()))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // localhost:8088/mascotas/{id}/enfermedad
    @GetMapping("/{id}/enfermedad")
    @Operation(summary = "Get disease by pet id")
    public ResponseEntity<Enfermedad> getEnfermedadMascota(@PathVariable Long id) {
        Optional<Mascota> mascotaOpt = mascotaService.searchMascotaById(id);

        return mascotaOpt.map(mascota -> ResponseEntity.ok(mascota.getEnfermedad()))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    

}
