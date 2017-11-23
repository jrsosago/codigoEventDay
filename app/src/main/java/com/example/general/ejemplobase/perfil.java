package com.example.general.ejemplobase;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sosa on 19/10/2017.
 */

public class perfil extends AppCompatActivity {

    Button cerrarSesion;
    TextView nombreP;
    ImageView fotoP;
    public static String nombre;
    public static String apellido;
    private static final int GALLERY_INTENT=3;



    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_perfil);

        Drawable originalDrawable = getResources().getDrawable(R.drawable.perfil);
        Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);

        roundedDrawable.setCornerRadius(originalBitmap.getHeight());

        fotoP = (ImageView) findViewById(R.id.fotoDePerfil);
        fotoP.setImageDrawable(roundedDrawable);

        cerrarSesion=(Button)findViewById(R.id.buttonCerrarSesion);
        nombreP = (TextView) findViewById(R.id.nombrePerfil);

        nombre = "Usuario";
        apellido = " ";
        nombreP.setText(nombre+" "+apellido);

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(perfil.this, baseEjemplo.class);
                startActivity(i);
                finish();
            }
        });

        fotoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, GALLERY_INTENT);

            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);

        if(requestCode==GALLERY_INTENT && resultCode==RESULT_OK){
            Uri uri= data.getData();

            fotoP.setImageURI(uri);
        }
    }

}
