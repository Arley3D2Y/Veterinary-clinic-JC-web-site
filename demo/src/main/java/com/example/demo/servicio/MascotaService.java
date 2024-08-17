package com.example.demo.servicio;

import com.example.demo.entidad.Mascota;

import java.util.Collection;


public interface MascotaService {

    public Mascota SearchById(int id);

    public Collection<Mascota> SearchAll();

}
