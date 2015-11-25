package com.example.clase.bdpractica3.Tablas;

/**
 * Created by Clase on 16/11/2015.
 */
public class RelaIngRec {
    private long idRelacion;
    private long idIngrediente;
    private long ireceta;
    private String cantidad;

    public RelaIngRec(long idRelacion, long idUtensilio, long ireceta, String cantidad) {
        this.idIngrediente = idUtensilio;
        this.ireceta = ireceta;
        this.cantidad = cantidad;
    }
    public  RelaIngRec(){

    }
    public RelaIngRec(long idReceta, long idIngrediente, String cantidad) {
        this.idRelacion = 0;
        this.ireceta = idReceta;
        this.idIngrediente = idIngrediente;
        this.cantidad = cantidad;
    }

    public long getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public long getIdRelacion() {
        return idRelacion;
    }

    public void setIdRelacion(long idRelacion) {
        this.idRelacion = idRelacion;
    }

    public long getIdUtensilio() {
        return idIngrediente;
    }

    public void setIdUtensilio(long idUtensilio) {
        this.idIngrediente = idUtensilio;
    }

    public long getIreceta() {
        return ireceta;
    }

    public void setIreceta(long ireceta) {
        this.ireceta = ireceta;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "RelaIngRec{" +
                "idRelacion=" + idRelacion +
                ", idUtensilio=" + idIngrediente +
                ", ireceta=" + ireceta +
                ", cantidad=" + cantidad +
                '}';
    }
}
