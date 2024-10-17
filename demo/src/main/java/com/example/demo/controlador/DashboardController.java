package com.example.demo.controlador;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.servicio.TratamientoService;
import com.example.demo.servicio.VeterinarioService;
import com.example.demo.servicio.MascotaService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private TratamientoService tratamientoService;
    
    @Autowired
    private VeterinarioService veterinarioService;
    
    @Autowired
    private MascotaService mascotaService;

    // Método para obtener el primer día del mes actual
    private Date getPrimerDiaMes() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    @GetMapping("/")
    public Map<String, Object> getDashboardData() {
        // Obtenemos el primer día del mes actual
        Date fechaInicioMes = getPrimerDiaMes();

        // Creamos un mapa para los datos del dashboard
        Map<String, Object> dashboardData = new HashMap<>();

        // Cantidad total de tratamientos administrados desde el inicio del mes
        dashboardData.put("totalTratamientos", tratamientoService.Count(fechaInicioMes));
        
        // Cantidad de tratamientos por tipo de medicamento administrado
        dashboardData.put("tratamientosPorMedicamento", tratamientoService.getTratamientosPorMedicamento());
        
        // Cantidad de veterinarios activos
        dashboardData.put("veterinariosActivos", veterinarioService.countVeterinariosActivos());
        
        // Cantidad de veterinarios inactivos
        dashboardData.put("veterinariosInactivos", veterinarioService.countVeterinariosInactivos());
        
        // Cantidad total de mascotas en la veterinaria
        dashboardData.put("totalMascotas", mascotaService.countMascotas());
        
        // Cantidad de mascotas activas (en tratamiento) en la veterinaria
        dashboardData.put("mascotasActivas", mascotaService.countMascotasEnTratamiento());

        // Top 3 tratamientos con más unidades vendidas
        dashboardData.put("topTratamientos", tratamientoService.getTopTratamientos());

        // Retornamos los datos del dashboard
        return dashboardData;
    }
}
