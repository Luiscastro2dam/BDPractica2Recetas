package com.example.clase.bdpractica3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.clase.bdpractica3.BaseDatos.Adaptador;
import com.example.clase.bdpractica3.BaseDatos.GestorRecetas;
import com.example.clase.bdpractica3.Tablas.Recetas;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GestorRecetas gestor;
    private Adaptador ad;
    private List<Recetas> lista;
    private ListView lv;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = (SearchView)findViewById(R.id.searchView);
        gestor=new GestorRecetas(this);

    }
    //------------menu principal--------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.agregar: {
                Intent i = new Intent(this,Agregar.class);
                startActivity(i);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    //-----------------------------------------------------------
    //---------Menu ListView-------------------------------------

    @Override
     public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos = menuInfo.position;
        switch (item.getItemId()) {
            case R.id.menu_Mostar: {
                Intent i = new Intent(this, Mostrar.class);
                i.putExtra("receta", gestor.select().get(pos));
                startActivity(i);
                return true;
            }
            case R.id.menu_Borrar:{
              long a=  lista.get(pos).getIdReceta();
                gestor.deleteReceta(a);
                this.init();
            }
            default:
                return super.onContextItemSelected(item);
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_listview, menu);
    }
    //-------------------------------------------------------------------
    public void init(){
        lv = (ListView) findViewById(R.id.lvMostrar);
        lista = gestor.select();
        ad = new Adaptador(this, R.layout.item, (ArrayList<Recetas>) lista);
        lv.setAdapter(ad);
        this.registerForContextMenu(lv);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                ad.getFilter().filter(text.toString());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                ad.getFilter().filter(text.toString());
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        gestor.open();
        super.onResume();
        this.init();

    }
    @Override
    protected void onPause() {
        gestor.close();
        Log.v("APLICACION", "Resume Principal Close");
        super.onPause();
    }

}
