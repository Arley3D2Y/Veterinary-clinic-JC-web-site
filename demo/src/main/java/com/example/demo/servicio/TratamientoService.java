package com.example.demo.servicio;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Tratamiento;

public interface TratamientoService {

    /**
     * Este metodo es usado para obtener la información de todos los tratamientos.
     *
     * @return La colección de todos los tratamientos.
     */
    public List<Tratamiento> searchAll();

    /*
     * Este metodo es usado para buscar tratamientos por su id.
     *
     * @param identificacion El ID con el que es identificado el tratamiento.
     * 
     * @return EL objeto Tratamiento representado al tratamiento con el ID dado
     * o null si no existe
     */
    public Optional<Tratamiento> searchById(Long identificacion);

        /**
     * Este metodo es usado para agregar un nuevo tratamiento al sistema.
     *
     * @param tratamiento El objeto Tratamiento que se desea agregar al sistema.
     *
     */
    public Optional<Tratamiento> addTratamiento(Long idp, Long idv, Tratamiento tratamiento);
        /**
     * Este metodo es usado para borrar un tratamiento del sistema.
     *
     * @param identificacion El ID del tratamiento que se desea borrar.
     */
    public boolean removeById(Long id);

    /**
     * Este método se utiliza para actualizar un tratamiento en el sistema.
     *
     * @param tratamiento El objeto Tratamiento que se desea actualizar en el
     *                    sistema.
     */
    public Optional<Tratamiento> updateById(Long id, Tratamiento tratamiento);

    public List<Tratamiento> searchByNombre(String name);
    
    public List<Tratamiento> getTratamientosPorVeterinario(Long id);

    public List<Tratamiento> getTratamientosPorMascota(Long id);

    public Number Count(Date o);

    public List<Tratamiento> getTratamientosPorMedicamento();
    
    public List<Tratamiento> getTopTratamientos();

}
