package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Menu extends AppCompatActivity {

    Button btnRegistrar, btnLogear, btnVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        // Configura los márgenes para los insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializa los botones
        btnRegistrar = findViewById(R.id.register);
        btnLogear = findViewById(R.id.login);
        btnVer = findViewById(R.id.ver);

        // Evento del botón "Registrar"
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la actividad de registro
                Intent intent = new Intent(Menu.this, NuevoActivity2.class);
                startActivity(intent);
            }
        });
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la actividad de login
                Intent intent = new Intent(Menu.this, VerPacientes.class);
                startActivity(intent);
            }
        });
        btnLogear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la actividad de login
                Intent intent = new Intent(Menu.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
