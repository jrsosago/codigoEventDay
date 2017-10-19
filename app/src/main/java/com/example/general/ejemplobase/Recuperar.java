package com.example.general.ejemplobase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Recuperar extends AppCompatActivity {
    Button enviarCorreo;
    Button cancelarEnvio;
    FirebaseAuth mAuth;
    EditText recuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);

        enviarCorreo = (Button) findViewById(R.id.btnEnviarCorreo);
        cancelarEnvio = (Button) findViewById(R.id.btnCancelarRecuperar);
        recuperar = (EditText) findViewById(R.id.recuperarClave);
        mAuth = FirebaseAuth.getInstance();

        enviarCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarContraseña();
            }
        });
        cancelarEnvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Recuperar.this , baseEjemplo.class);
                startActivity(i);
                finish();
            }
        });


    }
    private void recuperarContraseña () {
        String correo = recuperar.getText().toString().trim();
        if (!TextUtils.isEmpty(correo)){
            mAuth.sendPasswordResetEmail(correo).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Recuperar.this, "Contraseña enviada", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Recuperar.this, baseEjemplo.class);
                        startActivity(i);
                        finish();
                    }
                }
            });
        }
        else{
            Toast.makeText(Recuperar.this,"Por favor escribe tu correo",Toast.LENGTH_SHORT).show();
        }
    }
}
