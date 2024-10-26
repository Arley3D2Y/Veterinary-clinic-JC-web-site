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
    public List<Cliente> searchAllClientes() {
        return clientRepo.findAll();
    }

    @Override
    public Optional<Cliente> searchClienteById(Long id) {
        return clientRepo.findById(id);
    }

    @Override
    public Optional<Cliente> addCliente(Cliente cliente) {
        Optional<Cliente> clienteOpt = clientRepo.findByCedula(cliente.getCedula());

        if (!clienteOpt.isPresent()) {
            cliente = clientRepo.save(cliente);
            return Optional.of(cliente);
        }
        return Optional.empty();
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
    public Optional<Cliente> updateById(Long id, Cliente cliente) {
        Optional<Cliente> clientOpt = clientRepo.findById(id);
        if (clientOpt.isPresent()) {
            cliente.setMascotas(clientOpt.get().getMascotas());
            cliente = clientRepo.save(cliente);  // Save es usado tanto para crear como para actualizar
            return Optional.of(cliente);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Cliente> searchByCedula(String cedula) {
        return clientRepo.findByCedula(cedula);
    }

    public List<Cliente> searchByNombre(String nombre) {
        return clientRepo.findByNombreStartingWithIgnoreCase(nombre);
    }
    
}
