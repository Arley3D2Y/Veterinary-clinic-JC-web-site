package com.example.demo.servicio;
import com.example.demo.model.Cliente;
import java.util.List;

public interface ClienteService {

    /**
     * Este metodo es usado para obtener la información de todos los clientes.
     *
     * @return La colección de todos los clientes.
     */
    public List<Cliente> SearchAll();
    
    /**
     * Este metodo es usado para buscar clientes por su id.
     *
     * @param identificacion El ID con el que es identificado el cliente.
     * @return EL objeto Cliente representado al cliente con el ID dado
     *         o null si no existe
     */
    public Cliente SearchById(Long identificacion);


    /**
     * Este metodo es usado para buscar un cliente por su cedula.
     *
     * @param cedula El ID con el que es identificado el cliente.
     * @return El objeto Cliente representado al cliente con la cedula dada
     *         o null si no existe
     */
    public Cliente SearchByCedula(String cedula);




    /**
     * Este metodo es usado para agregar un nuevo cliente al sistema.
     *
     * @param cliente El objeto Cliente que se desea agregar al sistema.
     *
     */
    public void addCliente(Cliente cliente);

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
    public void update(Cliente cliente);

    /**
     * Este metodo es usado para buscar clientes por su nombre.
     *
     * @param nombre El nombre con el que es identificado el cliente.
     * @return La colección de todos los clientes que contengan el nombre dado
     */
    public List<Cliente> buscarPorNombre(String nombre);

}
