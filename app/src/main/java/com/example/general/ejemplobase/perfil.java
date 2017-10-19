package com.example.general.ejemplobase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by sosa on 19/10/2017.
 */

public class perfil extends AppCompatActivity {

    Button cerrarSesion;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_perfil);

        cerrarSesion=(Button)findViewById(R.id.buttonCerrarSesion);

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(perfil.this, baseEjemplo.class);
                startActivity(i);
                finish();
            }
        });

    }
}
