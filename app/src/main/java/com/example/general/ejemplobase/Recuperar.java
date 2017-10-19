package com.example.general.ejemplobase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Recuperar extends AppCompatActivity {
    Button enviarCorreo;
    Button cancelarEnvio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);

        enviarCorreo = (Button) findViewById(R.id.btnEnviarCorreo);
        cancelarEnvio = (Button) findViewById(R.id.btnCancelarRecuperar);

        enviarCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Recuperar.this , lugar.class);
                startActivity(i);
                finish();
            }
        });
        cancelarEnvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Recuperar.this , baseEjemplo.class);
                startActivity(i);
                finish();
            }
        });


    }
}
