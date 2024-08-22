package com.example.demo.repositorio;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import com.example.demo.entidad.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteRepository {

    private Map<Integer, Cliente> data = new HashMap<>();

    //repositorio
    public ClienteRepository() {

        data.put(1, new Cliente(1, "Jose", "1087675675", "JY5se@hotmail.com", "3119378271"));
        data.put(2, new Cliente(2, "Luis", "9878767847", "luisillo34@gmail.com", "382873281"));
        data.put(3, new Cliente(3, "Juliana", "1038470394", "julix@gmail.com", "3982728387"));
        data.put(4, new Cliente(4, "Carlos", "30948261783", "charles43@hotmail.com", "347898678"));
        data.put(5, new Cliente(5, "Camila", "8392041628", "kmiP7@gmail.com", "368978678"));
    }

    //metodos

    // metodo para obtener la información de todos los clientes
      public Collection<Cliente> findAll() {
        return data.values();
    }

    //metodo para buscar por id
    public Cliente findById(int id) {
        return data.get(id);
    }

    //metodo para buscar por cedula
    public Cliente findByCedula(String cedula) {
        for (Map.Entry<Integer, Cliente> entry : data.entrySet()) {
            if (entry.getValue().getCedula().equals(cedula)) {
                return entry.getValue();
            }
        }
        return null;
    }

    //metodo para agregar un nuevo cliente
    public void addCliente(Cliente cliente) {
        int tam = data.size();
        int lastId = data.get(tam).getId();
        cliente.setId(lastId + 1);
        data.put(cliente.getId(), cliente);
    }

    //metodo para borrar un cliente
    public void deleteById(int id) {
        data.remove(id);
    }

    //metodo para actualizar la info de un cliente
    public void update(Cliente cliente) {
        data.put(cliente.getId(), cliente);
    }

}
