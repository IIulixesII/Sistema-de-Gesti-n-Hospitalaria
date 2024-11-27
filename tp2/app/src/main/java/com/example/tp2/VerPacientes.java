package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp2.bd.DbPacientes;

import java.util.ArrayList;

public class VerPacientes extends AppCompatActivity {

    private ListView listViewPacientes;
    private Button btnBorrarTodos, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pacientes);

        // Inicializar las vistas
        listViewPacientes = findViewById(R.id.listViewPacientes);
        btnBorrarTodos = findViewById(R.id.btnBorrarTodos);
        btnVolver = findViewById(R.id.btnVolver2);

        // Obtener los pacientes de la base de datos
        DbPacientes dbPacientes = new DbPacientes(this);
        ArrayList<String> listaPacientes = dbPacientes.obtenerTodosLosPacientes();

        // Configurar el ListView
        actualizarListaPacientes(listaPacientes);

        // Configurar el botón para borrar todos los pacientes
        btnBorrarTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Eliminar todos los pacientes de la base de datos
                boolean eliminados = dbPacientes.eliminarTodosLosPacientes();

                if (eliminados) {
                    Toast.makeText(VerPacientes.this, "Todos los pacientes han sido eliminados.", Toast.LENGTH_SHORT).show();
                    // Actualizar la lista después de la eliminación
                    actualizarListaPacientes(new ArrayList<>()); // Lista vacía
                } else {
                    Toast.makeText(VerPacientes.this, "Error al eliminar los pacientes.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Configurar el botón Volver
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerPacientes.this, Menu.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // Método para actualizar el ListView
    private void actualizarListaPacientes(ArrayList<String> listaPacientes) {
        if (listaPacientes.isEmpty()) {
            Toast.makeText(this, "No hay pacientes registrados.", Toast.LENGTH_SHORT).show();
        } else {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaPacientes);
            listViewPacientes.setAdapter(adapter);
        }
    }
}
