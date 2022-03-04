package com.example.disney.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disney.R.*;
import com.example.disney.models.Charact;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//Adaptador para los personajes
public class charAdapter extends RecyclerView.Adapter<charAdapter.RecyclerHolder> implements View.OnClickListener{
    private Activity context;
    ArrayList<Charact> listCharacters;
    private View.OnClickListener click;
    public charAdapter(ArrayList<Charact> arrayData, Activity activity) {
        this.listCharacters = arrayData;
        this.context = activity;
    }

    //Creo la estructura de componentes de cada celsa de la lista a partir del layout donde los vaya a mostrar
    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(layout.custom_item_list,parent,false);
        RecyclerHolder recyclerHolder = new RecyclerHolder(vista);

        vista.setOnClickListener(this);
        return recyclerHolder;
    }

    //Enlaza la información con cada una de las celdas
    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        Charact a = listCharacters.get(position);
            holder.name.setText(a.getName());
            holder._id.setText(a.getId());

        Picasso.get().load(a.getImageUrl()).into(holder.imageUrl);
    }

    @Override
    public int getItemCount() {
        return listCharacters.size();
    }

    //Para poder hacer click
    @Override
    public void onClick(View view) {
        if(click!=null){
            click.onClick(view);
    }}

    public void setOnClickListener(View.OnClickListener click) {
        this.click = click;
    }

    //Recrea los elementos de la vista del layout de cada elemento de la lista acorde al modelo de datos creado
    public class RecyclerHolder extends ViewHolder {
        TextView _id;
        TextView name;
        ImageView imageUrl;
        //El constructor recibe como parámetro un tipo vista(itemView) que contiene la celda ya creada a partir del layout correspondiente
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            _id  = itemView.findViewById(id.itemId);
            name = itemView.findViewById(id.itemName);
            imageUrl= itemView.findViewById(id.itemImg);
        }
    }
}