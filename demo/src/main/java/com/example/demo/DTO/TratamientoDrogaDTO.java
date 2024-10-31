package com.example.demo.DTO;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class TratamientoDrogaDTO {
    
    private String nombreDroga;
    
    @Min(0)
    private Integer cantidadTratamiento; // o Integer

    public TratamientoDrogaDTO(String nombreDroga, Integer cantidadTratamiento) {
        this.nombreDroga = nombreDroga;
        this.cantidadTratamiento = cantidadTratamiento;
    }
}