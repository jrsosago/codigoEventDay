package com.example.general.ejemplobase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
                Intent i=new Intent(localizacion.this,baseEjemplo.class);
                startActivity(i);
                finish();

            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(localizacion.this, lugar.class);
                startActivity(i);
                finish();
            }
        });
    }
}
