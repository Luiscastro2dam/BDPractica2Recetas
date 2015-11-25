package com.example.clase.bdpractica3.Tablas;

/**
 * Created by Clase on 16/11/2015.
 */
public class Categoria {
    private long idCategoria;
    private String nombre;

    public Categoria(String nombre, long idCategoria) {
        this.nombre = nombre;
        this.idCategoria = idCategoria;
    }

    public long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "idCategoria=" + idCategoria +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

