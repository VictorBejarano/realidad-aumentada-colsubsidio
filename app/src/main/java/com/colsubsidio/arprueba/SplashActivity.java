package com.colsubsidio.arprueba;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.colsubsidio.arprueba.augmentedimage.AugmentedImageActivity;

public class SplashActivity extends AppCompatActivity {

    private AnimatorSet anim = new AnimatorSet();
    private TextView Welcome;
    private LinearLayout Verificator;
    private boolean conD0 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Welcome = findViewById(R.id.welcome);
        Verificator = findViewById(R.id.verificator);
        Welcome.setAlpha(0.0f);
        Verificator.setAlpha(0.0f);
        Verificator.setScaleY(0.01f);
        Verificator.setScaleX(0.01f);

        ValueAnimator alphaAnim0 = ObjectAnimator.ofFloat(Welcome,"alpha",0.0f,1.0f);
        alphaAnim0.setDuration(2000);
        ValueAnimator alphaAnim1 = ObjectAnimator.ofFloat(Verificator,"alpha",0.0f,1.0f);
        alphaAnim1.setDuration(100);
        ValueAnimator alphaAnim2 = ObjectAnimator.ofFloat(Verificator,"ScaleX",1.0f);
        alphaAnim2.setDuration(400);
        alphaAnim2.setInterpolator(new DecelerateInterpolator());
        ValueAnimator alphaAnim3 = ObjectAnimator.ofFloat(Verificator,"ScaleY",1.0f);
        alphaAnim3.setInterpolator(new DecelerateInterpolator());
        alphaAnim3.setDuration(400);

        anim.play(alphaAnim0).before(alphaAnim1);
        anim.play(alphaAnim1).before(alphaAnim2);
        anim.play(alphaAnim2).with(alphaAnim3);
        anim.start();

        new AsyncTaskVerificator().execute();


////
//        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
//        // La App esta en ejecución
//        if (permissionCheck == PackageManager.PERMISSION_GRANTED){
//            Log.e("SplashActvity", "Sasha Permiso concedido");
//
//        } else if (permissionCheck == PackageManager.PERMISSION_DENIED){
//
//
//            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
//                mostrarExplicacion();
//
//            }else{
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
//                Log.e("SplashActvity", "Sasha B acepto");
//            }
//
//        }
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

    public void acceptButton(View view){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
    }

    public void cancelButton(View view){
        finish();
    }
    private void initAugmentedActivity(){
        Intent intent = new Intent(this, AugmentedImageActivity.class);
        startActivity(intent);
    }

    private class AsyncTaskVerificator extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... strings) {
            conD0 = true;
            while (conD0) {
                try {
                    int permissionCheck = (int) ContextCompat.checkSelfPermission( SplashActivity.this, Manifest.permission.CAMERA);
                    if (permissionCheck == PackageManager.PERMISSION_GRANTED){
                        initAugmentedActivity();
                        conD0 = false;
                    }
                }
                catch (Exception e) {
                    return e.getLocalizedMessage();
                }
            }
            return null;
        }
    }
}
