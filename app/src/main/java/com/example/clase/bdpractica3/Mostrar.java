package com.example.clase.bdpractica3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.clase.bdpractica3.BaseDatos.GestorIngredientes;
import com.example.clase.bdpractica3.BaseDatos.GestorRecetas;
import com.example.clase.bdpractica3.BaseDatos.GestorRelacion;
import com.example.clase.bdpractica3.Tablas.Recetas;
import com.example.clase.bdpractica3.Tablas.RelaIngRec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clase on 19/11/2015.
 */
public class Mostrar extends AppCompatActivity {
    private ListView lv;

    private List<String> lista;
    GestorRecetas gestor;
    GestorIngredientes gestorIngredientes;
    GestorRelacion gestorRelacion;
    Recetas receta;
    String a;
    private TextView tvnombre,tvelaboracion,tvMosIngre;
   // private ImageView imageView2;
    FrameLayout frame;

   // private final String ruta_fotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/misfotos/";
  //  private File file = new File(ruta_fotos);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar);
        tvnombre = (TextView)findViewById(R.id.tvMnom);
        tvelaboracion = (TextView)findViewById(R.id.tvMela);
        tvMosIngre = (TextView)findViewById(R.id.tvMosIngredie);
        //imageView2= (ImageView)findViewById(R.id.imageView2);
        gestor=new GestorRecetas(this);
        gestorRelacion=new GestorRelacion(this);
        gestorIngredientes=new GestorIngredientes(this);
        receta = (Recetas) getIntent().getSerializableExtra("receta");
        frame = (FrameLayout)findViewById(R.id.frame);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_GET && resultCode ==
                RESULT_OK) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                String ruta = this.BitMapToString(bitmap);
                receta.setFoto(ruta);
                gestor.updateRecetas(receta);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
                    encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
    @Override
    protected void onResume() {
        gestor.open();
        gestorIngredientes.open();
        gestorRelacion.open();
        super.onResume();
        this.mostrarReceta();
        this.mostrarIngredientes();
        Log.v("APLICACION", "Resume Alta Open");
    }
    public void mostrarReceta(){
        receta=gestor.selectIdMostrarece(receta);
        tvnombre.setText(receta.getNombre());
        tvelaboracion.setText(receta.getElaboracion());
        String ruta = receta.getFoto();
        Bitmap bit = StringToBitMap(ruta);
        Drawable d = new BitmapDrawable(getResources(), bit);
        frame.setBackground(d);
    }
    public void mostrarIngredientes(){

        List<RelaIngRec> relacionIngredientes = gestorRelacion.selectCantidades(receta);
        List<String> ingrediente= new ArrayList<>();
        List<String> cantidade= new ArrayList<>();

        for (RelaIngRec cant : relacionIngredientes) {
            ingrediente.add(gestorIngredientes.selectNombreIngredienteId(cant.getIdIngrediente()));
            cantidade.add(cant.getCantidad());
            System.out.println("luis"+cantidade.toString());//tenemos las cantidades
        }
        for (int i=0; i<cantidade.size(); i++){
            tvMosIngre.append(ingrediente.get(i) + "=" + cantidade.get(i) +"\n");
        }
    }
    public  static final int REQUEST_IMAGE_GET = 1;
    public void btFotoInternas(View v){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        //PARA ABRIR UNA PALICACION QUE USE LA IMG
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_GET);
        }
    }
    public void btcancelar(View v){
        System.exit(0);
    }
}
