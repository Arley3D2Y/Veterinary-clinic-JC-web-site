package com.example.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cliente;
import com.example.demo.repositorio.ClienteRepository;
import java.util.List;

@Service
public class ClienteServiceImp implements ClienteService {

    @Autowired
    ClienteRepository repo;

    @Override
    public List<Cliente> SearchAll() {
        return repo.findAll();
    }

    @Override
    public Cliente SearchById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Cliente SearchByCedula(String cedula) {
        return repo.findByCedula(cedula);
    }

    @Override
    public void addCliente(Cliente cliente) {
        repo.save(cliente);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void update(Cliente cliente) {
        Cliente existingCliente = repo.findById(cliente.getId()).orElse(null);
        if (existingCliente != null) {
            repo.save(cliente);  // Save es usado tanto para crear como para actualizar
        }
    }

    public List<Cliente> buscarPorNombre(String nombre) {
        // Llamar al repositorio para buscar los clientes que contengan el nombre
        return repo.findByNombreContainingIgnoreCase(nombre);
    }
    
}
