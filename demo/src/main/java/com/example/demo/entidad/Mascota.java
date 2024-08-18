package com.example.demo.entidad;

public class Mascota{

    private Integer id;
    private String nombre;
    private String sexo;
    private String raza;
    private String fechaNacimiento;
    private String fotoString;

    public Mascota(Integer id, String nombre, String sexo, String raza, String fechaNacimiento, String fotoString) {
        this.id = id;
        this.nombre = nombre;
        this.sexo = sexo;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.fotoString = fotoString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {  
        this.sexo = sexo;
    }

    public String getRaza() {
        return raza;
    }   

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }   

    public String getFotoString() {
        return fotoString;
    }

    public void setFotoString(String fotoString) {
        this.fotoString = fotoString;
    }

}
