package com.example.demo.servicio;

import com.example.demo.entidad.Mascota;


@Service
public class MascotaServiceImpl implements MascotaService {

    @Autowired
    MascotaRepository repo;
    
    @Override
    public Mascota SearchById(int id) {
        return repo.FindById(id);
        
    }

    @Override
    public Collection<Mascota> SearchAll() {
        return repo.FindAll();
       
    }
}
