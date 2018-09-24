package com.example.danielbustamante.peluches2application;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class InventarioFragment extends Fragment {
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
     ArrayList<peluchito> peluche;
    private PeluchesAdapter peluchesAdapter;


    private TextView tout;

    public InventarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventario, container, false);

        recyclerView= view.findViewById(R.id.recyclerView);
        peluche = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        peluchesAdapter = new PeluchesAdapter(peluche);
        recyclerView.setAdapter(peluchesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       peluchesAdapter = new PeluchesAdapter(peluche);
        recyclerView.setAdapter(peluchesAdapter);

        loadData();

        return view;
    }
    private void loadData() {
        PelucheSQLiteHelper pelucheSQLiteHelper;
        SQLiteDatabase dbPeluches;

        pelucheSQLiteHelper = new PelucheSQLiteHelper(
                getContext(),
                "peluchesBD",
                null,
                1);

        dbPeluches = pelucheSQLiteHelper.getWritableDatabase();

        Cursor c = dbPeluches.rawQuery(
                        "SELECT * FROM peluches",
                        null);

                if(c.moveToFirst()){
                    do{
                        peluchito peluchito = new peluchito(
                                c.getString(0),
                                c.getString(1),
                                c.getString(2));
                peluche.add(peluchito);
            }while (c.moveToNext());
            peluchesAdapter.notifyDataSetChanged();
        } else{
            Toast.makeText(getContext(),"No hay Peluches",Toast.LENGTH_SHORT).show();
        }
    }

}
