package com.example.general.ejemplobase;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.general.ejemplobase.Objetos.FirebaseReference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class baseEjemplo extends AppCompatActivity {

    public static String idUsuario;

    EditText cCorreo;
    EditText cContraseña;
    Button btnlogin;
    TextView registrarse;
    TextView recuperar;
    FirebaseAuth.AuthStateListener mAuthListener;

    DatabaseReference usuariosReferencia;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_ejemplo);



        database = FirebaseDatabase.getInstance();
        usuariosReferencia = database.getReference(FirebaseReference.USUARIOS_REFERENCIA);


        cCorreo = (EditText) findViewById(R.id.campoCorreo);
        idUsuario=cCorreo.getText().toString();
        cContraseña = (EditText) findViewById(R.id.campoContraseña);

        btnlogin = (Button) findViewById(R.id.botonLogin);
        registrarse = (TextView) findViewById(R.id.textRegistro);
        recuperar = (TextView) findViewById(R.id.textRecuperar);
        FirebaseAuth.getInstance();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){

                }
                else {
                    Log.i("SESION","Sesion cerrada");
                }
            }
        };

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loguear();



            }
        });

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(baseEjemplo.this, Registro.class);
                startActivity(i);
                finish();
            }
        });

        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(baseEjemplo.this, Recuperar.class);
                startActivity(i);
                finish();
            }
        });

    }


    private void loguear (){
        String correo = cCorreo.getText().toString().trim();
        String contraseña = cContraseña.getText().toString().trim();
        if (!TextUtils.isEmpty(correo) && !TextUtils.isEmpty(contraseña)){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(correo,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Intent i = new Intent(baseEjemplo.this , localizacion.class);
                        startActivity(i);
                        finish();
                    }
                }
            });
        }
        else {
            Toast.makeText(baseEjemplo.this,"Ingrese su usuario y contraseña",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);

        }
    }

}

