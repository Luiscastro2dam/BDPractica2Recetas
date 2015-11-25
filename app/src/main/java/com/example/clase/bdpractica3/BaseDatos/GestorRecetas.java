package com.example.clase.bdpractica3.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.clase.bdpractica3.Tablas.Recetas;
import com.example.clase.bdpractica3.Tablas.RelaIngRec;

import java.util.ArrayList;
import java.util.List;
/*
Cursor c = db.query(
    "Quotes",  //Nombre de la tabla
    null,  //Lista de Columnas a consultar
    null,  //Columnas para la clausula WHERE
    null,  //Valores a comparar con las columnas del WHERE
    null,  //Agrupar con GROUP BY
    null,  //Condición HAVING para GROUP BY
    null  //Clausula ORDER BY
    );
    String columns[] = new String[]{ColumnQuotes.BODY_QUOTES};
String selection = ColumnQuotes.AUTHOR_QUOTES + " = ? ";//WHERE author = ?
String selectionArgs[] = new String[]{"John D. Rockefeller"};
*/
public class GestorRecetas {
    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorRecetas(Context c) {
        abd = new Ayudante(c);
    }
    public void open() {
        bd = abd.getWritableDatabase();
    }

    public void close() {
        abd.close();
    }
    //----------------------Principal----------------------------//
    //este select devuelve el nombre y la imagen de nuestras recetas
    public List<Recetas> select() {
        List<Recetas> lista;
        lista = new ArrayList<Recetas>();
        Cursor cursor = bd.query(Recetario.TablaRecetas.TABLA, null,
                null, null, null, null, null, null);
        cursor.moveToFirst();
        Recetas receta;
        while (!cursor.isAfterLast()) {
            receta = getRow(cursor);
            lista.add(receta);
            cursor.moveToNext();
        }
        cursor.close();
        if(lista==null){
            lista.add(new Recetas());
            return lista;
        }
        return lista;
    }
    //-----------------
    public Recetas selectIdMostrarece(Recetas receta){
        String[] columnas = {Recetario.TablaRecetas._ID,Recetario.TablaRecetas.NOMBRE,Recetario.TablaRecetas.ELABORACION,Recetario.TablaRecetas.FOTO};
        String condicion = Recetario.TablaRecetas._ID + " = ?";
        String[] argumentos = { receta.getIdReceta() + "" };
        Cursor cursor = bd.query(Recetario.TablaRecetas.TABLA, columnas, condicion, argumentos, null, null, null);
        cursor.moveToFirst();
        Recetas ag = getRowDos(cursor);
        return ag;
    }

    public Recetas getRowDos(Cursor c) {
        Recetas p = new Recetas();
        p.setIdReceta(c.getLong(c.getColumnIndex(Recetario.TablaRecetas._ID)));
        p.setNombre(c.getString(c.getColumnIndex(Recetario.TablaRecetas.NOMBRE)));
        p.setElaboracion(c.getString(c.getColumnIndex(Recetario.TablaRecetas.ELABORACION)));
        p.setFoto(c.getString(c.getColumnIndex(Recetario.TablaRecetas.FOTO)));
        return p;
    }

    public Recetas getRow(Cursor c) {
        Recetas p = new Recetas();
        p.setIdReceta(c.getLong(c.getColumnIndex(Recetario.TablaRecetas._ID)));
        p.setNombre(c.getString(1));
        p.setFoto(c.getString(c.getColumnIndex(Recetario.TablaRecetas.FOTO)));
        return p;
    }
    public long insert(Recetas ag) {

        ContentValues valores = new ContentValues();
        valores.put(Recetario.TablaRecetas.NOMBRE,
                ag.getNombre());
        valores.put(Recetario.TablaRecetas.ELABORACION,
                ag.getElaboracion());
        valores.put(Recetario.TablaRecetas.FOTO,
                ag.getFoto());
      long id=  bd.insert(Recetario.TablaRecetas.TABLA,null,valores);
        return id;
    }
    public void deleteReceta(long id){
        String condicion = Recetario.TablaRecetas._ID + " = ?";
        String[] argumentos = { id + "" };
        bd.delete(Recetario.TablaRecetas.TABLA, condicion, argumentos);
    }

    public long insertRelacion(RelaIngRec c){
        ContentValues cv = new ContentValues();
        cv.put(Recetario.TablaRelaIngreRece.IDRECETA, c.getIreceta());
        cv.put(Recetario.TablaRelaIngreRece.IDINGREDIENTE, c.getIdIngrediente());
        cv.put(Recetario.TablaRelaIngreRece.CANTIDAD, c.getCantidad());
        return bd.insert(Recetario.TablaRelaIngreRece.TABLA, null, cv);
    }
    public int updateRecetas(Recetas receta){
        ContentValues valores = new ContentValues();
        valores.put(Recetario.TablaRecetas.FOTO, receta.getFoto());
        String condicion = Recetario.TablaRecetas._ID + " = ?";
        String[] argumentos = { receta.getIdReceta() + "" };
        int cuenta = bd.update(Recetario.TablaRecetas.TABLA, valores,
                condicion, argumentos);
        return cuenta;
    }

}
