package com.example.demo.DTO;

public class GenericoDTO {
    private String nombre;
    private Number cantidad;

    public GenericoDTO() {}

    public GenericoDTO(String nombre, Number cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Number getCantidad() {
        return cantidad;
    }

    public void setCantidad(Number cantidad) {
        this.cantidad = cantidad;
    }
}
