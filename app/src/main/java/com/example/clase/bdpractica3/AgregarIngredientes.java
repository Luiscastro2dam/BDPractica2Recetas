package com.example.clase.bdpractica3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.clase.bdpractica3.BaseDatos.GestorIngredientes;
import com.example.clase.bdpractica3.Tablas.Ingredientes;

import java.util.List;

/**
 * Created by Clase on 21/11/2015.
 */
public class AgregarIngredientes extends AppCompatActivity {

    private GestorIngredientes gestor;
    private List<Ingredientes> lista;
    private LinearLayout linerVerti;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredientes);
        gestor = new GestorIngredientes(this);
        linerVerti = (LinearLayout) findViewById(R.id.linerVertiIngre);

    }
    public void btagregarIngerdiente(View v){
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Nuevo ingrediente");

        final View view = LayoutInflater.from(this).inflate(R.layout.nuevo_ingrediente, null);

        alert.setView(view);

        DialogInterface.OnClickListener listenerSearch = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                EditText et = (EditText) view.findViewById(R.id.etIngreNuevo);
                String nuevo = et.getText().toString();
                Ingredientes ingre = new Ingredientes(nuevo);
                gestor.insert(ingre);

                LinearLayout vl = (LinearLayout) findViewById(R.id.linerVertiIngre);
                LinearLayout horizontal = new LinearLayout(AgregarIngredientes.this);
                CheckBox check = new CheckBox(AgregarIngredientes.this);
                TextView tv = new TextView(AgregarIngredientes.this);
                EditText edit = new EditText(AgregarIngredientes.this);
                edit.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                check.setText("");
                tv.setText(nuevo);
                horizontal.addView(check);
                horizontal.addView(tv);
                horizontal.addView(edit);
                vl.addView(horizontal);
            }
        };
        alert.setPositiveButton("Agregar", listenerSearch);
        alert.setNegativeButton("Cancelar", null);
        alert.show();
    }
    public void mostrar() {

        lista = gestor.selectIngredientes();
        for (Ingredientes ingrediente : lista) {
            LinearLayout horizontal = new LinearLayout(this);
            CheckBox check = new CheckBox(this);
            TextView tv = new TextView(this);
            EditText et = new EditText(this);
            et.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            check.setText("");
            tv.setText(ingrediente.getNombre());
            horizontal.addView(check);
            horizontal.addView(tv);
            horizontal.addView(et);
            linerVerti.addView(horizontal);
        }
    }
    public void btanadirIngredientes(View v){
        int children = linerVerti.getChildCount();
        String[] ingredientes = new String[0];

        for (int i = 0; i < children; i++) {
            LinearLayout hl = (LinearLayout) linerVerti.getChildAt(i);
            CheckBox cb = (CheckBox) hl.getChildAt(0);
            TextView tv = (TextView) hl.getChildAt(1);
            EditText et = (EditText) hl.getChildAt(2);
            if (cb.isChecked()) {
                String[] masingredientes = new String[ingredientes.length + 2];
                int j = 0;
                if (ingredientes.length != 0) {
                    for (j = 0; j < ingredientes.length; j++) {
                        masingredientes[j] = ingredientes[j];
                    }
                }
                masingredientes[j] = tv.getText().toString();
                masingredientes[j+1] = et.getText().toString();
                ingredientes= masingredientes;
            }
        }
        gestor.close();
        this.getIntent().putExtra("ingreLista", ingredientes);
        setResult(RESULT_OK, this.getIntent());
        finish();
    }
    public void btBorrarIngrediente(View v){
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Introduce el nombre");
        final View view = LayoutInflater.from(this).inflate(R.layout.borrar_ingrediente, null);
        alert.setView(view);
        DialogInterface.OnClickListener listenerSearch = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                EditText et = (EditText) view.findViewById(R.id.etBorrar);
                String nombre = et.getText().toString();
                gestor.deleteIngrediente(nombre);
                gestor.close();
                linerVerti = new LinearLayout(AgregarIngredientes.this);
                finish();
            }
        };
        alert.setPositiveButton("Borrar", listenerSearch);
        alert.setNegativeButton("Cancelar", null);
        alert.show();

    }

    @Override
    protected void onResume() {
        gestor.open();
        super.onResume();
        this.mostrar();
    }
    @Override
    protected void onPause() {
        gestor.close();
        Log.v("APLICACION", "Resume Principal Close");
        super.onPause();
    }
}
