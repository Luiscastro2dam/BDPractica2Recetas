package com.example.clase.bdpractica3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clase.bdpractica3.BaseDatos.GestorIngredientes;
import com.example.clase.bdpractica3.BaseDatos.GestorRecetas;
import com.example.clase.bdpractica3.Tablas.Recetas;
import com.example.clase.bdpractica3.Tablas.RelaIngRec;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clase on 18/11/2015.
 */
public class Agregar extends AppCompatActivity {
    private List<String[]> ingredientesReceta;
    private GestorRecetas gestor;
    private GestorIngredientes gestorIngre;
    private EditText tvNombre,tvElaboracion;
    private TextView tvIngreAgre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar);
        tvNombre=(EditText)findViewById(R.id.tvNombre);
        tvElaboracion=(EditText)findViewById(R.id.tvElaboracion);
        tvIngreAgre=(TextView)findViewById(R.id.tvIngreAgre);
        gestor = new GestorRecetas(this);
        gestorIngre = new GestorIngredientes(this);
        ingredientesReceta = new ArrayList<>();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if(requestCode == 1){
                String[] ingredient = data.getExtras().getStringArray("ingreLista");
                System.out.println("luiskiko"+ingredient.toString());
                tvIngreAgre.setText("");
                for (int i = 0; i < ingredient.length; i+=2) {
                    tvIngreAgre.append(ingredient[i]+" "+ingredient[i+1]+"\n");
                    ingredientesReceta.add(new String[]{ingredient[i],ingredient[i+1]});
                }
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        gestor.open();
        gestorIngre.open();
        Log.v("APLICACION", "Resume Alta Open");
    }
    public void btAgregar(View v){

        Recetas receta = new Recetas();
        receta.setNombre(tvNombre.getText().toString());
        receta.setElaboracion(tvElaboracion.getText().toString());
        receta.setFoto(tvElaboracion.getText().toString());
        long idReceta= gestor.insert(receta);
        for (String[] ing : ingredientesReceta) {
            long idIngrediente=gestorIngre.selectIdIngredienteNombre(ing[0]);
            RelaIngRec c = new RelaIngRec(idReceta,idIngrediente,ing[1]);
            gestor.insertRelacion(c);
            Toast.makeText(this, "Receta guardada con Exito", Toast.LENGTH_SHORT).show();
            finish();
        }
        gestor.close();

    }
    public  void btingredientes(View v){
        Intent a = new Intent(this,AgregarIngredientes.class);
        startActivityForResult(a, 1);
    }
    @Override
    protected void onPause() {
        gestor.close();
        Log.v("APLICACION", "Resume Principal Close");
        super.onPause();
    }

}
