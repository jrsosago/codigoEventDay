package com.example.general.ejemplobase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by sosa on 18/10/2017.
 */

public class lugar extends AppCompatActivity {

    Button agregarEvento;
    Button c;
    Button c1;
    Button c2;
    Button c3;
    Button perfilactividadlugar;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_lugar);

        agregarEvento=(Button) findViewById(R.id.botonAgregarEvento);
        c=(Button)findViewById(R.id.BotonConcierto);
        c1=(Button)findViewById(R.id.BotonConciertoUno);
        c2=(Button)findViewById(R.id.botonConciertoDos);
        c3=(Button)findViewById(R.id.BotonConciertoTres);
        perfilactividadlugar=(Button) findViewById(R.id.button2);

        agregarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(lugar.this,CrearEvento.class);
                startActivity(i);
                finish();
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(lugar.this, MostrarEvento.class);
                startActivity(i);
                finish();
            }
        });
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(lugar.this, MostrarEvento.class);
                startActivity(i);
                finish();
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(lugar.this, MostrarEvento.class);
                startActivity(i);
                finish();
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(lugar.this, MostrarEvento.class);
                startActivity(i);
                finish();
            }
        });
        perfilactividadlugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(lugar.this,perfil.class);
                startActivity(i);
                finish();
            }
        });
    }
}
