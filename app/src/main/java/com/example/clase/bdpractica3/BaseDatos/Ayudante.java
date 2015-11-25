package com.example.clase.bdpractica3.BaseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Clase on 14/11/2015.
 */
public class Ayudante extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "rece.sqlite";
    public static final int DATABASE_VERSION = 9;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    //se encargara de crear las tablas si no existen
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql="create table "+ Recetario.TablaRecetas.TABLA+
                " ("+ Recetario.TablaRecetas._ID+
                " integer primary key autoincrement, "+
                Recetario.TablaRecetas.NOMBRE+" text, "+
                Recetario.TablaRecetas.ELABORACION+" text, "+
                Recetario.TablaRecetas.FOTO+" text) ";
        db.execSQL(sql);
        sql="create table "+Recetario.TablaIngredientes.TABLA+
                " ("+ Recetario.TablaIngredientes._ID+
                " integer primary key autoincrement, "+
                Recetario.TablaIngredientes.NOMBRE+" text)";
        db.execSQL(sql);
        sql="create table "+Recetario.TablaRelaIngreRece.TABLA+
                " ("+ Recetario.TablaRelaIngreRece._ID+
                " integer primary key autoincrement, "+
                Recetario.TablaRelaIngreRece.IDINGREDIENTE+" text, "+
                Recetario.TablaRelaIngreRece.IDRECETA+" text," +
                Recetario.TablaRelaIngreRece.CANTIDAD+" text)";
        db.execSQL(sql);
        /*
        sql="create table "+Recetario.TablaCategoria.TABLA+
                " ("+ Recetario.TablaCategoria.IDCATEGORIA+
                " integer primary key autoincrement, "+
                Recetario.TablaCategoria.NOMBRE+" text)";
        db.execSQL(sql);
        sql="create table "+Recetario.TablaUtensilio.TABLA+
                " ("+ Recetario.TablaUtensilio.IDUTENSILIO+
                " integer primary key autoincrement, "+
                Recetario.TablaUtensilio.NOMBRE+" text)";
        db.execSQL(sql);
        */
//--------------Insertamos datos de inicio---------------------------------------//

      /*  db.execSQL("INSERT INTO Categorias(idCategoria, nombre) VALUES(1,'Entrantes')");
        db.execSQL("INSERT INTO Utensilios(idUtensilio, nombre) VALUES(1,'Entrantes')");
      */
    /*    db.execSQL("INSERT INTO Recetas(idReceta,nombre,elaboracion,foto)" +
                             " VALUES(1,'Tortilla','Preparamos todo llsls','foto.jpg')");
        db.execSQL("INSERT INTO Recetas(idReceta,nombre,elaboracion,foto)" +
                " VALUES(2,'Pizza','Preparamos todo llsls','foto.jpg')");
        db.execSQL("INSERT INTO Recetas(idReceta,nombre,elaboracion,foto)" +
                " VALUES(3,'Bacalao','Preparamos todo llsls','foto.jpg')");
        db.execSQL("INSERT INTO Recetas(idReceta,nombre,elaboracion,foto)" +
                " VALUES(4,'Espaguetis','Preparamos todo llsls','foto.jpg')");
        db.execSQL("INSERT INTO Recetas(idReceta,nombre,elaboracion,foto)" +
                " VALUES(5,'Pollo','Preparamos todo llsls','foto.jpg')");

        db.execSQL("INSERT INTO Ingredientes(idIngrediente, nombre) VALUES(1,'Patatas')");
        db.execSQL("INSERT INTO Ingredientes(idIngrediente, nombre) VALUES(2,'Cebollas')");
        db.execSQL("INSERT INTO Ingredientes(idIngrediente, nombre) VALUES(3,'Ajos')");
        db.execSQL("INSERT INTO Ingredientes(idIngrediente, nombre) VALUES(4,'Pimientos')");
*/
    }
    // las actualizara
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="drop table if exists "+ Recetario.TablaRecetas.TABLA;
        db.execSQL(sql);
        String sql1="drop table if exists "+ Recetario.TablaIngredientes.TABLA;
        db.execSQL(sql1);
        String sql2="drop table if exists "+ Recetario.TablaRelaIngreRece.TABLA;
        db.execSQL(sql2);
        onCreate(db);
    }

}
