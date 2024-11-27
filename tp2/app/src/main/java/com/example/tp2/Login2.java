package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp2.bd.DbPacientes;

public class Login2 extends AppCompatActivity {

    TextView txtDatosPaciente;
    EditText edtDiagnostico;
    Button btnGuardarDiagnostico, btnVolver, btnDarDeAlta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        // Inicializar vistas
        txtDatosPaciente = findViewById(R.id.txtDatosPaciente);
        edtDiagnostico = findViewById(R.id.edtDiagnostico);
        btnGuardarDiagnostico = findViewById(R.id.btnVolver);
        btnVolver = findViewById(R.id.btnVolver1);
        btnDarDeAlta = findViewById(R.id.btnDarDeAlta); // Nuevo botón

        // Obtener datos del Intent
        Intent intent = getIntent();
        String dni = intent.getStringExtra("dni");

        if (dni == null || dni.isEmpty()) {
            Toast.makeText(this, "Error: DNI no disponible", Toast.LENGTH_SHORT).show();
            finish(); // Finalizar la actividad si no hay DNI
            return;
        }

        // Recuperar y mostrar datos del paciente desde la base de datos
        DbPacientes dbPacientes = new DbPacientes(this);
        String datosPaciente = dbPacientes.obtenerPacientePorDni(dni);

        if (datosPaciente != null && !datosPaciente.isEmpty()) {
            txtDatosPaciente.setText(datosPaciente);
        } else {
            txtDatosPaciente.setText("Paciente no encontrado.");
        }

        // Configurar acción del botón para guardar el diagnóstico
        btnGuardarDiagnostico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevoDiagnostico = edtDiagnostico.getText().toString();

                // Validar que el diagnóstico no esté vacío
                if (nuevoDiagnostico.isEmpty()) {
                    Toast.makeText(Login2.this, "Por favor, ingrese un diagnóstico", Toast.LENGTH_SHORT).show();
                } else {
                    // Guardar el diagnóstico en la base de datos
                    boolean actualizado = dbPacientes.actualizarDiagnostico(dni, nuevoDiagnostico);

                    if (actualizado) {
                        Toast.makeText(Login2.this, "Diagnóstico actualizado con éxito", Toast.LENGTH_SHORT).show();

                        // Refrescar los datos del paciente
                        String datosActualizados = dbPacientes.obtenerPacientePorDni(dni);
                        txtDatosPaciente.setText(datosActualizados);
                    } else {
                        Toast.makeText(Login2.this, "Error al actualizar el diagnóstico", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
               // btn dar de alta opcion que elimina al paciente de la base de datos
        btnDarDeAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Confirmar eliminación del paciente
                boolean eliminado = dbPacientes.eliminarPaciente(dni);

                if (eliminado) {
                    Toast.makeText(Login2.this, "Paciente dado de alta con éxito", Toast.LENGTH_SHORT).show();
                    // Regresar al menú después de eliminar
                    Intent intent = new Intent(Login2.this, Menu.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login2.this, "Error al dar de alta al paciente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login2.this, Menu.class);

                startActivity(intent);
                finish();
            }
        });
    }
}
