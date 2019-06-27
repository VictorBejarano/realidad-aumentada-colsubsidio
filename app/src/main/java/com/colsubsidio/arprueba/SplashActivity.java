package com.colsubsidio.arprueba;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.colsubsidio.arprueba.augmentedimage.AugmentedImageActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        // La App esta en ejecución
        if (permissionCheck == PackageManager.PERMISSION_GRANTED){
            Log.e("SplashActvity", "Sasha Permiso concedido");

        } else if (permissionCheck == PackageManager.PERMISSION_DENIED){


            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
                mostrarExplicacion();

            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
                Log.e("SplashActvity", "Sasha B acepto");
            }

        }
    }

    private void mostrarExplicacion(){
        new AlertDialog.Builder(this)
                .setTitle("Autorización")
                .setMessage("Necesito permiso para acceder a los contactos de tu dispositivo.")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                        }

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Mensaje acción cancelada
                        mensajeAccionCancelada();
                    }
                })
        .show();
        Log.e("SplashActvity", "Sasha Permiso concedido");

    }
    public void mensajeAccionCancelada(){
        Toast.makeText(getApplicationContext(),
                "Haz rechazado la petición, no puede continuar con la aplicación.",
                Toast.LENGTH_SHORT).show();
        finish();
    }
}
