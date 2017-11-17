package com.example.general.ejemplobase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.general.ejemplobase.Objetos.FirebaseReference;
import com.example.general.ejemplobase.Objetos.Usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {

    Button btnregistrar;
    Button btnCancelarR;
    EditText Enombre;
    EditText Eapellido;
    EditText Ecorreo;
    EditText Econtraseña;
    EditText Erepetir;
    Pattern pat;
    private FirebaseAuth.AuthStateListener mAuthListener;

    DatabaseReference usuariosReferencia;
    FirebaseDatabase database;


    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        database = FirebaseDatabase.getInstance();
        usuariosReferencia = database.getReference(FirebaseReference.USUARIOS_REFERENCIA);

        mAuth = FirebaseAuth.getInstance();

        btnregistrar = (Button) findViewById(R.id.btnRegistrar);
        btnCancelarR = (Button)  findViewById(R.id.btnCancelarRegistro);

        Enombre = (EditText) findViewById(R.id.Rnombre);
        Eapellido = (EditText) findViewById(R.id.Rapellido);
        Ecorreo = (EditText) findViewById(R.id.Rcorreo);
        Econtraseña = (EditText) findViewById(R.id.Rcontraseña);
        Erepetir = (EditText) findViewById(R.id.Rcontraseña2);
        pat = Patterns.EMAIL_ADDRESS;
        /*mAuthListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
            }
        };*/

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerStart();
            }
        });
        btnCancelarR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registro.this , baseEjemplo.class);
                startActivity(i);
                finish();
            }
        });



    }

    private void registerStart () {
        final String nom1 = Enombre.getText().toString().trim();
        final String ape1 = Eapellido.getText().toString().trim();
        String co1 = Ecorreo.getText().toString().trim();
        String cont1 = Econtraseña.getText().toString().trim();
        final String rep1 = Erepetir.getText().toString().trim();

        if (!TextUtils.isEmpty(nom1) && !TextUtils.isEmpty(ape1) && !TextUtils.isEmpty(co1) && !TextUtils.isEmpty(cont1) && !TextUtils.isEmpty(rep1)) {
            if (cont1.length() < 6) {
                Toast.makeText(Registro.this,"La contraseña debe tener al menos 6 caracteres",Toast.LENGTH_SHORT).show();
            }
            if (!cont1.equals(rep1)) {
                Toast.makeText(Registro.this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
            }
            if (!pat.matcher(co1).matches()) {
                Toast.makeText(Registro.this,"El formato de correo no es valido",Toast.LENGTH_SHORT).show();
            }
            else {
                Usuarios usuario1 = new Usuarios(nom1,ape1,co1,cont1);
                usuariosReferencia.child("User"+nom1.substring(0,3)+ape1.substring(0,5)).setValue(usuario1);
                mAuth.createUserWithEmailAndPassword(co1, cont1)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("SESION", "createUserWithEmail:success");
                                    Toast.makeText(Registro.this, "Registrado", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Registro.this , baseEjemplo.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("SESION", "createUserWithEmail:failure", task.getException());

                                }

                            }
                        });
            }
        }
        else{
            Toast.makeText(Registro.this,"Todos los campos son obligatorios",Toast.LENGTH_SHORT).show();
        }
    }


}
