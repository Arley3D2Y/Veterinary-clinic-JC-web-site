package com.example.demo.servicio;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cliente;
import com.example.demo.repositorio.ClienteRepository;
import java.util.List;

@Service
public class ClienteServiceImp implements ClienteService {

    @Autowired
    ClienteRepository repo;

     // Implementacion de los metodos
    @Override
    public Cliente SearchById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Optional<Cliente> SearchByCedula(String cedula) {
        return repo.findByCedula(cedula);
    }

    @Override
    public Collection<Cliente> SearchAll() {
        return repo.findAll();
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
        repo.save(cliente);
    }
    public List<Cliente> buscarPorNombre(String nombre) {
        // Llamar al repositorio para buscar los clientes que contengan el nombre
        return repo.findByNombreContainingIgnoreCase(nombre);
    }
}
