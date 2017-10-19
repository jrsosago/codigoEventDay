package com.example.general.ejemplobase;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by sosa on 16/10/2017.
 */

public class CrearEvento extends AppCompatActivity {


    Button crearEvento;
    Button cancelarCreacionEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);

        crearEvento= (Button) findViewById(R.id.botonCrearEvento);
        cancelarCreacionEvento= (Button) findViewById(R.id.botonCancelarEvento);

        crearEvento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=new Intent(CrearEvento.this,Registro.class);
                startActivity(i);
                finish();
            }
        });

        cancelarCreacionEvento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(CrearEvento.this, Recuperar.class);
                startActivity(i);
                finish();
            }

        });
    }

}
