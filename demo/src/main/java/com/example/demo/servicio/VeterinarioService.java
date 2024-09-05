package com.example.demo.servicio;

import java.util.Collection;

import java.util.Optional;
import com.example.demo.model.Veterinario;

public interface VeterinarioService {

    /**
     * Este metodo es usado para buscar clientes por su id.
     *
     * @param identificacion El ID con el que es identificado el cliente.
     * @return EL objeto Cliente representado al cliente con el ID dado
     *         o null si no existe
     */
    public Optional<Veterinario> SearchById(Long identificacion);


    /**
     * Este metodo es usado para buscar un cliente por su cedula.
     *
     * @param cedula El ID con el que es identificado el cliente.
     * @return El objeto Cliente representado al cliente con la cedula dada
     *         o null si no existe
     */
    public Optional<Veterinario> SearchByCedula(String cedula);


    /**
     * Este metodo es usado para buscar un cliente por su correo.
     * 
     * @param correo El ID con el que es identificado el cliente.
     * @return El objeto Cliente representado al cliente con la cedula dada
     *         o null si no existe
     */
    public Optional<Veterinario> SearchByCorreo(String correo);


    /**
     * Este metodo es usado para obtener la información de todos los clientes.
     *
     * @return La colección de todos los clientes.
     */
    public Collection<Veterinario> SearchAll();

    /**
     * Este metodo es usado para agregar un nuevo cliente al sistema.
     *
     * @param cliente El objeto Cliente que se desea agregar al sistema.
     *
     */
    public void addCliente(Veterinario cliente);

    /**
     * Este metodo es usado para borrar un cliente del sistema.
     *
     * @param identificacion El ID del cliente que se desea borrar.
     */
    public void deleteById(Long identificacion);

    /**
     * Este método se utiliza para actualizar un cliente en el sistema.
     *
     * @param cliente El objeto Cliente que se desea actualizar en el sistema.
     */
    public void update(Veterinario cliente);

}
