package com.example.general.ejemplobase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.general.ejemplobase.Objetos.FirebaseReference;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class baseEjemplo extends AppCompatActivity {
    Button boton_login;
    TextView registrarse;
    TextView recuperar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_ejemplo);

        boton_login = (Button) findViewById(R.id.botonLogin);
        registrarse = (TextView) findViewById(R.id.textRegistro);

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(baseEjemplo.this, Registro.class);
                startActivity(i);
                finish();
            }
        });

        recuperar = (TextView) findViewById(R.id.textRecuperar);

        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(baseEjemplo.this, Recuperar.class);
                startActivity(i);
                finish();
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference(FirebaseReference.EVENTDAY_REFERENCES);
        boton_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
