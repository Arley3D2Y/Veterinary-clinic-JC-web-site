package com.example.demo.errorHandling;

public class NotFoundException extends RuntimeException {
    private Long id;
    private String cedula;
    private String correo;

    // Constructor vacío
    public NotFoundException() {
    }

    // Constructor con correo y cédula
    public NotFoundException(String correo, String cedula) {
        this.correo = correo;
        this.cedula = cedula;
    }

    // Constructor con ID
    public NotFoundException(Long id) {
        this.id = id;
    }

    // Getters y Setters
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
