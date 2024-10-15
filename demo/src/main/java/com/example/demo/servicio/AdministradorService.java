package com.example.demo.servicio;

import java.util.List;
import java.util.Optional;
import com.example.demo.model.Administrador;

public interface AdministradorService {

    /**
     * Este metodo es usado para obtener la información de todos los veterinarios.
     *
     * @return La colección de todos los veterinarios.
     */
    public List<Administrador> searchAllAdministradores();

    /**
     * Este metodo es usado para buscar veterinarios por su id.
     *
     * @param identificacion El ID con el que es identificado el veterinario.
     * @return EL objeto Veterianrio representado al veterianrio con el ID dado
     *         o null si no existe
     */
    public Optional<Administrador> searchAdministradorById(Long identificacion);

    /**
     * Este metodo es usado para buscar un veterinario por su nombre.
     *
     * @param user El nombre con el que es identificado el veterinario.
     * @return La colección de todos los veterinarios que contengan el nombre dado
     */
    public Optional<Administrador> searchByUser(String user);

}
