package com.example.clase.bdpractica3.BaseDatos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clase.bdpractica3.R;
import com.example.clase.bdpractica3.Tablas.Recetas;

import java.util.ArrayList;

/**
 * Created by 2dam on 05/10/2015.
 */
public class Adaptador extends ArrayAdapter<Recetas>{

    private int res;
    private LayoutInflater lInflator;
    private ArrayList<Recetas> valores;
    private Context con;

    static class ViewHolder{
        public TextView tv1;
        public ImageView img;
    }


    public Adaptador(Context context, int resource, ArrayList<Recetas> objects) {
        super(context, resource, objects);
        this.res= resource; // LAYOUT
        this.valores= objects; // LISTA DE VALORES
        this.con= context;
        lInflator=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder gv= new ViewHolder();
        if(convertView==null){
            convertView= lInflator.inflate(res, null);


            TextView tv1= (TextView) convertView.findViewById(R.id.tv1);
            gv.tv1=tv1;
            ImageView img= (ImageView) convertView.findViewById(R.id.img);
            gv.img= img;
            convertView.setTag(gv);
        }else{
            gv= (ViewHolder) convertView.getTag();
        }
        gv.tv1.setText(valores.get(position).getNombre());
        String ruta = valores.get(position).getFoto();
        Bitmap bit = StringToBitMap(ruta);
        gv.img.setImageBitmap(bit);
        return convertView;
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
}
