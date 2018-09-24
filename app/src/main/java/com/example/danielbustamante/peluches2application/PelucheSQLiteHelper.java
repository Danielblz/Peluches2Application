package com.example.danielbustamante.peluches2application;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PelucheSQLiteHelper extends SQLiteOpenHelper {


    String sqlCreate = "CREATE TABLE peluches (" +
            "nombre     TEXT, " +                               //0
            "cantidad   TEXT, " +                               //1
            "precio     TEXT)";                                 //2

    public PelucheSQLiteHelper(Context context,
                                 String name,
                                 SQLiteDatabase.CursorFactory factory,
                                 int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS peluches");
        db.execSQL(sqlCreate);
    }
}
