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
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.colsubsidio.arprueba.augmentedimage.AugmentedImageActivity;
import com.colsubsidio.arprueba.augmentedimage.helpers.CameraPermissionHelper;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.Session;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;


import java.util.EnumSet;

public class SplashActivity extends AppCompatActivity {
    private boolean installRequested;
    private AnimatorSet anim = new AnimatorSet();
    private AnimatorSet anim2 = new AnimatorSet();
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
        installRequested = false;

        new AsyncTaskVerificator().execute();
    }

    public void acceptButton(View view){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
        verificatorFunction();
    }

    public void cancelButton(View view){
        finish();
    }
    private void initAugmentedActivity(){
        Intent intent = new Intent(this, AugmentedImageActivity.class);
        startActivity(intent);
    }
    private boolean verificatorFunction(){
        int permissionCheck = (int) ContextCompat.checkSelfPermission( SplashActivity.this, Manifest.permission.CAMERA);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {

            Exception exception = null;
            String message = null;
            try {
                switch (ArCoreApk.getInstance().requestInstall(SplashActivity.this, !installRequested)) {
                    case INSTALL_REQUESTED:
                        installRequested = true;
                        break;
                    case INSTALLED:
                        initAugmentedActivity();
                        return false;
                }
                //Solicita el permiso de camara
            } catch (UnavailableUserDeclinedInstallationException e) {
                message = "Please install ARCore";
                exception = e;
            } catch (UnavailableDeviceNotCompatibleException e) {
                message = "This device does not support AR";
                exception = e;
            } catch (Exception e) {
                message = "Failed to create AR session";
                exception = e;
            }

        }
        return true;

    }

    private class AsyncTaskVerificator extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... strings) {
            conD0 = true;
            while (conD0) {
                try {
                    conD0 = verificatorFunction();

                }
                catch (Exception e) {
                    return e.getLocalizedMessage();
                }
            }
            return null;
        }
    }
}
