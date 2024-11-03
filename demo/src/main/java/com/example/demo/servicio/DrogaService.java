package com.example.demo.servicio;

import java.util.Optional;

import java.util.List;
import com.example.demo.model.Droga;

public interface DrogaService {
    
    /**
     * Este metodo es usado para obtener la información de todos las drogas.
     *
     * @return La colección de todas las drogas.
     */
    public List<Droga> searchAllDrogas();
    
    /**
     * Este metodo es usado para buscar drogas por su id.
     *
     * @param id El ID con el que es identificada las droga.
     * @return EL objeto Droga representado a la droga con el ID dado
     *         o null si no existe
     */
    public Optional<Droga> searchDrogaById(Long id);

    /**
     * Este metodo es usado para agregar una nueva Droga al sistema.
     *
     * @param cliente El objeto Droga que se desea agregar al sistema.
     *
     */
    public Optional<Droga> addDroga(Droga droga);

    /**
     * Este metodo es usado para borrar una Droga del sistema.
     *
     * @param id El ID de la Droga que se desea borrar.
     */
    public boolean removeById(Long id);

    /**
     * Este método se utiliza para actualizar una Droga en el sistema.
     *
     * @param cliente El objeto Droga que se desea actualizar en el sistema.
     */
    public Optional<Droga> updateById(Long id, Droga droga);

    /**
     * Este metodo es usado para buscar drogas por su nombre.
     *
     * @param nombre El nombre con el que es identificado la droga.
     * @return La colección de todas las drogas que contengan el nombre dado
     */
    public List<Droga> searchByNombre(String nombre);
    
}
