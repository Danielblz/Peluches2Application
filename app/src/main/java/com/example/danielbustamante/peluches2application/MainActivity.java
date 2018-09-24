package com.example.danielbustamante.peluches2application;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements comunicador,  NavigationView.OnNavigationItemSelectedListener {

    private PelucheSQLiteHelper pelucheSQLiteHelper;
    private SQLiteDatabase dbPeluches;
    private ContentValues dataBD;

    private FragmentManager fm ;
    private FragmentTransaction ft;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pelucheSQLiteHelper = new PelucheSQLiteHelper(
                this,
                "peluchesBD",
                null,
                1);

        dbPeluches = pelucheSQLiteHelper.getWritableDatabase();





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        AgregarFragment agregarFragment = new AgregarFragment();
        ft.add(R.id.frame, agregarFragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        ft=fm.beginTransaction();


        if (id == R.id.mAgregar) {
            AgregarFragment agregarFragment = new AgregarFragment();
            ft.replace(R.id.frame, agregarFragment).commit();


        } else if (id == R.id.mBuscar) {

            BuscarFragment buscarFragment = new BuscarFragment();
            ft.replace(R.id.frame, buscarFragment).commit();

        } else if (id == R.id.mEliminar) {

            EliminarFragment eliminarFragment = new EliminarFragment();
            ft.replace(R.id.frame, eliminarFragment).commit();


        } else if (id == R.id.mInventario) {


            InventarioFragment inventarioFragment =  new InventarioFragment();

            ft.replace(R.id.frame, inventarioFragment).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void envioDatos( String nombre, String cantidad, String precio) {
        dataBD = new ContentValues();
        dataBD.put("nombre", nombre);
        dataBD.put("cantidad",cantidad);
        dataBD.put("precio",precio);

        dbPeluches.insert("peluches",null,dataBD);


        Toast.makeText(this, "El peluche  "+ nombre + "  Ha sido agregado", Toast.LENGTH_SHORT).show();



    }

    @Override
    public void Eliminar(String eliminar) {
        Cursor c = dbPeluches.rawQuery(
                "SELECT * FROM peluches WHERE nombre = '" + eliminar + "'",
                null);

        if (c.moveToFirst()) {
            dbPeluches.delete("peluches",
                    "nombre = '" + eliminar + "'",
                    null);
            Toast.makeText(this, "El peluche:  " + eliminar + "  - Ha sido Eliminado", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Pelcuhe:    " + eliminar+  "   -no Existe", Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    public void buscarP(String buscar) {

        Cursor c = dbPeluches.rawQuery(
                "SELECT * FROM peluches WHERE nombre = '"+buscar+"'",
                null);

        if(c.moveToFirst()){
            Toast.makeText(this,"Nombre:  "+buscar+"\nCantidad: "+c.getString(1)+"\nPrecio: "+c.getString(2),Toast.LENGTH_SHORT).show();

        } else{
            Toast.makeText(this,"Pelcuhe:   " + buscar+"    - no Existe",Toast.LENGTH_SHORT).show();
        }

    }








}
