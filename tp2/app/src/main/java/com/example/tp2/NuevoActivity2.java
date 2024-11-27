package com.example.tp2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tp2.bd.DbPacientes;

public class NuevoActivity2 extends AppCompatActivity {

    EditText txtnombre, txtfecha, txtdni;
    Button buttonguardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nuevo2);

        // Configura los márgenes para los insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializa las vistas
        txtnombre = findViewById(R.id.txtnombre);
        txtfecha = findViewById(R.id.txtfecha);
        txtdni = findViewById(R.id.txtdni);
        buttonguardar = findViewById(R.id.buttonguarda); // Asegúrate de que el ID sea correcto

        buttonguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén los datos ingresados
                String nombre = txtnombre.getText().toString();
                String fecha = txtfecha.getText().toString();
                String dni = txtdni.getText().toString();

                // Valida los campos
                if (nombre.isEmpty() || fecha.isEmpty() || dni.isEmpty()) {
                    Toast.makeText(NuevoActivity2.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Inserta en la base de datos
                DbPacientes dbPacientes = new DbPacientes(NuevoActivity2.this);
                long id = dbPacientes.insertarPaciente(nombre, fecha, "Diagnóstico por defecto", dni);

                if (id > 0) {
                    Toast.makeText(NuevoActivity2.this, "Paciente guardado con éxito", Toast.LENGTH_SHORT).show();
                    limpiar(); // Limpia los campos
                    finish(); // Cierra la actividad
                } else {
                    Toast.makeText(NuevoActivity2.this, "Error al guardar el paciente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Método para limpiar los campos
    private void limpiar() {
        txtnombre.setText("");
        txtfecha.setText("");
        txtdni.setText("");
    }
}
