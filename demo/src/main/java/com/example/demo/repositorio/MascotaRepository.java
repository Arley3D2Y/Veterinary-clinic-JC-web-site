package com.example.demo.repositorio;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.entidad.Mascota;
import org.springframework.stereotype.Repository;

@Repository
public class MascotaRepository {

        private Map<Integer, Mascota> data = new HashMap<>();

        public MascotaRepository() {
                data.put(1, new Mascota(1, "Toby", "Macho", "Birmano", "2017-11-13",
                                "https://content.elmueble.com/medio/2023/04/12/gato-birmano_40aca551_230412112429_900x900.jpg"));
                data.put(2, new Mascota(2, "Manchas", "Macho", "Persa", "2020-08-01",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                data.put(3, new Mascota(3, "Orion", "Macho", "Burmés", "2019-06-13",
                                "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
                data.put(4, new Mascota(4, "Linsy", "Hembra", "Ragdoll", "2013-09-14",
                                "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
                data.put(5, new Mascota(5, "Trixy", "Hembra", "Siberiano", "2020-06-19",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));
        }

        // metodo para buscar una mascota por id
        public Mascota findById(int id) {
                return data.get(id);
        }

        // metodo para obtener la información de todas las mascotas
        public Collection<Mascota> findAll() {
                return data.values();
        }

        // metodo para agregar una nueva mascota
        public void addMascota(Mascota mascota) {
                int tam = data.size();
                int lastId = data.get(tam).getId();
                mascota.setId(lastId + 1);
                data.put(mascota.getId(), mascota);
        }

        // metodo para borrar una mascota
        public void deleteById(int id) {
                data.remove(id);
        }

        // metodo para actualizar una mascota
        public void update(Mascota mascota) {
                data.put(mascota.getId(), mascota);
        }

}
