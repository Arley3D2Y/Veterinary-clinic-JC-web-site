package com.example.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cliente;
import com.example.demo.repositorio.ClienteRepository;
import com.example.demo.repositorio.MascotaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImp implements ClienteService {

    @Autowired
    ClienteRepository clientRepo;

    // Repositorio de mascotas
    @Autowired
    MascotaRepository mascotaRepo;

    /* Clientes: Peticiones CRUD */

    // Busqueda de todos los clientes
    @Override
    public List<Cliente> searchAllClientes() {
        return clientRepo.findAll();
    }

    // Busqueda de un cliente por id
    @Override
    public Optional<Cliente> searchClienteById(Long id) {
        return clientRepo.findById(id);
    }

    // Creacion un nuevo cliente
    @Override
    public Optional<Cliente> addCliente(Cliente cliente) {
        Optional<Cliente> clienteOpt = clientRepo.findByCedula(cliente.getCedula());
        // Se debe crear con lista de mascotas vacia
        
        if (!clienteOpt.isPresent()) {
            cliente = clientRepo.save(cliente);
            return Optional.of(cliente);
        }
        return Optional.empty();
    }

    // Método para eliminar un cliente
    @Override
    public boolean removeById(Long id) {
        if (clientRepo.existsById(id)) {
            clientRepo.deleteById(id);
            return true;
        }
        return false;
    }

    // Actualizar un cliente
    @Override
    public Optional<Cliente> updateById(Long id, Cliente cliente) {
        Optional<Cliente> clientOpt = clientRepo.findById(id);
        if (clientOpt.isPresent()) {
            Cliente c = clientOpt.get();
            cliente.setId(c.getId());  // Asegura que el ID no cambie
            cliente.setMascotas(c.getMascotas());  // Mantiene las mascotas existentes
            return Optional.of(clientRepo.save(cliente));
        }
        return Optional.empty();
    }


    /* Busquedas - search by */

    // Busqueda clientes por nombre
    @Override
    public List<Cliente> searchByNombre(String nombre) {
        return clientRepo.findByNombreStartingWithIgnoreCase(nombre);
    }
    
    // Busqueda de un cliente por cedula
    @Override
    public Optional<Cliente> searchByCedula(String cedula) {
        return clientRepo.findByCedula(cedula);
    }

}
