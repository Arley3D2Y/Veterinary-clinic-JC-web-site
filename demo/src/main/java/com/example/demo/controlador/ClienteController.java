package com.example.demo.controlador;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.servicio.ClienteService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    /** Clientes **/

    // localhost:8091/clientes
    @GetMapping
    @Operation(summary = "Find all clients")
    public ResponseEntity<List<Cliente>> obtenerClientes() {
        List<Cliente> clientes = clienteService.searchAllClientes();
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes);
    }

    // localhost:8091/clientes/find/{id}
    @GetMapping("/find/{id}")
    @Operation(summary = "Find client by id")
    private ResponseEntity<Cliente> obtenerClientePorId(@PathVariable("id") Long id) {
        Optional<Cliente> cliente = clienteService.searchClienteById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // localhost:8091/clientes/add
    @PostMapping("/add")
    @Operation(summary = "Add a new client")
    private ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Optional<Cliente> nuevoCliente = clienteService.addCliente(cliente);
        return nuevoCliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    // localhost:8091/clientes/delete/{id}
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete client by id")
    private ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        boolean isDeleted = clienteService.removeById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // localhost:8091/clientes/update/{id}
    @PutMapping("/update/{id}")
    @Operation(summary = "Update client by id")
    private ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteActualizado = clienteService.updateById(id, cliente);
        return clienteActualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // localhost:8091/clientes/searchPets

    // localhost:8091/search-by-name
    @GetMapping("/search-by-name/{search}")
    @Operation(summary = "Find client by name")
    public ResponseEntity<List<Cliente>> searchByNombre(@PathVariable String search) {
        List<Cliente> clientes = clienteService.searchByNombre(search);
        return ResponseEntity.ok(clientes); 
    }

    // localhost:8091/search-by-document/{document}
    @GetMapping("/search-by-document/{document}")
    @Operation(summary = "Find client by document")
    public ResponseEntity<Cliente> buscarClienteByCedula(@PathVariable("document") String cedula) {
        Optional<Cliente> cliente = clienteService.searchByCedula(cedula);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
