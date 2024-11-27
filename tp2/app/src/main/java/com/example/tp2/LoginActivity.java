package com.example.tp2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp2.bd.Dbhelper;

public class LoginActivity extends AppCompatActivity {

    EditText txtDni, txtNombre;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializa las vistas
        txtDni = findViewById(R.id.txtDni);
        txtNombre = findViewById(R.id.txtNombre);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni = txtDni.getText().toString();
                String nombre = txtNombre.getText().toString();

                // Valida los campos
                if (dni.isEmpty() || nombre.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Por favor, completa ambos campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Llama al método para validar el login
                if (validarLogin(dni, nombre)) {
                    Toast.makeText(LoginActivity.this, "¡Login exitoso!", Toast.LENGTH_SHORT).show();
                    // Redirige al menú principal con los datos del paciente
                    Intent intent = new Intent(LoginActivity.this, Login2.class);
                    intent.putExtra("dni", dni); // Pasa el DNI al siguiente Activity
                    intent.putExtra("nombre", nombre); // Pasa el Nombre al siguiente Activity
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validarLogin(String dni, String nombre) {
        Dbhelper dbHelper = new Dbhelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Consulta para verificar que el DNI y el nombre coinciden en la base de datos
        String query = "SELECT * FROM Pacientes WHERE dni = ? AND nombre = ?";
        Cursor cursor = db.rawQuery(query, new String[]{dni, nombre});

        // Si el cursor tiene más de 0 filas, significa que las credenciales son correctas
        boolean loginValido = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return loginValido;
    }
}
