package com.example.clase.bdpractica3.Tablas;

import android.database.Cursor;

import java.io.Serializable;


public class Recetas implements Serializable {
    private long idReceta;
    private String nombre;
    private String elaboracion;
    private String foto;
    //constructor vacio
    public Recetas(){

    } //constructor

    public Recetas(String nombre, long idReceta, String elaboracion, String foto) {
        this.nombre = nombre;
        this.idReceta = idReceta;
        this.elaboracion = elaboracion;
        this.foto = foto;
    }

    public long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(long idReceta) {
        this.idReceta = idReceta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getElaboracion() {
        return elaboracion;
    }

    public void setElaboracion(String elaboracion) {
        this.elaboracion = elaboracion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }



    @Override
    public String toString() {
        return "Recetas{" +
                "idReceta=" + idReceta +
                ", nombre='" + nombre + '\'' +
                ", elaboracion='" + elaboracion + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
    public void set(Cursor c) {
        setIdReceta(c.getLong(0));
        setNombre(c.getString(1));
        setElaboracion(c.getString(2));
        setFoto(c.getString(3));
    }
}
