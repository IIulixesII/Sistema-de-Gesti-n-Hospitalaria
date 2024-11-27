package com.example.tp2;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tp2.bd.Dbhelper;

public class MainActivity extends AppCompatActivity {

    Button buttoncr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Configurar el botón
        buttoncr = findViewById(R.id.buttoncr);

        buttoncr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dbhelper dbHelper = new Dbhelper(MainActivity.this);
                SQLiteDatabase db= dbHelper.getWritableDatabase();
                if (db!=null){

                    Toast.makeText(MainActivity.this, "BASE DE DATOS CREADA BIEN AHI ULI", Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(MainActivity.this, "LA BASE DE DATO NO SE CREO TODO FUE UN GRAN FRACASO CAMBIATE DE CARRERA", Toast.LENGTH_LONG).show();

                }
            }
        });

        // Ajustar los insets de la ventana
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}