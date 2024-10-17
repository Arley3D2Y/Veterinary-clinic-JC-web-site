package com.example.demo.model;

public class FinanzasDTO {
    private double ventasTotales;
    private double gananciasTotales;

    // Constructor
    public FinanzasDTO(double ventasTotales, double gananciasTotales) {
        this.ventasTotales = ventasTotales;
        this.gananciasTotales = gananciasTotales;
    }

    // Getters y setters
    public double getVentasTotales() {
        return ventasTotales;
    }

    public void setVentasTotales(double ventasTotales) {
        this.ventasTotales = ventasTotales;
    }

    public double getGananciasTotales() {
        return gananciasTotales;
    }

    public void setGananciasTotales(double gananciasTotales) {
        this.gananciasTotales = gananciasTotales;
    }
}
