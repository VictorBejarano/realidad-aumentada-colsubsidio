package com.colsubsidio.arprueba.augmentedimage;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.colsubsidio.arprueba.R;
import com.colsubsidio.arprueba.augmentedimage.helpers.CameraPermissionHelper;
import com.colsubsidio.arprueba.augmentedimage.helpers.SnackbarHelper;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.Frame;
import com.google.ar.core.Session;
import com.google.ar.core.TrackingState;
import com.google.ar.core.exceptions.CameraNotAvailableException;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class AugmentedImageActivity extends AppCompatActivity {

    private AnimatorSet anim = new AnimatorSet();
    private AnimatorSet anim2 = new AnimatorSet();
    private AnimatorSet anim3 = new AnimatorSet();
    private AnimatorSet anim4 = new AnimatorSet();
    //private AnimatorSet anim5 = new AnimatorSet();
    private float MenuDp = 250.0f;

    private DisplayMetrics metrics = new DisplayMetrics();
    private MediaPlayer audioView000;

    public boolean conD = true;
    public boolean conD2 = true;
    public boolean conD3 = true;
    public boolean conD4 = true;
    public boolean conD5 = true;
    public boolean conD6 = true;
    public boolean conD7 = true;
    public boolean conD8 = true;
    public boolean conD9 = true;

    private FrameLayout menuCon;
    private LinearLayout playBar;
    private ImageView burguer0;
    private ImageView burguer1;
    private ImageView burguer2;
    private ImageButton buttonPlay;
    private SeekBar seekAudio;
    private ImageButton buttonFull;
    private VideoView videoFull;

    private static final int SPIDER_RENDERABLE = 1;
    private static final String TAG = "AugmentedImageActivity";
    private final Map<AugmentedImage, AugmentedImageNode> augmentedImageMap = new HashMap<>();
    private Session mSession;
    private final SnackbarHelper messageSnackbarHelper = new SnackbarHelper();
    private boolean installRequested;
    private ViewRenderable testViewRenderable;
    private ArFragment arFragment;
    private AugmentedImageNode aux2;
    private AugmentedImage aux;
    private AugmentedImage aux3;
    private AugmentedImage aux4;
    private ModelRenderable spiderRenderable;
    private ModelLoader modelLoader;
    private int condicion2 = 0;
    private boolean conditionInit = false;
    private boolean conditionAux = false;
    private ImageView fitToScanView;

    @SuppressLint("WrongViewCast")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindowManager().getDefaultDisplay().getMetrics(metrics);


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Slide explode = new Slide(Gravity.RIGHT);
//            //explode.setStartDelay(2000);
//            explode.setDuration(1000);
//            this.getWindow().setEnterTransition(explode);
//            //this.getWindow().setAllowEnterTransitionOverlap(false);
//
//        }
       // getWindow().setAllowEnterTransitionOverlap(false);
        setContentView(R.layout.activity_augmented_image);
        //this.getWindow().setAllowEnterTransitionOverlap(false);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);
        fitToScanView = findViewById(R.id.image_view_fit_to_scan);
        menuCon = findViewById(R.id.menuview);
        playBar = findViewById(R.id.playbar);
        burguer0 = findViewById(R.id.burguer0);
        burguer1 = findViewById(R.id.burguer1);
        burguer2 = findViewById(R.id.burguer2);
        buttonPlay = findViewById(R.id.buttonplay);
        seekAudio = findViewById(R.id.seekaudio);
        videoFull = findViewById(R.id.videofull);
        arFragment.getArSceneView().getScene().addOnUpdateListener(this::onUpdateFrame);
        maybeEnableArButton();
        //getWindow().setAllowEnterTransitionOverlap(false);
        fitToScanView.setAlpha(0.0f);
        videoFull.setVideoURI(R.raw.video);



//        modelLoader = new ModelLoader(this);
//
//        modelLoader.loadModel(SPIDER_RENDERABLE, R.raw.spider_3);
        audioView000 = MediaPlayer.create(this, R.raw.audio000);

        installRequested = false;
    }

    @Override
    protected void onResume() {
        super.onResume();




        int width = metrics.widthPixels; // ancho absoluto en pixels
        int height = metrics.heightPixels;



        menuCon.setX(-(MenuDp * metrics.densityDpi) / 160.0f);

        playBar.setY(metrics.heightPixels);


        //playBar.setY(metrics.heightPixels-(48 * metrics.densityDpi) / 160.0f);

        ImageView LogoCol = (ImageView) findViewById(R.id.logoColsubsidio);
        ImageView LogoCulEdu = (ImageView) findViewById(R.id.logoCulEdu);
        ConstraintLayout MainView = (ConstraintLayout) findViewById(R.id.mainview);
        LogoCol.setAlpha(0.0f);
        LogoCulEdu.setAlpha(0.0f);
        ValueAnimator alphaAnim0 = ObjectAnimator.ofFloat(LogoCol,"alpha",0.0f,1.0f);
        alphaAnim0.setStartDelay(500);
        alphaAnim0.setDuration(2000);
        ValueAnimator alphaAnim1 = ObjectAnimator.ofFloat(LogoCulEdu,"alpha",0.0f,1.0f);
        alphaAnim1.setStartDelay(1000);
        alphaAnim1.setDuration(2000);
        ValueAnimator alphaAnim2 = ObjectAnimator.ofFloat(MainView,"translationX",-width);
        alphaAnim2.setStartDelay(500);
        alphaAnim2.setDuration(1500);
        anim.play(alphaAnim0).with(alphaAnim1);
        anim.play(alphaAnim1).before(alphaAnim2);
        anim.start();

        new AsyncTaskVerificator().execute();

        if (augmentedImageMap.isEmpty()) {
            fitToScanView.setVisibility(View.VISIBLE);
        }
        if (mSession == null) {
            Exception exception = null;
            String message = null;
            try {
                switch (ArCoreApk.getInstance().requestInstall(this, !installRequested)) {
                    case INSTALL_REQUESTED:
                        installRequested = true;
                        return;
                    case INSTALLED:
                        break;
                }
                //Solicita el permiso de camara
                if (!CameraPermissionHelper.hasCameraPermission(this)) {
                    CameraPermissionHelper.requestCameraPermission(this);
                    return;
                }
                //Crea la sesion
                mSession = new Session(this, EnumSet.of(Session.Feature.SHARED_CAMERA));
            } catch (UnavailableArcoreNotInstalledException
                    | UnavailableUserDeclinedInstallationException e) {
                message = "Please install ARCore";
                exception = e;
            } catch (UnavailableApkTooOldException e) {
                message = "Please update ARCore";
                exception = e;
            } catch (UnavailableSdkTooOldException e) {
                message = "Please update this app";
                exception = e;
            } catch (UnavailableDeviceNotCompatibleException e) {
                message = "This device does not support AR";
                exception = e;
            } catch (Exception e) {
                message = "Failed to create AR session";
                exception = e;
            }
            if (message != null) {
                messageSnackbarHelper.showError(this, message);
                Log.e(TAG, "Exception creating session", exception);
                return;
            }
        }
        try {
            mSession.resume();
        } catch (CameraNotAvailableException e) {
            messageSnackbarHelper.showError(this, "Camera not available. Please restart the app.");
            mSession = null;
            return;

        }
    }

    private void onUpdateFrame(FrameTime frameTime) {
        if(!conD4) {
            Frame frame = arFragment.getArSceneView().getArFrame();
            if (frame == null || frame.getCamera().getTrackingState() != TrackingState.TRACKING) {
                //  condicion = 0;
                //Log.e(TAG,"Pureba");
                return;
            }
            Collection<AugmentedImage> updatedAugmentedImages =
                    frame.getUpdatedTrackables(AugmentedImage.class);

            for (AugmentedImage augmentedImage : updatedAugmentedImages) {
                switch (augmentedImage.getTrackingState()) {
                    case PAUSED:
                        //String text = "Detected Image " + augmentedImage.getIndex();
                        break;
                    case TRACKING:
                        AugmentedImageNode node = new AugmentedImageNode(this);
                        //Almacena cada nodo de cada marcador detectado en una collection en cada session
                        if (!augmentedImageMap.containsKey(augmentedImage)) {
                            node.setImage(augmentedImage, this);
                            augmentedImageMap.put(augmentedImage, node);
                            conditionInit = true;
                        }
                        if (augmentedImage.getTrackingMethod() ==
                                AugmentedImage.TrackingMethod.FULL_TRACKING) {
                            aux = augmentedImage;
                        }
                        break;
                    case STOPPED:
                        augmentedImageMap.remove(augmentedImage);
                        break;
                }
            }
//////////Visualiza la imagen segun el marcador que se este visualizando.///////////////////////////////
//////////Deja de visualizar cuando no se esta visualizando ningun marcador/////////////////////////////
//            if (conditionInit) {
//

//            if (aux.getTrackingMethod() == AugmentedImage.TrackingMethod.FULL_TRACKING) {
//                if (!conditionAux) {
//                    fitToScanView.setVisibility(View.GONE);
//                    if((aux.getIndex() == 0) && (!conD5)){
//                        augmentedImageMap.get(aux).mediaPlayer.start();
//                        conD5 = true;
//                    }
//                    arFragment.getArSceneView().getScene().addChild(augmentedImageMap.get(aux));
//                    conditionAux = true;
//                }
//            } else {
//                if (conditionAux) {
//                    fitToScanView.setVisibility(View.VISIBLE);
//                    arFragment.getArSceneView().getScene().removeChild(augmentedImageMap.get(aux));
//                    conditionAux = false;
//                }
//            }

////////////////////////////////////////////////////////////////////////////////////////////////////
            if(conditionInit){

//                if (aux.getTrackingMethod() == AugmentedImage.TrackingMethod.FULL_TRACKING) {
//                    if (!conditionAux) {
//                        fitToScanView.setVisibility(View.GONE);
//                        if((aux.getIndex() == 0) && (!conD5)){
//                            augmentedImageMap.get(aux).mediaPlayer.start();
//                            conD5 = true;
//                        }
//                        arFragment.getArSceneView().getScene().addChild(augmentedImageMap.get(aux));
//                        conditionAux = true;
//                    }
//                } else {
//                    if (conditionAux) {
//                        fitToScanView.setVisibility(View.VISIBLE);
//                        arFragment.getArSceneView().getScene().removeChild(augmentedImageMap.get(aux));
//                        conditionAux = false;
//                    }
//                }
/////////////////////////////
                switch (aux.getIndex()){
                    case 0:

                        if(conD5){
                            fitToScanView.setVisibility(View.GONE);
                            arFragment.getArSceneView().getScene().addChild(augmentedImageMap.get(aux));
                            ValueAnimator alphaAnim1 = ObjectAnimator.ofFloat(buttonFull,"alpha",0.0f,1.0f);
                            alphaAnim1.setDuration(500);
                            alphaAnim1.start();

                            conD5 = false;
                            aux4 = aux;
                        }
                        if((aux.getTrackingMethod() == AugmentedImage.TrackingMethod.LAST_KNOWN_POSE) && !conD6){
                            augmentedImageMap.get(aux).mediaPlayer.pause();
                            conD6 = true;
                        } else if((aux.getTrackingMethod() == AugmentedImage.TrackingMethod.FULL_TRACKING) && conD6){
                            augmentedImageMap.get(aux).mediaPlayer.start();
                            conD6 = false;
                        }

                        if(augmentedImageMap.get(aux).mediaPlayer.getCurrentPosition() >= augmentedImageMap.get(aux).mediaPlayer.getDuration()){
                            arFragment.getArSceneView().getScene().removeChild(augmentedImageMap.get(aux));
                            fitToScanView.setVisibility(View.VISIBLE);
                            if(aux.getTrackingMethod() == AugmentedImage.TrackingMethod.LAST_KNOWN_POSE){
                                conD5 = true;
                            }
                        }
                        //obligatorio para todos
                        if(!conD7){
                            arFragment.getArSceneView().getScene().removeChild(augmentedImageMap.get(aux3));
                            conD7 = true;
                            audioView000.pause();
                            audioView000.seekTo(0);
                            animPlay();
                        }
                        break;
                    case 1:
                        if(conD7){
                            fitToScanView.setVisibility(View.GONE);
                            arFragment.getArSceneView().getScene().addChild(augmentedImageMap.get(aux));
                            ValueAnimator alphaAnim0 = ObjectAnimator.ofFloat(playBar,"translationY",metrics.heightPixels-(48 * metrics.densityDpi) / 160.0f);
                            alphaAnim0.setDuration(1000);
                            alphaAnim0.start();
                            buttonPlay.setImageResource(R.drawable.ic_button_pause);
                            audioView000.start();
                            new AsyncTaskVerificatorC().execute();
                            conD7 = false;
                            aux3 = aux;
                        }
                        if(!conD5){
                            arFragment.getArSceneView().getScene().removeChild(augmentedImageMap.get(aux4));
                            conD5 = true;
                            //audioView000.stop();
                            //animPlay();
                        }
                        break;
                }
            }
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////
//        if(conditionInit){
//            if(aux3 != aux){
//
//                arFragment.getArSceneView().getScene().addChild(augmentedImageMap.get(aux));
//                if(conditionAux){
//                    arFragment.getArSceneView().getScene().removeChild(augmentedImageMap.get(aux3));
//                }
//                conditionAux=true;
//            }
//        }
//        aux3=aux;

    }
    //Cierra la app si no tiene los permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] results) {
        if (!CameraPermissionHelper.hasCameraPermission(this)) {
            Toast.makeText(this, "Camera permission is needed to run this application", Toast.LENGTH_LONG)
                    .show();
            if (!CameraPermissionHelper.shouldShowRequestPermissionRationale(this)) {
                // Permission denied with checking "Do not ask again".
                CameraPermissionHelper.launchPermissionSettings(this);
            }
            finish();
        }
    }
    public void playButton(View view){
        if(conD8) {
            audioView000.pause();
            buttonPlay.setImageResource(R.drawable.ic_button_play);
            conD8=false;
        }else{
            audioView000.start();
            buttonPlay.setImageResource(R.drawable.ic_button_pause);
            conD8=true;
            if(!conD9){
                new AsyncTaskVerificatorC().execute();
            }
        }
    }
    public void mainButton(View view){
        if(!conD){
//        arFragment.getArSceneView().getScene().removeChild(augmentedImageMap.get(aux));
//        mSession.close();
//        finish();

        FrameLayout whitewindow = (FrameLayout) findViewById(R.id.whitewindow);
        ImageButton botonAR = (ImageButton) findViewById(R.id.botonar);
//        whitewindow.setVisibility(View.GONE);
//        botonAR.setVisibility(View.GONE);

//        ConstraintLayout MainView = (ConstraintLayout) findViewById(R.id.mainview);
        ValueAnimator alphaAnim0 = ObjectAnimator.ofFloat(whitewindow,"alpha",1.0f,0.0f);
        alphaAnim0.setDuration(2000);
        ValueAnimator alphaAnim1 = ObjectAnimator.ofFloat(botonAR,"alpha",1.0f,0.0f);
        alphaAnim1.setDuration(2000);
        ValueAnimator alphaAnim2 = ObjectAnimator.ofFloat(fitToScanView,"alpha",0.0f,1.0f);
        alphaAnim2.setDuration(3000);

        anim2.play(alphaAnim0).with(alphaAnim1);
        anim2.play(alphaAnim1).with(alphaAnim2);
        anim2.start();
        conD2 = true;
        conD4 = false;
        new AsyncTaskVerificatorB().execute();
        }
    }
    public void burgerButton(View view){
        int tiempoMenu = 500;
        if(conD3){
            ValueAnimator alphaAnim0 = ObjectAnimator.ofFloat(menuCon,"translationX",0.0f);
            alphaAnim0.setDuration(1000);
            alphaAnim0.setInterpolator(new DecelerateInterpolator());
            ValueAnimator alphaAnim1 = ObjectAnimator.ofFloat(burguer0,"rotation",45.0f);
            alphaAnim1.setDuration(tiempoMenu);
            alphaAnim1.setInterpolator(new DecelerateInterpolator());
            ValueAnimator alphaAnim2 = ObjectAnimator.ofFloat(burguer2,"rotation",-45.0f);
            alphaAnim2.setDuration(tiempoMenu);
            alphaAnim2.setInterpolator(new DecelerateInterpolator());
            ValueAnimator alphaAnim3 = ObjectAnimator.ofFloat(burguer0,"translationY",(16 * metrics.densityDpi) / 160.0f);
            alphaAnim3.setDuration(tiempoMenu);
            alphaAnim3.setInterpolator(new DecelerateInterpolator());
            ValueAnimator alphaAnim4 = ObjectAnimator.ofFloat(burguer2,"translationY",-(16 * metrics.densityDpi) / 160.0f);
            alphaAnim4.setDuration(tiempoMenu);
            alphaAnim4.setInterpolator(new DecelerateInterpolator());
            ValueAnimator alphaAnim5 = ObjectAnimator.ofFloat(burguer1,"alpha",1.0f,0.0f);
            alphaAnim5.setDuration(tiempoMenu);
            alphaAnim5.setInterpolator(new DecelerateInterpolator());
            anim3.play(alphaAnim0).with(alphaAnim1);
            anim3.play(alphaAnim1).with(alphaAnim2);
            anim3.play(alphaAnim2).with(alphaAnim3);
            anim3.play(alphaAnim3).with(alphaAnim4);
            anim3.play(alphaAnim4).with(alphaAnim5);
            anim3.start();
            conD3=false;
        }else{
            ValueAnimator alphaAnim0 = ObjectAnimator.ofFloat(menuCon,"translationX",-(MenuDp * metrics.densityDpi) / 160.0f);
            alphaAnim0.setDuration(1000);
            alphaAnim0.setInterpolator(new DecelerateInterpolator());
            ValueAnimator alphaAnim1 = ObjectAnimator.ofFloat(burguer0,"rotation",0.0f);
            alphaAnim1.setDuration(tiempoMenu);
            alphaAnim1.setInterpolator(new DecelerateInterpolator());
            ValueAnimator alphaAnim2 = ObjectAnimator.ofFloat(burguer2,"rotation",0.0f);
            alphaAnim2.setDuration(tiempoMenu);
            alphaAnim2.setInterpolator(new DecelerateInterpolator());
            ValueAnimator alphaAnim3 = ObjectAnimator.ofFloat(burguer0,"translationY",0.0f);
            alphaAnim3.setDuration(tiempoMenu);
            alphaAnim3.setInterpolator(new DecelerateInterpolator());
            ValueAnimator alphaAnim4 = ObjectAnimator.ofFloat(burguer2,"translationY",0.0f);
            alphaAnim4.setDuration(tiempoMenu);
            alphaAnim4.setInterpolator(new DecelerateInterpolator());
            ValueAnimator alphaAnim5 = ObjectAnimator.ofFloat(burguer1,"alpha",0.0f,1.0f);
            alphaAnim5.setDuration(tiempoMenu);
            alphaAnim5.setInterpolator(new DecelerateInterpolator());
            anim4.play(alphaAnim0).with(alphaAnim1);
            anim4.play(alphaAnim1).with(alphaAnim2);
            anim4.play(alphaAnim2).with(alphaAnim3);
            anim4.play(alphaAnim3).with(alphaAnim4);
            anim4.play(alphaAnim4).with(alphaAnim5);
            anim4.start();
            conD3=true;
        }

    }
    //Verifica si el dispositivo soporta ARCORE
    void maybeEnableArButton() {
        ArCoreApk.Availability availability = ArCoreApk.getInstance().checkAvailability(this);
        if (availability.isTransient()) {
            // Re-query at 5Hz while compatibility is checked in the background.
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    maybeEnableArButton();
                }
            }, 200);
        }
        if (availability.isSupported()) {
            Log.e(TAG,"Lo soporta");
        } else {
            Log.e(TAG,"No soporta");
        }
    }
    void setRenderable(int id, ModelRenderable renderable) {
        if (id == SPIDER_RENDERABLE) {
            this.spiderRenderable = renderable;
        }
    }
    void onException(int id, Throwable throwable) {
        Toast toast = Toast.makeText(this, "Unable to load renderable: " + id, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        Log.e(TAG, "Unable to load andy renderable", throwable);
    }
    private class AsyncTaskVerificator extends AsyncTask<String,Integer,String> {

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
            conD = true;
            while (conD) {

                try {
                    //Thread.sleep(100)
                    Log.e(TAG, "Juego " + anim.isRunning());

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
    private class AsyncTaskVerificatorB extends AsyncTask<String,Integer,String> {

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
            conD2 = true;
            FrameLayout whitewindow = (FrameLayout) findViewById(R.id.whitewindow);
            ImageButton botonAR = (ImageButton) findViewById(R.id.botonar);
            while (conD2) {

                try {
                    //Thread.sleep(100)
                   // Log.e(TAG, "Juego " + anim.isRunning());

                    if(!anim2.isRunning()){

                        botonAR.setVisibility(View.GONE);
                        whitewindow.setVisibility(View.GONE);

                        conD2 = false;

                    }
                }
                catch (Exception e) {
                    return e.getLocalizedMessage();
                }
            }
            return "Button Pressed";


        }
    }
    private class AsyncTaskVerificatorC extends AsyncTask<String,Integer,String> {

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
            conD9 = true;
            while (conD9) {

                try {
                    //Log.e(TAG, "Boom " + seekAudio.getProgress());
                    seekAudio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                            audioView000.pause();

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            audioView000.seekTo(seekAudio.getProgress());
                            if(conD8) {
                                audioView000.start();
                            }

                        }
                    });
                    seekAudio.setMax(audioView000.getDuration());
                    seekAudio.setProgress(audioView000.getCurrentPosition());

                    if(audioView000.getCurrentPosition() >= audioView000.getDuration()){
                        buttonPlay.setImageResource(R.drawable.ic_button_play);
                        conD9=false;
                        conD8=false;
                        seekAudio.setProgress(0);
//                        animPlay();
                    }

                }
                catch (Exception e) {
                    return e.getLocalizedMessage();
                }
            }
            return "Button Pressed";


        }
    }
    public void animPlay(){
        ValueAnimator alphaAnim0 = ObjectAnimator.ofFloat(playBar,"translationY",metrics.heightPixels);
        alphaAnim0.setDuration(1000);
        alphaAnim0.start();
    }
}
