package com.colsubsidio.arprueba;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.colsubsidio.arprueba.augmentedimage.AugmentedImageActivity;

public class MainActivity extends AppCompatActivity {
    private AnimatorSet anim = new AnimatorSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Slide explode = new Slide(Gravity.LEFT);
//            //explode.setStartDelay(2000);
//            explode.setDuration(10000);
//            this.getWindow().setEnterTransition(explode);
            this.getWindow().setAllowEnterTransitionOverlap(false);
//
//        }

        setContentView(R.layout.activity_main);
        ImageView LogoCol = (ImageView) findViewById(R.id.logoColsubsidio);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ImageView LogoCol = (ImageView) findViewById(R.id.logoColsubsidio);
        ImageView LogoCulEdu = (ImageView) findViewById(R.id.logoCulEdu);
        ValueAnimator alphaAnim0 = ObjectAnimator.ofFloat(LogoCol,"alpha",0.0f,1.0f);
        alphaAnim0.setDuration(2000);
        ValueAnimator alphaAnim1 = ObjectAnimator.ofFloat(LogoCulEdu,"alpha",0.0f,1.0f);
        alphaAnim1.setDuration(2000);

        anim.play(alphaAnim0).with(alphaAnim1);
        anim.start();
        //new AsyncTaskVerificator().execute();
    }
    private class AsyncTaskVerificator extends AsyncTask<String,Integer,String>{
        private boolean conD = true;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... strings) {
            while (conD) {

                try {
                    //Thread.sleep(100)
                    Log.e("MainActivity", "Juego " + anim.isRunning());

                    if(!anim.isRunning()){


                        conD = false;
                        //prueba();
                    }
                }
                catch (Exception e) {
                    return e.getLocalizedMessage();
                }
            }
            return "Button Pressed";


        }
    }
    public void prueba(View view){
        Intent intent = new Intent(this, AugmentedImageActivity.class);
       // finishAfterTransition();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide slide = new Slide(Gravity.RIGHT);
            slide.setDuration(1000);
            //explode.setStartDelay(5000);
//            this.getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            this.getWindow().setExitTransition(slide);
            this.startActivity(intent,ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
            // Apply activity transition
        } else {
            // Swap without transition
            this.startActivity(intent);

        }

    }
}
