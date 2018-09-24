package com.example.danielbustamante.peluches2application;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class BuscarFragment extends Fragment {
private EditText ebuscar;
    private TextView tout;
    private Button bbuscar;
    comunicador interfaz;

    public BuscarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_buscar,container,false);
        ebuscar=view.findViewById(R.id.ebuscar);
        bbuscar=view.findViewById(R.id.bbuscar);


     /*   Bundle bundle = getArguments();
        String name = bundle.getString("datos");
        tout.setText(name);
        */



        bbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ebuscar.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"Ingrese un Nombre",Toast.LENGTH_SHORT).show();
                }else{
                    String nombre= ebuscar.getText().toString();
                    interfaz.buscarP(nombre);

                }

            }
        });


        return view;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            interfaz=(comunicador)activity;
        }catch (ClassCastException e){

        }
    }

}
