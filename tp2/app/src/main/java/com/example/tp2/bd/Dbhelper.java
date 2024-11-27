package com.example.tp2.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2; // Incrementa la versión de la base de datos
    private static final String DATABASE_NOMBRE = "Hospital";

    public static final String TABLE_PACIENTES = "Pacientes";

    public Dbhelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PACIENTES + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "Fecha_Nacimiento DATE NOT NULL, " +
                "Diagnostico TEXT NOT NULL, " +
                "dni TEXT NOT NULL" + // Se agrega el campo dni
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            // Agregar columna dni si la base de datos está en la versión 1
            db.execSQL("ALTER TABLE " + TABLE_PACIENTES + " ADD COLUMN dni TEXT NOT NULL DEFAULT '';");
        }
    }
}
