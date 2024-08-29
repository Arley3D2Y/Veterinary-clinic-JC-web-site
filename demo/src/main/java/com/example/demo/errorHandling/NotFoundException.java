package com.example.demo.errorHandling;

public class NotFoundException extends RuntimeException {
    private Long id;
    private String cedula;

    public NotFoundException() {
    }

    public NotFoundException(Long id) {
        this.id = id;
    }

    public NotFoundException(String ced) {
        this.cedula = ced;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCed() {
        return cedula;
    }

    public void setCed(String ced) {
        this.cedula = ced;
    }

}