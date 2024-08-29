package com.example.demo.servicio;

import java.util.Collection;
import java.util.Optional;
import com.example.demo.model.Mascota;

public interface MascotaService {

    /**
     * Este metodo es usado para buscar mascotas por su id.
     *
     * @param id El ID con el que es identificada la mascota.
     * @return EL objeto Mascota representado a la mascota con el ID dado
     *         o null si no existe
     */
    public Optional<Mascota> SearchById(Long id);

    /**
     * Este metodo es usado para obtener la información de todas las mascotas.
     *
     * @return La colección de todas las mascotas.
     */
    public Collection<Mascota> SearchAll();

    /**
     * Este metodo es usado para agregar una nueva mascota.
     *
     * @param mascora El objeto Mascora que se desea agregar al sistema.
     *
     */
    public void addMascota(Mascota mascota);

    /**
     * Este metodo es usado para borrar una mascota del sistema.
     *
     * @param id El ID de la mascota que se desea borrar.
     */
    public void deleteById(Long id);

    /**
     * Este método se utiliza para actualizar una mascota en el sistema.
     *
     * @param mascota El objeto Mascota que se desea actualizar en el sistema.
     */
    public void update(Mascota mascota);
}
