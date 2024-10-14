package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "mascota") // Corregido el nombre de la tabla
public class Mascota {

    @Id
    @GeneratedValue
    private Long id; // Cambiado de Integer a Long para coincidir con Cliente
    private String nombre;
    private String edad;
    private String peso;
    private String estado;
    private String enfermedad;
    private String sexo;
    private String raza;
    private String fotoString;

    @JsonIgnore
    @OneToMany(mappedBy = "mascota")
    private List<Tratamiento> tratamientos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente; // Cambiado de duenho a cliente

    public Mascota(String nombre, String edad, String peso, String estado, String enfermedad, String sexo, String raza, String fotoString) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.estado = estado;
        this.enfermedad = enfermedad;
        this.sexo = sexo;
        this.raza = raza;
        this.fotoString = fotoString;
        this.tratamientos = new ArrayList<>();
        this.cliente = null;
    }

    public Mascota() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
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

    public String getFotoString() {
        return fotoString;
    }

    public void setFotoString(String fotoString) {
        this.fotoString = fotoString;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void agregarTratamiento(Tratamiento tratamiento) {
        if (!this.tratamientos.contains(tratamiento)) {
            tratamiento.setMascota(this);
            this.tratamientos.add(tratamiento);
        }
    }

    public void eliminarTratamiento(Tratamiento tratamiento) {
        if (this.tratamientos.contains(tratamiento)) {
            this.tratamientos.remove(tratamiento);
        }
    }

    // Método para actualizar un tratamiento existente
    public void actualizarTratamiento(Long id, Tratamiento tratamientoActualizado) {
        for (int i = 0; i < tratamientos.size(); i++) {
            Tratamiento tratamiento = tratamientos.get(i);
            if (tratamiento.getId().equals(id)) { // Asumiendo que Tratamiento tiene un método getId()
                tratamientos.set(i, tratamientoActualizado);
                tratamientoActualizado.setMascota(this); // Asegúrate de establecer la relación inversa
                return;
            }
        }
    }

    // Método para obtener todos los tratamientos
    public List<Tratamiento> obtenerTratamientos() {
        return new ArrayList<>(tratamientos); // Retornar una copia de la lista para evitar modificaciones externas
    }
}
