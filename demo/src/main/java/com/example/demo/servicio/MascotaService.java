package com.example.demo.servicio;

import com.example.demo.entidad.Mascota;

import java.util.Collection;

public interface MascotaService {

    /**
     * Este metodo es usado para buscar mascotas por su id.
     *
     * @param id El ID con el que es identificada la mascota.
     * @return EL objeto Mascota representado a la mascota con el ID dado
     *         o null si no existe
     */
    public Mascota SearchById(int id);

    /**
     * Este metodo es usado para obtener la información de todas las mascotas.
     *
     * @return La colección de todas las mascotas.
     */
    public Collection<Mascota> SearchAll();

}
