package com.example.mostrarposicionesgps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_SQLite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "posiciones.sqlite";
    private static final int    DATABASE_VERSION = 1;

    private static final String SQL_CREATE_POSICIONES = "CREATE TABLE POSICIONES ( " +
            "id INTEGER PRIMARY KEY AUTOINCREMENT,latitud DOUBLE, longitud DOUBLE, descripcion TEXT, idCategoria INTEGER )";
    private static final String SQL_DELETE_POSICIONES = "DROP TABLE IF EXISTS POSICIONES";

    private static final String SQL_CREATE_CATEGORIAS = "CREATE TABLE CATEGORIAS ( id INTEGER PRIMARY KEY AUTOINCREMENT, categoria TEXT )";
    private static final String SQL_DELETE_CATEGORIAS = "DROP TABLE IF EXISTS CATEGORIA";

    public DB_SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_POSICIONES);
        db.execSQL(SQL_CREATE_CATEGORIAS);
        db.execSQL("INSERT INTO CATEGORIAS (categoria) VALUES ('CATEGORIA 1')");
        db.execSQL("INSERT INTO CATEGORIAS (categoria) VALUES ('CATEGORIA 2')");
        db.execSQL("INSERT INTO CATEGORIAS (categoria) VALUES ('CATEGORIA 3')");
        db.execSQL("INSERT INTO CATEGORIAS (categoria) VALUES ('CATEGORIA 4')");
        db.execSQL("INSERT INTO CATEGORIAS (categoria) VALUES ('CATEGORIA 5')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_POSICIONES);
        db.execSQL(SQL_DELETE_CATEGORIAS);
        onCreate(db);
    }

}