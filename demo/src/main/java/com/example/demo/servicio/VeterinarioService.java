package com.example.demo.servicio;

import java.util.Collection;

import com.example.demo.model.Veterinario;

public interface VeterinarioService {

    /**
     * Este metodo es usado para buscar veterinarios por su id.
     *
     * @param identificacion El ID con el que es identificado el veterinario.
     * @return EL objeto Veterianrio representado al veterianrio con el ID dado
     *         o null si no existe
     */
    public Veterinario SearchById(Long identificacion);


    /**
     * Este metodo es usado para buscar un veterinario por su cedula.
     *
     * @param cedula El ID con el que es identificado el veterinario.
     * @return El objeto Veterinario representado al veterinario con la cedula dada
     *         o null si no existe
     */
    public Veterinario SearchByCedula(String cedula);


    /**
     * Este metodo es usado para buscar un veterinario por su correo.
     * 
     * @param correo El ID con el que es identificado el veterinario.
     * @return El objeto Veterinario representado al veterinario con la cedula dada
     *         o null si no existe
     */
    public Veterinario SearchByCorreo(String correo);


    /**
     * Este metodo es usado para obtener la información de todos los veterinarios.
     *
     * @return La colección de todos los veterinarios.
     */
    public Collection<Veterinario> SearchAll();

    /**
     * Este metodo es usado para agregar un nuevo veterinario al sistema.
     *
     * @param veterinario El objeto Veterinario que se desea agregar al sistema.
     *
     */
    public void addVeterinario(Veterinario veterinario);

    /**
     * Este metodo es usado para borrar un veterinario del sistema.
     *
     * @param identificacion El ID del veterinario que se desea borrar.
     */
    public void deleteById(Long identificacion);

    /**
     * Este método se utiliza para actualizar un veterinario en el sistema.
     *
     * @param veterinario El objeto Veterinario que se desea actualizar en el sistema.
     */
    public void update(Veterinario veterinario);

}
