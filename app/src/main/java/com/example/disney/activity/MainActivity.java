package com.example.disney.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.disney.adapter.charAdapter;
import com.example.disney.api.apiConnection;
import com.example.disney.models.Charact;
import com.example.disney.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Declaro la lista y los elementos del recycler view
    private RecyclerView recyclerView;
    private charAdapter recAdapter;
    Charact myChar;
    public ArrayList<Charact> charactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        charactList = new ArrayList<>();
        new taskConnections().execute("GET","");
        myChar = new Charact();
        charactList = new ArrayList<>();
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80DEEA")));
        //getSupportActionBar().setTitle("Personajes Disney");

        //Oculto el action bar porque no me gusta
        loadPreferences();
    }

    //Creo el menú
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_simple,menu);
        return true;
    }

    //Obtengo los datos del JSON
    class taskConnections extends AsyncTask<String, Void, String> {

        private String name;
        private String id;
        private String imgUrl;
        private String url;

        @Override
        public String doInBackground(String... strings) {
            String resultado = "";
            switch (strings[0]) {
                case "GET":
                    resultado = apiConnection.getRequest();
                    break;
                case "POST":
                    resultado = Integer.toString((apiConnection.post(strings[1])));
                    break;
            }
            return resultado;
        }

        //De los datos devueltos me quedo con los que quiero
        @Override
        protected void onPostExecute(String s) {
            try {
                if (!s.isEmpty()) {
                    JSONObject respuesta = new JSONObject(s);
                    JSONArray obj = respuesta.getJSONArray("data");

                    //Lo voy recorriendo y obteniendo los datos
                    for (int i = 0; i <obj.length(); i++) {
                        id = obj.getJSONObject(i).getString("_id");
                        imgUrl = obj.getJSONObject(i).getString("imageUrl");
                        url = obj.getJSONObject(i).getString("url");
                        name = obj.getJSONObject(i).getString("name");

                        //Una vez obtenidos creo los personajes de uno en uno con los datos obtenidos
                        myChar = new Charact(name, id, imgUrl, url);
                        charactList.add(myChar);
                    }
                    cargarPersonajes();
                    //Usaba los Log para comprobar la información, después los comento para que no se ejecuten
                    //Log.d("l", "carga char"+ myChar.getName());
                }
            } catch (JSONException e) {
                //Log.d("l", "error en taskedchar" + e);
            }
        }
    }

    //Cargo cada uno de los personajes obtenidos y los meto en el recycler view
    public void cargarPersonajes(){
        recyclerView = (RecyclerView) findViewById(R.id.recView);
        recAdapter = new charAdapter(charactList,this);
        recyclerView.setAdapter(recAdapter);

        //Podemos eliminar un personaje deslizando dicho personaje hacia un lado
        new ItemTouchHelper(swipe).attachToRecyclerView(recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        //Adaptador para el recycler view
        recAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Cargado con éxito", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this,InfoChar.class);

                //Añado los datos que necesito al recycler view
                i.putExtra("id",charactList.get(recyclerView.getChildAdapterPosition(view)).getId());
                i.putExtra("nombre",charactList.get(recyclerView.getChildAdapterPosition(view)).getName());
                i.putExtra("url",charactList.get(recyclerView.getChildAdapterPosition(view)).getUrl());
                i.putExtra("imagen",charactList.get(recyclerView.getChildAdapterPosition(view)).getImageUrl());

                startActivity(i);
            }
        });
    }

    //Llamada para cuando se desliza un personaje
    ItemTouchHelper.SimpleCallback swipe = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            //Lo elimino
            charactList.remove(viewHolder.getAdapterPosition());
            recAdapter.notifyDataSetChanged();
        }
    };

    //Metodo para cargar las preferencias
    public void loadPreferences(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.getString("user","id");
    }

    //Metodo para cuando pulsemos en preferencias
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
        return true;
    }
}





