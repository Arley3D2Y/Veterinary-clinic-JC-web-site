package com.example.demo.servicio;

import java.time.LocalDate;
import java.util.*;

import com.example.demo.model.Tratamiento;

public interface TratamientoService {

    /*
     * Este metodo es usado para buscar tratamientos por su id.
     *
     * @param identificacion El ID con el que es identificado el tratamiento.
     * 
     * @return EL objeto Tratamiento representado al tratamiento con el ID dado
     * o null si no existe
     */
    public Tratamiento SearchById(Long identificacion);

    /*
     * Este metodo es usado para buscar tratamientos por su fecha de inicio.
     *
     * @param starDate la fecha en la que inicio el tratamiento.
     * 
     * @return Una coleccion de los tratamientos iniciados en esa fecha
     */
    public Collection<Tratamiento> SearchByStartDate(LocalDate startDate);

    /*
     * Este metodo es usado para buscar tratamientos por su fecha de finalizacion.
     *
     * @param starDate la fecha en la que inicio el tratamiento.
     * 
     * @return Una coleccion de los tratamientos iniciados en esa fecha
     */
    public Collection<Tratamiento> SearchByEndDate(LocalDate startDate);

    /*
     * Este metodo es usado para buscar tratamientos por el id de la mascota.
     *
     * @param es el id de la mascota del que se quiere buscar los tratamientos.
     * 
     * @return Una coleccion de los tratamientos de la mascota
     */
    public Collection<Tratamiento> SearchByMascota(Long mascotaId);

    /*
     * Este metodo es usado para buscar tratamientos por el id del veterinario.
     *
     * @param es el id del veterinario del que se quiere buscar los tratamientos.
     * 
     * @return Una coleccion de los tratamientos de asociados al veterianrio
     */
    public Collection<Tratamiento> SearchByVeterinario(Long veterianrioId);

    /*
     * Este metodo es usado para buscar tratamientos por el id de la droga.
     *
     * @param es el id de la droga de la que se quiere buscar los tratamientos.
     * 
     * @return Una coleccion de los tratamientos de asociados a la droga
     */
    public Collection<Tratamiento> SearchByDroga(Long drogaId);

    /**
     * Este metodo es usado para obtener la información de todos los tratamientos.
     *
     * @return La colección de todos los tratamientos.
     */
    public Collection<Tratamiento> SearchAll();

    /**
     * Este metodo es usado para agregar un nuevo tratamiento al sistema.
     *
     * @param tratamiento El objeto Tratamiento que se desea agregar al sistema.
     *
     */
    public void addTratamiento(Tratamiento tratamiento);

    /**
     * Este metodo es usado para borrar un tratamiento del sistema.
     *
     * @param identificacion El ID del tratamiento que se desea borrar.
     */
    public void deleteById(Long identificacion);

    /**
     * Este método se utiliza para actualizar un tratamiento en el sistema.
     *
     * @param tratamiento El objeto Tratamiento que se desea actualizar en el
     *                    sistema.
     */
    public void update(Tratamiento tratamiento);
}
