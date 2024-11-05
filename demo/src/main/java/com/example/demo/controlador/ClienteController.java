package com.example.demo.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.example.demo.DTO.ClienteDTO;
import com.example.demo.DTO.ClienteMapper;
import com.example.demo.model.Cliente;
import com.example.demo.model.Mascota;
import com.example.demo.model.UserEntity;
import com.example.demo.repositorio.UserRepository;
import com.example.demo.security.CustomUserDetailService;
import com.example.demo.servicio.ClienteService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    AuthenticationManager authenticationManager;
    /* Clientes: Peticiones CRUD */

    // localhost:8088/clientes
    @GetMapping
    @Operation(summary = "Find all clients")
    public ResponseEntity<List<Cliente>> obtenerClientes() {
        List<Cliente> clientes = clienteService.searchAllClientes();

        return ResponseEntity.ok(clientes);
    }

    // localhost:8088/clientes/find/{id}
    @GetMapping("/find/{id}")
    @Operation(summary = "Find client by id")
    private ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.searchClienteById(id);

        return cliente.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // localhost:8088/clientes/add
    @PostMapping("/add")
    @Operation(summary = "Add a new client")
    private ResponseEntity<?> crearCliente(@RequestBody Cliente cliente) {
        /*
         * Optional<Cliente> nuevoCliente = clienteService.addCliente(cliente);
         * 
         * return nuevoCliente.map(c -> new ResponseEntity<>(c, HttpStatus.CREATED))
         * .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
         */
        if (!userRepository.existsByUsername(cliente.getCedula())) {
            UserEntity userEntity = customUserDetailService.ClienteToUser(cliente);
            cliente.setUser(userEntity);

            Optional<Cliente> nuevoCliente = clienteService.addCliente(cliente);
            if (nuevoCliente.isPresent()) {
                ClienteDTO clienteDTO = ClienteMapper.INSTANCE.convert(nuevoCliente.get());

                return new ResponseEntity<>(clienteDTO, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<String>("El usuario ya existe", HttpStatus.CONFLICT);
        }
    }

    // localhost:8088/clientes/delete/{id}
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete client by id")
    private ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        boolean isDeleted = clienteService.removeById(id);

        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // localhost:8088/clientes/update/{id}
    @PutMapping("/update/{id}")
    @Operation(summary = "Update client by id")
    private ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteActualizado = clienteService.updateById(id, cliente);

        return clienteActualizado.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /* Busquedas - search by */

    // localhost:8088/clientes/search-by-document/{document}
    @GetMapping("/search-by-document/{document}")
    @Operation(summary = "Find client by document")
    public ResponseEntity<Cliente> buscarClienteByCedula(@PathVariable("document") String cedula) {
        Optional<Cliente> cliente = clienteService.searchByCedula(cedula);

        return cliente.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // localhost:8088/clientes/search-by-name/{search}
    @GetMapping("/search-by-name/{search}")
    @Operation(summary = "Find client by name")
    public ResponseEntity<List<Cliente>> searchByNombre(@PathVariable String search) {
        List<Cliente> clientes = clienteService.searchByNombre(search);

        return ResponseEntity.ok(clientes);
    }

    /* Buscar listas del cliente o las entidades */

    // localhost:8088/clientes/{id}/mascotas
    @GetMapping("/{id}/mascotas")
    @Operation(summary = "Get pets by client")
    public ResponseEntity<List<Mascota>> getMascotascliente(@PathVariable Long id) {
        Optional<Cliente> clienteOpt = clienteService.searchClienteById(id);

        return clienteOpt.map(cliente -> ResponseEntity.ok(cliente.getMascotas()))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // // localhost:8088/clientes/login
    // @PostMapping("/login")
    // public ResponseEntity loginCliente(@RequestBody Cliente cliente) {
    //     Authentication authentication = authenticationManager.authenticate(
    //             new UsernamePasswordAuthenticationToken(cliente.getCedula()));

    //     SecurityContextHolder.getContext().setAuthentication(authentication);

    //     return new ResponseEntity<String>("Usuario ingresado con exito", HttpStatus.OK);
    // }

}
