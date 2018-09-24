package com.example.danielbustamante.peluches2application;


import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class EliminarFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            interfaz = (comunicador)activity;
        }catch (ClassCastException e){}
    }
    comunicador interfaz;
    private EditText eeliminar;
    private Button beliminar;

    public EliminarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eliminar, container, false);
        eeliminar = view.findViewById(R.id.eeliminar);
        beliminar = view.findViewById(R.id.beliminar);


        beliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (eeliminar.equals("")){
                    Toast.makeText(getActivity(), "Ingrese Nombre a eliminar", Toast.LENGTH_SHORT).show();

                }else {
                    String eliminar = eeliminar.getText().toString();
                    interfaz.Eliminar(eliminar);
                    eeliminar.equals("");

                }

            }
        });
        // Inflate the layout for this fragment
        return view;
    }


}
