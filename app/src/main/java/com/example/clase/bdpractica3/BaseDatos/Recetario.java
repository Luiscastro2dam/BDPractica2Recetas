package com.example.clase.bdpractica3.BaseDatos;

import android.provider.BaseColumns;

public class Recetario {

    private Recetario(){
    }
    public static abstract class TablaRecetas implements
            BaseColumns{
        public static final String TABLA = "Recetas";
        public static final String NOMBRE = "nombre";
        public static final String ELABORACION = "elaboracion";
        public static final String FOTO = "foto";

    }
     public static abstract class TablaIngredientes implements
            BaseColumns{
        public static final String TABLA = "Ingredientes";
        public static final String NOMBRE = "nombre";
    }
    public static abstract class TablaRelaIngreRece implements
            BaseColumns{
        public static final String TABLA = "RelaIngreRece";
        public static final String IDINGREDIENTE = "idIngrediente";
        public static final String IDRECETA = "idReceta";
        public static final String CANTIDAD = "cantidad";
    }
/*
    ////---------------------Subida de nota------------------------////

    public static abstract class TablaCategoria implements
            BaseColumns{
        public static final String TABLA = "Categorias";
        public static final String IDCATEGORIA = "idCategoria";
        public static final String NOMBRE = "Nombre";
    }
    public static abstract class TablaUtensilio implements
            BaseColumns{
        public static final String TABLA = "Utensilios";
        public static final String IDUTENSILIO = "idUtensilio";
        public static final String NOMBRE = "Nombre";
    }*/
}
