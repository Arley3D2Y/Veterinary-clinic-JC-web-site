package com.example.demo.model;

public class VeterinarioEstadisticasDTO {
    private Number activos;
    private Number inactivos;

    public VeterinarioEstadisticasDTO() {}

    public VeterinarioEstadisticasDTO(Number activos, Number inactivos) {
        this.activos = activos;
        this.inactivos = inactivos;
    }

    public Number getActivos() {
        return activos;
    }

    public void setActivos(Number activos) {
        this.activos = activos;
    }

    public Number getInactivos() {
        return inactivos;
    }

    public void setInactivos(Number inactivos){
        this.inactivos = inactivos;
    }
}
