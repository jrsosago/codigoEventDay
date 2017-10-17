package com.example.general.ejemplobase;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
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

public class CrearEvento extends Fragment {

    Calendar myCalendar=Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;
    TimePickerDialog.OnTimeSetListener hour;
    private ProgressDialog progressDialog;

    private String info="";
    private String sNameEvento;
    private String sCategoriaEvento;
    private String sPrecioEvento;
    private String sDateEvento;
    private String sHoraEvento;
    private String sDescripcionEvento;
    private String sDireccionEvento;

    private EditText txtNombre;
    private EditText txtCategoria;
    private EditText txtPrecio;
    private EditText txtDia;
    private EditText txtHora;
    private EditText txtDescripcion;
    private EditText txtDireccion;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    public CrearEvento(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view=inflater.inflate(R.layout.activity_crear_evento,container, false);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        mDatabase=database.getReference("Eventos");
        mAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(getActivity());

        txtNombre=(EditText)view.findViewById(R.id.editTextNombreEvento);
        txtCategoria=(EditText)view.findViewById(R.id.editTextCategoria);
        txtPrecio=(EditText)view.findViewById(R.id.editTextPrecio);
        txtDia=(EditText)view.findViewById(R.id.editTextFecha);
        txtHora=(EditText)view.findViewById(R.id.editTextHora);
        txtDescripcion=(EditText)view.findViewById(R.id.editTextDescripcionEvento);
        txtDireccion=(EditText)view.findViewById(R.id.editTextDireccion);
        Button botonCrear=(Button)view.findViewById(R.id.botonCrearEvento);

        date=new DatePickerDialog.OnDateSetListener(){
          @Override
            public void onDateSet(DatePicker view, int ano, int mes, int dia){
              myCalendar.set(Calendar.YEAR,ano);
              myCalendar.set(Calendar.MONTH, mes);
              myCalendar.set(Calendar.DAY_OF_MONTH, dia);
              updateTxtDia();
          }
        };
        hour= new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hora, int minutos){
                myCalendar.set(Calendar.HOUR,hora);
                myCalendar.set(Calendar.MINUTE,minutos);
                updateTxtHora();
            }
        };

        txtDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        txtHora.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new TimePickerDialog(getActivity(),hour,myCalendar.get(Calendar.HOUR),myCalendar.get(Calendar.MINUTE),true).show();
            }
        });

        //Se oprime el botón de crear evento
        botonCrear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sNameEvento=txtNombre.getText().toString().trim();
                sCategoriaEvento=txtCategoria.getText().toString().trim();
                sPrecioEvento=txtPrecio.getText().toString().trim();
                sDateEvento=txtDia.getText().toString().trim();
                sHoraEvento=txtHora.getText().toString().toLowerCase().trim();
                sDescripcionEvento=txtDescripcion.getText().toString().trim();
                sDireccionEvento=txtDireccion.getText().toString().trim();

                progressDialog.setMessage("Creando evento, por favor espera...");
                progressDialog.show();

                if(checkCambios(sNameEvento,sCategoriaEvento,sPrecioEvento,sDateEvento,sHoraEvento,sDescripcionEvento,sDireccionEvento)){
                    Toast.makeText(getActivity().getApplication().getApplicationContext(),"Evento creado con éxito", Toast.LENGTH_SHORT).show();
                    String key = mDatabase.child(mDatabase.push().getKey()).getKey();
                    mDatabase.child(key).setValue(new Evento (key, sNameEvento, sCategoriaEvento, sPrecioEvento, sDateEvento, sHoraEvento, sDescripcionEvento, sDireccionEvento, mAuth.getCurrentUser().getEmail()));
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.popBackStackImmediate();

                }else {
                    Toast.makeText(getActivity().getApplication().getApplicationContext(), info, Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });

        return view;
    }

    public boolean checkCambios(String nombre, String categoria, String precio, String fecha, String hora, String descripcion, String ubicacion){
        if(nombre.isEmpty()||categoria.isEmpty()||precio.isEmpty()||fecha.isEmpty()||hora.isEmpty()||descripcion.isEmpty()||ubicacion.isEmpty()){
            info="Todos los campos deben estar llenos";
            return false;
        }else{
            info="Error en la conexión";
            return true;
        }
    }

    private void updateTxtDia(){
        String myFormat="dd/MM/yy";
        SimpleDateFormat date=new SimpleDateFormat(myFormat, Locale.US);
        txtDia.setText(date.format(myCalendar.getTime()));
    }
    private void updateTxtHora(){
        String myFormat="HH:mm";
        SimpleDateFormat hour=new SimpleDateFormat(myFormat,Locale.US);
        txtHora.setText(hour.format(myCalendar.getTime()));
    }
}
