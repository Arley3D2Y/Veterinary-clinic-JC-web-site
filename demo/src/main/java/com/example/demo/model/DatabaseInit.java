package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import com.example.demo.repositorio.ClienteRepository;
import org.springframework.boot.ApplicationArguments;

import jakarta.transaction.Transactional;

@Controller
@Transactional
public class DatabaseInit implements ApplicationRunner{

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        clienteRepository.save(new Cliente("Juan Carlos","1435466", "juancarlos@gmail.com", "2343544354"));
        clienteRepository.save(new Cliente("Pedro","14789806", "pedro@gmail.com", "4366854"));
        clienteRepository.save(new Cliente("Carlos Luis","2234466", "carlos@gmail.com", "546544354"));
        clienteRepository.save(new Cliente("Luis Alberto","14789806", "luis@gmail.com", "56766854"));
        clienteRepository.save(new Cliente("Carlos Ret","1435466", "carlosret@gmail.com", "456546554"));
    }

    
}
