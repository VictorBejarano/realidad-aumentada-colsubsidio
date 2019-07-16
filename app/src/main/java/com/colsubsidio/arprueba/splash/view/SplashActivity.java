package com.colsubsidio.arprueba.splash.view;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.colsubsidio.arprueba.R;
import com.colsubsidio.arprueba.WelcomeActivity;
import com.colsubsidio.arprueba.splash.presenter.SplashPresenter;
import com.colsubsidio.arprueba.splash.presenter.SplashPresenterImpl;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;

public class SplashActivity extends AppCompatActivity implements SplashView{

    private SplashPresenter presenter;

    private boolean installRequested;
    private AnimatorSet animationWindow = new AnimatorSet();
    private TextView Welcome;
    private LinearLayout Verificator;
    private boolean conditionPermission = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Welcome     = findViewById(R.id.welcome);
        Verificator = findViewById(R.id.verificator);

        Welcome     .setAlpha(0.0f);
        Verificator .setAlpha(0.0f);
        Verificator .setScaleY(0.01f);
        Verificator .setScaleX(0.01f);

        presenter = new SplashPresenterImpl(this);
        presenter.InitialAnimation();

        installRequested = false;
        new AsyncTaskVerificator().execute();
    }

    public void acceptButton(View view){
        buttonAccept();
    }

    public void cancelButton(View view){
        buttonCancel();
    }
    @Override
    public void animationWelcome() {
        ValueAnimator welcomeAnimation = ObjectAnimator.ofFloat(Welcome,"alpha",0.0f,1.0f);
        welcomeAnimation.setDuration(2000);
        welcomeAnimation.start();
    }

    @Override
    public void animationIntroView() {
        ValueAnimator alphaAnim1 = ObjectAnimator.ofFloat(Verificator,"alpha",0.0f,1.0f);
        alphaAnim1.setStartDelay(2000);
        alphaAnim1.setDuration(100);
        ValueAnimator alphaAnim2 = ObjectAnimator.ofFloat(Verificator,"ScaleX",1.0f);
        alphaAnim2.setDuration(400);
        alphaAnim2.setInterpolator(new DecelerateInterpolator());
        ValueAnimator alphaAnim3 = ObjectAnimator.ofFloat(Verificator,"ScaleY",1.0f);
        alphaAnim3.setInterpolator(new DecelerateInterpolator());
        alphaAnim3.setDuration(400);
        animationWindow.play(alphaAnim1).before(alphaAnim2);
        animationWindow.play(alphaAnim2).with(alphaAnim3);
        animationWindow.start();
    }

    @Override
    public void checkVersion() {

    }

    @Override
    public void splashError(String error) {
        Toast.makeText(this, getString(R.string.error_splash) + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void buttonAccept() {
        presenter.requestPermissionCamera(this);
    }

    @Override
    public void buttonCancel() {
        finish();
    }

    @Override
    public void goWelcome() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    private class AsyncTaskVerificator extends AsyncTask<String,Integer,String> {
        @Override
        protected String doInBackground(String... string) {
            conditionPermission = true;
            while (conditionPermission) {
                try {
                    conditionPermission = presenter.checkPermissionCamera(SplashActivity.this,installRequested);
                }
                catch (Exception e) {
                    splashError(String.valueOf(e));
                    return e.getLocalizedMessage();
                }
            }
            return null;
        }
    }
}
