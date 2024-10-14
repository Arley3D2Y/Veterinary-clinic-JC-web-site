package com.example.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cliente;
import com.example.demo.repositorio.ClienteRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImp implements ClienteService {

    @Autowired
    ClienteRepository clientRepo;

    @Override
    public List<Cliente> searchAll() {
        return clientRepo.findAll();
    }

    @Override
    public Optional<Cliente> searchById(Long id) {
        return clientRepo.findById(id);
    }

    @Override
    public Cliente searchByCedula(String cedula) {
        return clientRepo.findByCedula(cedula);
    }

    @Override
    public boolean addCliente(Cliente cliente) {
        if (!clientRepo.existsById(cliente.getId())) {
            clientRepo.save(cliente);
            return true;      
        } else {
            return false;
        }
    }

    @Override
    public boolean removeById(Long id) {
        if (clientRepo.existsById(id)) {
            clientRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateById(Long id, Cliente cliente) {
        if (clientRepo.existsById(id)) {
            clientRepo.save(cliente);  // Save es usado tanto para crear como para actualizar
            return true;
        }
        return false;
    }

    public List<Cliente> searchByNombre(String nombre) {
        // Llamar al repositorio para buscar los clientes que contengan el nombre
        return clientRepo.findByNombreContainingIgnoreCase(nombre);
    }
    
}
