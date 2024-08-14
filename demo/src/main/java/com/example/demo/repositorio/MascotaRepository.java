package com.example.demo.repositorio;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.entidad.Mascota;
import org.springframework.stereotype.Repository;


@Repository
public class MascotaRepository {

    private Map<Integer, Mascota> data = new HashMap<>();

    public MascotaRepository(){+
        
        data.put(1, new Mascota(1, "Toby", "Macho", "Birmano", "2017-11-13"));
        data.put(2, new Mascota(2, "Manchas", "Macho", "Persa", "2020-08-01"));
        data.put(3, new Mascota(3, "Orion", "Macho", "Burmés", "2019-06-13"));
        data.put(4, new Mascota(4, "Linsy", "Hembra", "Ragdoll", "2013-09-14"));
        data.put(5, new Mascota(5, "Trixy", "Hembra", "Siberiano", "2020-06-19"));

    }

    public Mascota findById(int id){
        return data.get(id);
    }

    public Collection<Mascota> findAll(){
        return data.values();
    }   

}
