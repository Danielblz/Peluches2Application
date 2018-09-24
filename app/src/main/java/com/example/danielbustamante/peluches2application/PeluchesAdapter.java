package com.example.danielbustamante.peluches2application;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PeluchesAdapter extends RecyclerView.Adapter<PeluchesAdapter.PeluchesViewHolder>{

    private ArrayList<peluchito> peluche;


    public PeluchesAdapter(ArrayList<peluchito> peluche) {
        this.peluche   = peluche;
    }


    @NonNull
    @Override
    public PeluchesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_list, parent,false);

        return new PeluchesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PeluchesViewHolder holder, int position) {
      peluchito peluchito = peluche.get(position);
        holder.bindPeluches(peluchito);
    }

    @Override
    public int getItemCount() {
        return peluche.size();
    }

    public class PeluchesViewHolder extends RecyclerView.ViewHolder{

        private TextView tId, tNombre, tCantidad, tPrecio;

        public PeluchesViewHolder(View itemView) {
            super(itemView);

            tNombre = itemView.findViewById(R.id.tNombre);
            tCantidad = itemView.findViewById(R.id.tCantidad);
            tPrecio = itemView.findViewById(R.id.tPrecio);
        }

        public void bindPeluches(peluchito peluche){
            tNombre.setText("Nombre: " + peluche.getNombre());
            tCantidad.setText("Cantidad: "+peluche.getCantidad());
            tPrecio.setText("Precio: " +peluche.getPrecio());
        }
    }
}
