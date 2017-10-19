package com.example.general.ejemplobase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class localizacion extends AppCompatActivity {

    Button cancelar;
    Button aceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacion);

        cancelar = (Button) findViewById(R.id.cancelarLocalizacion);
        aceptar = (Button) findViewById(R.id.aceptarLocalizacion);

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
