package com.example.general.ejemplobase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.general.ejemplobase.Objetos.FirebaseReference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class baseEjemplo extends AppCompatActivity {


    EditText cCorreo;
    EditText cContraseña;
    Button btnlogin;
    TextView registrarse;
    TextView recuperar;
    FirebaseAuth.AuthStateListener mAuthListener;

    //Se utiliza en otras clases
    public static String idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_ejemplo);


        cCorreo = (EditText) findViewById(R.id.campoCorreo);
        cContraseña = (EditText) findViewById(R.id.campoContraseña);

        idUsuario=cCorreo.toString().trim();

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

            
                Intent i = new Intent(baseEjemplo.this , localizacion.class);
                startActivity(i);
                finish();


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

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference(FirebaseReference.EVENTDAY_REFERENCES);
    }


    private void loguear (){
        String correo = cCorreo.getText().toString().trim();
        String contraseña = cContraseña.getText().toString().trim();
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
