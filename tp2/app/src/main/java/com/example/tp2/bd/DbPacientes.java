package com.example.tp2.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbPacientes extends Dbhelper {

    private final Context context;

    public DbPacientes(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public boolean eliminarPaciente(String dni) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_PACIENTES, "dni = ?", new String[]{dni});
        return rows > 0; // Retorna true si se eliminó al menos una fila
    }
    public boolean eliminarTodosLosPacientes() {
        SQLiteDatabase db = this.getWritableDatabase();
        int filasEliminadas = db.delete(TABLE_PACIENTES, null, null); // Eliminar todos los registros
        db.close();
        return filasEliminadas > 0; // Devuelve true si se eliminó al menos una fila
    }

    // Método para insertar un paciente en la base de datos
    public long insertarPaciente(String nombre, String fechaNacimiento, String diagnostico, String dni) {
        long id = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase(); // Obtener base de datos en modo escritura

            ContentValues values = new ContentValues(); // Crear objeto para los valores
            values.put("nombre", nombre);
            values.put("Fecha_Nacimiento", fechaNacimiento); // Usar el valor correcto para la fecha
            values.put("Diagnostico", diagnostico); // Usar el valor correcto para el diagnóstico
            values.put("dni", dni); // Agregar el dni

            // Insertar en la base de datos
            id = db.insert(TABLE_PACIENTES, null, values);
        } catch (Exception ex) {
            ex.printStackTrace(); // Registrar el error
        }
        return id; // Retornar el ID del registro insertado o 0 si falló
    }

    // Método para obtener todos los pacientes de la base de datos
    public ArrayList<String> obtenerTodosLosPacientes() {
        ArrayList<String> listaPacientes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase(); // Obtener base de datos en modo lectura

        Cursor cursor = null;
        try {
            // Consultar todos los datos de la tabla Pacientes
            cursor = db.rawQuery("SELECT * FROM " + TABLE_PACIENTES, null);

            if (cursor.moveToFirst()) {
                do {
                    // Construir una cadena con los datos del paciente
                    String paciente = "ID: " + cursor.getInt(0) +
                            ", Nombre: " + cursor.getString(1) +
                            ", Fecha Nacimiento: " + cursor.getString(2) +
                            ", Diagnóstico: " + cursor.getString(3) +
                            ", DNI: " + cursor.getString(4);

                    listaPacientes.add(paciente); // Agregar a la lista
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close(); // Cerrar el cursor
            }
            db.close(); // Cerrar la base de datos
        }

        return listaPacientes; // Retornar la lista de pacientes
    }

    // Método para obtener los datos de un paciente por su DNI
    public String obtenerPacientePorDni(String dni) {
        SQLiteDatabase db = this.getReadableDatabase();
        String datosPaciente = null;

        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT nombre, Fecha_Nacimiento, Diagnostico, dni FROM " + TABLE_PACIENTES + " WHERE dni = ?", new String[]{dni});

            if (cursor.moveToFirst()) {
                String nombre = cursor.getString(0);
                String fechaNacimiento = cursor.getString(1);
                String diagnostico = cursor.getString(2);

                datosPaciente = "Nombre: " + nombre +
                        "\nFecha de nacimiento: " + (fechaNacimiento != null ? fechaNacimiento : "No disponible") +
                        "\nDiagnóstico: " + (diagnostico != null ? diagnostico : "No disponible") +
                        "\nDNI: " + dni;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return datosPaciente;
    }

    // Método para actualizar el diagnóstico de un paciente
    public boolean actualizarDiagnostico(String dni, String nuevoDiagnostico) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean actualizado = false;

        try {
            ContentValues values = new ContentValues();
            values.put("Diagnostico", nuevoDiagnostico);

            int filasAfectadas = db.update(TABLE_PACIENTES, values, "dni = ?", new String[]{dni});
            if (filasAfectadas > 0) {
                actualizado = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }

        return actualizado;
    }
}
