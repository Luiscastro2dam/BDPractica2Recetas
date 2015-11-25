package com.example.clase.bdpractica3.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.clase.bdpractica3.Tablas.Recetas;
import com.example.clase.bdpractica3.Tablas.RelaIngRec;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clase on 24/11/2015.
 */
public class GestorRelacion {
    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorRelacion(Context c) {
        abd = new Ayudante(c);
    }
    public void open() {
        bd = abd.getWritableDatabase();
    }
    public void openRead() {
        bd = abd.getReadableDatabase();
    }
    public void close() {
        abd.close();
    }
    public long insertRelacion(RelaIngRec c){
        ContentValues cv = new ContentValues();
        cv.put(Recetario.TablaRelaIngreRece.IDRECETA, c.getIreceta());
        cv.put(Recetario.TablaRelaIngreRece.IDINGREDIENTE, c.getIdIngrediente());
        cv.put(Recetario.TablaRelaIngreRece.CANTIDAD, c.getCantidad());
        return bd.insert(Recetario.TablaRelaIngreRece.TABLA, null, cv);
    }
    public List<RelaIngRec> selectCantidades(Recetas receta){
        String condicion = Recetario.TablaRelaIngreRece.IDRECETA + " = ?";
        String[] argumentos = { Long.toString(receta.getIdReceta()) };
        Cursor cursor = bd.query(Recetario.TablaRelaIngreRece.TABLA, null, condicion, argumentos, null, null, null);
        cursor.moveToFirst();
        List<RelaIngRec> all = new ArrayList<>();
        RelaIngRec recetaIngrediente;
        while (!cursor.isAfterLast()) {
            recetaIngrediente = getRow(cursor);
            all.add(recetaIngrediente);
            cursor.moveToNext();
        }
        cursor.close();
        return all;
    }
    public RelaIngRec getRow(Cursor c) {
        RelaIngRec relacion = new RelaIngRec();
        relacion.setIdRelacion(c.getLong(c.getColumnIndex(Recetario.TablaRelaIngreRece._ID)));
        relacion.setIreceta(c.getLong(c.getColumnIndex(Recetario.TablaRelaIngreRece.IDRECETA)));
        relacion.setIdIngrediente(c.getLong(c.getColumnIndex(Recetario.TablaRelaIngreRece.IDINGREDIENTE)));
        relacion.setCantidad(c.getString(c.getColumnIndex(Recetario.TablaRelaIngreRece.CANTIDAD)));
        return relacion;
    }
}
