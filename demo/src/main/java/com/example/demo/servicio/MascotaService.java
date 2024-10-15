package com.example.demo.servicio;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Mascota;
import com.example.demo.model.Tratamiento;

public interface MascotaService {

    /**
     * Este metodo es usado para obtener la información de todas las mascotas.
     *
     * @return La colección de todas las mascotas.
     */
    public List<Mascota> searchAllMascotas();

    /**
     * Este metodo es usado para buscar mascotas por su id.
     *
     * @param id El ID con el que es identificada la mascota.
     * @return EL objeto Mascota representado a la mascota con el ID dado
     *         o null si no existe
     */
    public Optional<Mascota> searchMascotaById(Long id);

    /**
     * Este metodo es usado para agregar una nueva mascota.
     *
     * @param mascora El objeto Mascora que se desea agregar al sistema.
     *
     */
    public Optional<Mascota> addMascota(Long id, Mascota mascota);

    /**
     * Este metodo es usado para borrar una mascota del sistema.
     *
     * @param id El ID de la mascota que se desea borrar.
     */
    public boolean removeById(Long id);

    /**
     * Este método se utiliza para actualizar una mascota en el sistema.
     *
     * @param mascota El objeto Mascota que se desea actualizar en el sistema.
     */
    public Optional<Mascota> updateById(Long id, Mascota mascota);

    /**
     * Este metodo es usado para buscar clientes por su nombre.
     *
     * @param nombre El nombre con el que es identificado el cliente.
     * @return La colección de todos los clientes que contengan el nombre dado
     */
    public List<Mascota> searchByNombre(String nombre);

        /**
     * Este método se utiliza para actualizar un tratamiento en el sistema.
     *
     * @param identificacion El ID del tratamiento que se desea borrar.
     *                    sistema.
     */
    public List<Mascota> searchByClienteId(Long id);

    public List<Tratamiento> findTreatmentsByPetId(Long id);
}
