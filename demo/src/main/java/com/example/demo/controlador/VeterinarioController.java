package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Especialidad;
import com.example.demo.model.Tratamiento;
import com.example.demo.model.UserEntity;
import com.example.demo.model.Veterinario;
import com.example.demo.repositorio.UserRepository;
import com.example.demo.security.CustomUserDetailService;
import com.example.demo.security.JWTGenerator;
import com.example.demo.servicio.VeterinarioService;

import com.example.demo.DTO.VeterinarioDTO;
import com.example.demo.DTO.VeterinarioMapper;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/veterinarios")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTGenerator jwtGenerator;
    /* Veterinarios: Peticiones CRUD */

    // localhost:8088/veterinarios
    @GetMapping
    @Operation(summary = "Find all veterinarys")
    public ResponseEntity<List<Veterinario>> obtenerVeterinarios() {
        List<Veterinario> veterinarios = veterinarioService.searchAllVeterinarios();

        return ResponseEntity.ok(veterinarios);
    }

    // localhost:8088/veterinarios/find/{id}
    @GetMapping("/find/{id}")
    @Operation(summary = "Find veterinary by id")
    public ResponseEntity<Veterinario> obtenerVeterinarioPorId(@PathVariable Long id) {
        Optional<Veterinario> veterinario = veterinarioService.searchVeterinarioById(id);

        return veterinario.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /* Hacerlo en todos los Users - Usar DTO para retornar lo creado */

    // localhost:8088/veterinarios/add/especialidad-id/{idE}
    @PostMapping("/add/especialidad-id/{idE}")
    @Operation(summary = "Add a new veterinary by specialty id")
    public ResponseEntity<?> crearVeterinario(@PathVariable("idE") Long especialityId,
            @RequestBody Veterinario veterinario) {

        if (!userRepository.existsByUsername(veterinario.getCorreo())) {
            UserEntity userEntity = customUserDetailService.VeterinarioToUser(veterinario);
            veterinario.setUser(userEntity);

            Optional<Veterinario> nuevoVeterinario = veterinarioService.addVeterinario(especialityId, veterinario);
            if (nuevoVeterinario.isPresent()) {
                VeterinarioDTO veterinarioDTO = VeterinarioMapper.INSTANCE.convert(nuevoVeterinario.get());

                return new ResponseEntity<>(veterinarioDTO, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<String>("El usuario ya existe", HttpStatus.CONFLICT);
        }
    }

    // localhost:8088/veterinarios/delete/{id}
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete veterinary by id")
    public ResponseEntity<Void> eliminarVeterinario(@PathVariable Long id) {
        boolean isDeleted = veterinarioService.removeById(id);

        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // localhost:8088/veterinarios/update/{id}
    @PutMapping("/update/{id}")
    @Operation(summary = "Update veterinary by id")
    public ResponseEntity<Veterinario> actualizarVeterinario(@PathVariable Long id,
            @RequestBody Veterinario veterinario) {
        Optional<Veterinario> veterionarioActualizado = veterinarioService.updateById(id, veterinario);

        return veterionarioActualizado.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /* Busquedas - search by */

    // localhost:8088/veterinarios/search-by-name/{search}
    @GetMapping("/search-by-name/{search}")
    @Operation(summary = "Search veterinarians by name")
    public ResponseEntity<List<Veterinario>> searchByNombre(@PathVariable String search) {
        List<Veterinario> veterinarios = veterinarioService.searchByNombre(search);

        return ResponseEntity.ok(veterinarios); // 200 OK
    }

    // localhost:8088/veterinarios/search-by-document/{search}
    @GetMapping("/search-by-document/{search}")
    @Operation(summary = "Search veterinary by document")
    public ResponseEntity<Veterinario> buscarVeterinarioByCedula(@PathVariable String search) {
        Optional<Veterinario> veterinario = veterinarioService.searchByCedula(search);

        return veterinario.map(v -> new ResponseEntity<>(v, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // localhost:8088/veterinarios/search-by-email/{search}
    @GetMapping("/search-by-email/{search}")
    @Operation(summary = "Search veterinary by email")
    public ResponseEntity<Veterinario> buscarVeterinarioByCorreo(@PathVariable String search) {
        Optional<Veterinario> veterinario = veterinarioService.searchByCorreo(search);

        return veterinario.map(v -> new ResponseEntity<>(v, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /* Buscar listas del veterinario o las entidades */

    // localhost:8088/veterinarios/{id}/tratamientos
    @GetMapping("/{id}/tratamientos")
    @Operation(summary = "Get treatments by veterinary")
    public ResponseEntity<List<Tratamiento>> getTratamientosVeterinario(@PathVariable Long id) {
        Optional<Veterinario> veterinarioOpt = veterinarioService.searchVeterinarioById(id);

        return veterinarioOpt.map(veterinario -> ResponseEntity.ok(veterinario.getTratamientos()))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // localhost:8088/veterinarios/{id}/especialidad
    @GetMapping("/{id}/especialidad")
    @Operation(summary = "Get specialty by veterinary id")
    public ResponseEntity<Especialidad> getEspecialidadVeterinario(@PathVariable Long id) {
        Optional<Veterinario> veterinarioOpt = veterinarioService.searchVeterinarioById(id);

        return veterinarioOpt.map(veterinario -> ResponseEntity.ok(veterinario.getEspecialidad()))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // localhost:8080/administradores/login
    @PostMapping("/login")
    public ResponseEntity<?> loginVeterinary(@RequestBody Veterinario veterinario) {
   
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(veterinario.getCorreo(), veterinario.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        return new ResponseEntity<String>( token, HttpStatus.OK);}

}
