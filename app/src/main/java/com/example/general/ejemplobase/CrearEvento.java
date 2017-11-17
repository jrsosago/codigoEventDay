package com.example.general.ejemplobase;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.general.ejemplobase.baseEjemplo.*;
import com.example.general.ejemplobase.Objetos.FirebaseReference;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.example.general.ejemplobase.Objetos.FirebaseReference.STORAGE_EVENTOS;
import static com.example.general.ejemplobase.baseEjemplo.idUsuario;

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
    private StorageReference mStorage;


    private Button crearEvento;
    private Button cancelarCreacionEvento;
    private Button perfilCrearEvento;
    private ImageButton seleccionarFoto;

    private static final int PETICION_CAPTURA_IMAGEN = 1;
    private static final int SOLICITUD_CAMERA = 2;
    private static final int GALLERY_INTENT=3;

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
                    Evento e=new Evento(sNombre,sCategoria,sPrecio,sFecha,sHora,sDescripcion,sDireccion,idUsuario);
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

        //Poner una foto guardada en el celular
        mStorage= FirebaseStorage.getInstance().getReference();
        seleccionarFoto=(ImageButton)findViewById(R.id.BotonCamaraEvento);

        seleccionarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, GALLERY_INTENT);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);

            if(requestCode==GALLERY_INTENT && resultCode==RESULT_OK){
                Uri uri= data.getData();

                StorageReference filepath= mStorage.child(STORAGE_EVENTOS).child(uri.getLastPathSegment());

                filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Toast.makeText(getApplicationContext(),"Foto Guardada con éxito",Toast.LENGTH_LONG).show();

                    }
                });
            }
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
