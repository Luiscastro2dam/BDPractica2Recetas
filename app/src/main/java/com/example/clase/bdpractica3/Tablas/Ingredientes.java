package com.example.clase.bdpractica3.Tablas;

/**
 * Created by Clase on 16/11/2015.
 */
public class Ingredientes {
    private long idIngredientes;
    private String nombre;

    public Ingredientes(long idIngredientes, String nombre) {
        this.idIngredientes = idIngredientes;
        this.nombre = nombre;
    }
    public Ingredientes(String nombre) {
        this.idIngredientes = 0;
        this.nombre = nombre;
    }
    public Ingredientes(){

    }

    public long getIdIngredientes() {
        return idIngredientes;
    }

    public void setIdIngredientes(long idIngredientes) {
        this.idIngredientes = idIngredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Ingredientes{" +
                "idIngredientes=" + idIngredientes +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
