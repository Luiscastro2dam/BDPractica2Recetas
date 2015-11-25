package com.example.clase.bdpractica3.Tablas;

/**
 * Created by Clase on 16/11/2015.
 */
public class Utensilio {
    private long idUtensilio;
    private String nombre;

    public Utensilio(long idUtensilio, String nombre) {
        this.idUtensilio = idUtensilio;
        this.nombre = nombre;
    }

    public long getIdUtensilio() {
        return idUtensilio;
    }

    public void setIdUtensilio(long idUtensilio) {
        this.idUtensilio = idUtensilio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Utensilio{" +
                "idUtensilio=" + idUtensilio +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
