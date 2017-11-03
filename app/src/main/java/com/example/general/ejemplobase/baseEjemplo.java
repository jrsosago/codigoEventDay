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
import android.widget.Toast;

import com.example.general.ejemplobase.Objetos.FirebaseReference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class baseEjemplo extends AppCompatActivity {

    private static final String TAG ="Ivan" ;
    EditText campoCorreo;
    EditText campoContraseña;
    Button btnlogin;
    TextView registrarse;
    TextView recuperar;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_ejemplo);


        btnlogin = (Button) findViewById(R.id.botonLogin);
        campoCorreo = (EditText) findViewById(R.id.CampoCorreo);
        campoContraseña = (EditText) findViewById(R.id.CampoCorreo);
        registrarse = (TextView) findViewById(R.id.textRegistro);
        recuperar = (TextView) findViewById(R.id.textRecuperar);
        mAuth = FirebaseAuth.getInstance();

        /*mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null){
                    Intent i = new Intent(baseEjemplo.this , localizacion.class);
                    startActivity(i);
                    finish();
                }  else {
                    Toast.makeText(baseEjemplo.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        };*/


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //loggearUser();
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
    /*private void loggearUser (){
        String email = campoCorreo.getText().toString().trim();
        String password = campoContraseña.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(baseEjemplo.this, "Fallo",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }*/

    /*@Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);

        }
    }*/

}
