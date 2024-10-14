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

import com.example.demo.model.Mascota;
import com.example.demo.servicio.MascotaService;
import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/mascotas")
@CrossOrigin(origins = "http://localhost:4200")
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

    /* Mascotas */

    // localhost:8091/mascotas
    @GetMapping
    @Operation(summary = "Find all pets")
    public ResponseEntity<List<Mascota>> mostrarMascotas() {
        List<Mascota> mascotas = mascotaService.searchAllMascotas();
        if (mascotas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(mascotas);
        }
    }

    // localhost:8091/mascotas/find/{id}
    @GetMapping("/find/{id}")
    @Operation(summary = "Find pet by id")
    public ResponseEntity<Mascota> mostrarMascotaPorId(@PathVariable Long id) {
        Optional<Mascota> mascota = mascotaService.searchMascotaById(id);
        return mascota.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // localhost:8091/mascotas/add
    @PostMapping("/add/cliente-id/{id}")
    @Operation(summary = "Add a new pet by client id")
    public ResponseEntity<String> crearMascota(@PathVariable Long id, @RequestBody Mascota mascota) {
        boolean isAdded = mascotaService.addMascota(id, mascota);
        if (!isAdded) {
            return ResponseEntity.badRequest().body("Pet not found.");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body("Pet added successfully."); // 201 Created
        }
    }

    // localhost:8091/veterinario/mascotas/delete/{id}
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete pet by id")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        boolean isDeleted = mascotaService.removeById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // localhost:8091/mascotas/update/{id}
    @PutMapping("/update/{id}")
    @Operation(summary = "Update pet by id")
    public ResponseEntity<Void> actualizarMascota(@PathVariable Long id, @RequestBody Mascota mascota) {
        boolean isUpdated = mascotaService.updateById(id, mascota);
        if (!isUpdated) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        return ResponseEntity.noContent().build(); // 204 No Content si se actualizó con éxito
    }

    @GetMapping("/search-by-name/{search}")
    @Operation(summary = "Search pets by name")
    public ResponseEntity<List<Mascota>> buscarMascotas(@PathVariable String search) {
        List<Mascota> mascotas = mascotaService.searchByNombre(search);
        return ResponseEntity.ok(mascotas); // 200 OK
    }

    @GetMapping("/search-by-client_id/{id}")
    @Operation(summary = "Find pets by client ID")
    public ResponseEntity<List<Mascota>> buscarMascotasByClienteId(@PathVariable("id") Long identificacion) {
        List<Mascota> mascotas = mascotaService.searchByClienteId(identificacion);
        return ResponseEntity.ok(mascotas); // 200 OK
    }

}
