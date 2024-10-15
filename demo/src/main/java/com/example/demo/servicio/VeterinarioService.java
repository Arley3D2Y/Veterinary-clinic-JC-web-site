package com.example.demo.servicio;

import java.util.List;
import java.util.Optional;
import com.example.demo.model.Veterinario;

public interface VeterinarioService {

    /**
     * Este metodo es usado para obtener la información de todos los veterinarios.
     *
     * @return La colección de todos los veterinarios.
     */
    public List<Veterinario> searchAllVeterinarios();

    /**
     * Este metodo es usado para buscar veterinarios por su id.
     *
     * @param identificacion El ID con el que es identificado el veterinario.
     * @return EL objeto Veterianrio representado al veterianrio con el ID dado
     *         o null si no existe
     */
    public Optional<Veterinario> searchVeterinarioById(Long identificacion);

    /**
     * Este metodo es usado para agregar un nuevo veterinario al sistema.
     *
     * @param veterinario El objeto Veterinario que se desea agregar al sistema.
     *
     */
    public Optional<Veterinario> addVeterinario(Veterinario veterinario);
    
    /**
     * Este metodo es usado para borrar un veterinario del sistema.
     *
     * @param identificacion El ID del veterinario que se desea borrar.
     */
    public boolean removeById(Long id);

    /**
     * Este método se utiliza para actualizar un veterinario en el sistema.
     *
     * @param veterinario El objeto Veterinario que se desea actualizar en el sistema.
     */
    public Optional<Veterinario> updateById(Long id, Veterinario veterinario);

    /**
     * Este metodo es usado para buscar un veterinario por su nombre.
     * 
     * @param nombre El nombre con el que es identificado el veterinario.
     * @return La colección de todos los veterinarios que contengan el nombre dado
     *         o null si no existe
     */
    public List<Veterinario> searchByNombre(String nombre);

    /**
     * Este metodo es usado para buscar un veterinario por su cedula.
     *
     * @param cedula El ID con el que es identificado el veterinario.
     * @return El objeto Veterinario representado al veterinario con la cedula dada
     *         o null si no existe
     */
    public Optional<Veterinario> searchByCedula(String cedula);

    /**
     * Este metodo es usado para buscar un veterinario por su correo.
     * 
     * @param correo El ID con el que es identificado el veterinario.
     * @return El objeto Veterinario representado al veterinario con la cedula dada
     *         o null si no existe
     */
    public Optional<Veterinario> searchByCorreo(String correo);


}
