package com.example.general.ejemplobase;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.general.ejemplobase.Objetos.FirebaseReference;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by sosa on 16/10/2017.
 */

public class CrearEvento extends AppCompatActivity {

    Calendar myCalendar=Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;
    TimePickerDialog.OnTimeSetListener hour;



    private String info="";
    private String sNombre;
    private String sCategoria;
    private String sPrecio;
    private String sFecha;
    private String sHora;
    private String sDescripcion;
    private String sDireccion;

    private EditText txtNombre;
    private EditText txtCategoria;
    private EditText txtPrecio;
    private EditText txtFecha;
    private EditText txtHora;
    private EditText txtDescripcion;
    private EditText txtDirreccion;

    private DatabaseReference mDatabase;
    private FirebaseDatabase database;

    FirebaseApp firebase;
    Button crearEvento;
    Button cancelarCreacionEvento;
    Button perfilCrearEvento;

    public CrearEvento(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);

        txtNombre=(EditText) findViewById(R.id.editTextNombreEvento);
        txtCategoria=(EditText) findViewById(R.id.editTextCategoria);
        txtPrecio=(EditText) findViewById(R.id.editTextPrecio);
        txtFecha=(EditText) findViewById(R.id.editTextFecha);
        txtHora=(EditText) findViewById(R.id.editTextHora);
        txtDescripcion=(EditText) findViewById(R.id.editTextDescripcionEvento);
        txtDirreccion=(EditText) findViewById(R.id.editTextDireccion);
        crearEvento= (Button) findViewById(R.id.botonCrearEvento);
        cancelarCreacionEvento= (Button) findViewById(R.id.botonCancelarEvento);
        perfilCrearEvento=(Button) findViewById(R.id.BotonPerfilCrearEvento);

        /*
        date=new DatePickerDialog.OnDateSetListener(){
            @Override
            public void OnDateSet(int year, int month, int day){
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                  updateTxtDate();
            }
        };

        hour= new TimePickerDialog.OnTimeSetListener(){
                @Override
                public void onTimeSet(int hour, int minutes){
                    myCalendar.set(Calendar.HOUR, hour);
                   myCalendar.set(Calendar.MINUTE, minutes);
                  updateTxtHour();
                }
            };

        txtFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(),date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        txtHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(getActivity(),hour,myCalendar.get(Calendar.HOUR),myCalendar.get(Calendar.MINUTE),true).show();
            }
        });
        */
        database=FirebaseDatabase.getInstance();

        crearEvento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sNombre=txtNombre.getText().toString().trim();
                sCategoria=txtCategoria.getText().toString().trim();
                sPrecio=txtPrecio.getText().toString().trim();
                sFecha=txtFecha.getText().toString().trim();
                sHora=txtHora.getText().toString().toLowerCase().trim();
                sDescripcion=txtDescripcion.getText().toString().trim();
                sDireccion=txtDirreccion.getText().toString().trim();


                if(checkFields(sNombre, sCategoria, sPrecio, sFecha, sHora, sDescripcion, sDireccion)){
                    Toast.makeText(getApplicationContext(), "Evento creado con éxito",  Toast.LENGTH_SHORT).show();
                    mDatabase=database.getReference(FirebaseReference.EVENTOS_REFERENCIA);
                    Evento e=new Evento(sNombre,sCategoria,sPrecio,sFecha,sHora,sDescripcion,sDireccion,"Yo");
                    mDatabase.push().setValue(e);

                    Intent i=new Intent(CrearEvento.this,MostrarEvento.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),info ,Toast.LENGTH_SHORT).show();
                }

            }
        });

        cancelarCreacionEvento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(CrearEvento.this, lugar.class);
                startActivity(i);
                finish();
            }

        });
        
        perfilCrearEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(CrearEvento.this,perfil.class);
                startActivity(i);
                finish();
            }
        });
    }

    public boolean checkFields(String nombre, String categoria, String precio, String fecha, String hora, String descripcion, String direccion ){
        if(nombre.isEmpty()||categoria.isEmpty()|| precio.isEmpty()||fecha.isEmpty()||hora.isEmpty()||descripcion.isEmpty()||direccion.isEmpty()){
            info="Todos los campos deben estar llenos";
            return false;
        }else{
            info="Error en la conexión";
        }
        return true;
    }

    private  void updateTxtDate(){
        String myFormat="dd/MM/yy";
        SimpleDateFormat date=new SimpleDateFormat(myFormat, Locale.US);
        txtFecha.setText(date.format(myCalendar.getTime()));
    }

    private void updateTxtHour(){
        String myFormat="HH:mm";
        SimpleDateFormat hour=new SimpleDateFormat(myFormat,Locale.US);
        txtHora.setText(hour.format(myCalendar.getTime()));
    }

}
