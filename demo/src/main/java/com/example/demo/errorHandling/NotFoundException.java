package com.example.demo.errorHandling;

public class NotFoundException extends RuntimeException {
    private int id;
    private String cedula;

    public NotFoundException(int id) {
        this.id = id;
    }

    public NotFoundException(String ced) {
        this.cedula = ced;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCed() {
        return cedula;
    }

    public void setCed(String ced) {
        this.cedula = ced;
    }

}
