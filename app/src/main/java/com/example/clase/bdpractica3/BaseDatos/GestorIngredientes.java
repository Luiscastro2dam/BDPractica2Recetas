package com.example.clase.bdpractica3.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.clase.bdpractica3.Tablas.Ingredientes;

import java.util.ArrayList;
import java.util.List;


public class GestorIngredientes {
    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorIngredientes(Context c) {
        abd = new Ayudante(c);
    }
    public void open() {
        bd = abd.getWritableDatabase();
    }
    public void close() {
        abd.close();
    }
    public List<Ingredientes> selectIngredientes() {
        List<Ingredientes> lista;
        lista = new ArrayList<>();
        Cursor cursor = bd.query(Recetario.TablaIngredientes.TABLA, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        Ingredientes ingre;
        while (!cursor.isAfterLast()) {
            ingre = getRowIngredientes(cursor);
            lista.add(ingre);
            cursor.moveToNext();
        }
        cursor.close();
        if(lista==null){
            lista.add(new Ingredientes());
            return lista;
        }
        return lista;
    }
    //

    public String selectNombreIngredienteId(long id){
        String[] columnas = {Recetario.TablaIngredientes.NOMBRE};
        String condicion = Recetario.TablaIngredientes._ID + " = ?";
        String[] argumentos = { Long.toString(id) };
        Cursor cursor = bd.query(Recetario.TablaIngredientes.TABLA, columnas, condicion, argumentos, null, null, null);
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex(Recetario.TablaIngredientes.NOMBRE));
    }
    public long selectIdIngredienteNombre(String nombre){
        String[] columnas = {Recetario.TablaIngredientes._ID};
        String condicion = Recetario.TablaIngredientes.NOMBRE + " = ?";
        String[] argumentos = { nombre };
        Cursor cursor = bd.query(Recetario.TablaIngredientes.TABLA, columnas, condicion, argumentos, null, null, null);
        cursor.moveToFirst();
        return cursor.getLong(cursor.getColumnIndex(Recetario.TablaIngredientes._ID));
    }
    public Ingredientes getRowIngredientes(Cursor c) {
        Ingredientes p = new Ingredientes();
        p.setIdIngredientes(c.getLong(c.getColumnIndex(Recetario.TablaIngredientes._ID)));
        p.setNombre(c.getString(c.getColumnIndex(Recetario.TablaIngredientes.NOMBRE)));
        return p;
    }
    public long insert(Ingredientes ing){
        ContentValues cv = new ContentValues();
        cv.put(Recetario.TablaIngredientes.NOMBRE, ing.getNombre());
        return bd.insert(Recetario.TablaIngredientes.TABLA, null, cv);
    }
    public void deleteIngrediente(String nombre){
        String condicion = Recetario.TablaIngredientes.NOMBRE + " = ?";
        String[] argumentos = { nombre + "" };
        bd.delete(Recetario.TablaIngredientes.TABLA, condicion, argumentos);
    }
}
