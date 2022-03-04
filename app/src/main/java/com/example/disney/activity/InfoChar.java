package com.example.disney.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.disney.R;

//La clase con los datos que quiero de mis personajes
public class InfoChar extends AppCompatActivity {
    TextView Nombre;
    TextView Id;
    TextView Url;
    ImageView Imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_char);
        //Pido los datos de la anterior actividad y los añado mejor en la vista detallada
        Intent i = getIntent();
        String id = i.getStringExtra("id");
        String nombre = i.getStringExtra("nombre");
        String url = i.getStringExtra("url");
        String imagen = i.getStringExtra("imagen");

        //Le asigno a cada dato dónde se va a mostrar
        Nombre = findViewById(R.id.itemName);
        Url = findViewById(R.id.url);
        Id = findViewById(R.id.id);
        Imagen = findViewById(R.id.imagen);
        Url.setText(url);
        Nombre.setText(nombre);
        Id.setText(id);
         Glide.with(getApplicationContext())
                .load(imagen).fitCenter()
                .into(Imagen);
    }
}