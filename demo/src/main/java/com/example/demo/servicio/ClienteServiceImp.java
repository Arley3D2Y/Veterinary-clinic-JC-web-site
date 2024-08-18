package com.example.demo.servicio;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Cliente;
import com.example.demo.repositorio.ClienteRepository;

@Service
public class ClienteServiceImp implements ClienteService {

    @Autowired
    ClienteRepository clientRep;

    @Override
    public Cliente SearchById(int id) {
        return clientRep.findById(id);
    }

    @Override
    public Collection<Cliente> SearchAll() {
        return clientRep.findAll();
    }

    @Override
    public void addCliente(Cliente cliente) {
        clientRep.addCliente(cliente);
    }

    @Override
    public void deleteById(int id) {
        clientRep.deleteById(id);
    }

    @Override
    public void update(Cliente cliente) {
        clientRep.update(cliente);
    }

}
