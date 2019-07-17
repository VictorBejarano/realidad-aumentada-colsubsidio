package com.colsubsidio.arprueba.videofullscreen.view;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.media.VolumeShaper;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.VideoView;

import com.colsubsidio.arprueba.R;
import com.colsubsidio.arprueba.augmentedimage.AugmentedImageActivity;
import com.colsubsidio.arprueba.videofullscreen.presenter.VideoPresenter;
import com.colsubsidio.arprueba.videofullscreen.presenter.VideoPresenterImpl;

public class VideoActivity extends AppCompatActivity implements VideoActivityView {

    private VideoPresenter presenter;

    public VideoView videoFull;
    private int display_mode = 0;
    private float scX = 0.0f;
    private float ratioDisplay = 0.0f;
    private float ratioVideo = 0.0f;
    private SeekBar seekVideo;
    private ImageButton buttonPlay;
    public DisplayMetrics metrics = new DisplayMetrics();

    public boolean conD = true;
    public boolean conD2 = true;


    Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        presenter           = new VideoPresenterImpl(this);

        seekVideo           = findViewById(R.id.seekVideo);
        buttonPlay          = findViewById(R.id.buttonPlayVideo);
        videoFull           = findViewById(R.id.videofull);

        buttonPlay.setImageResource(R.drawable.ic_button_pause);

        datos               = getIntent().getExtras();

        int continueVideo   = datos.getInt("stateVideo");
        int numVideo        = datos.getInt("numVideo");
        int videoWidth      = datos.getInt("videoWidth");
        int videoHeight     = datos.getInt("videoHeight");

        display_mode        = getResources().getConfiguration().orientation;
        ratioDisplay        = Float.valueOf(metrics.widthPixels) / Float.valueOf(metrics.heightPixels);
        ratioVideo          = Float.valueOf(videoWidth) / Float.valueOf(videoHeight);

        presenter.loadVideo(numVideo);
        presenter.checkOrientation(videoWidth,videoHeight);
        presenter.configVideo(continueVideo);
        presenter.playVideo();

        new AsyncTaskVerificator().execute();
    }

    public void playButton(View view){
        presenter.switchVideo(buttonPlay);
    }

    public void exitFullButton(View view) {
//        Intent intent = new Intent(this, AugmentedImageActivity.class);
//        startActivity(intent);
        exitFunction(videoFull.getCurrentPosition(),false);
    }
    public void exitButton(View view){
        exitFunction(videoFull.getDuration(),true);
    }
    public void exitFunction(int duration ,boolean conditionVideo){
        Bundle bundle = new Bundle();
        bundle.putInt("continuar Video", duration);
        bundle.putBoolean("video Condicional", conditionVideo);
        Intent i = getIntent();
        i.putExtras(bundle);
        Log.e("VideoActivity", "Bun A " + String.valueOf(videoFull.getCurrentPosition()));
        setResult(RESULT_OK, i);
        videoFull.pause();
        finish();
    }

    @Override
    public void loadVideo(int numVideo) {
        String path = new String();
        switch (numVideo){
            case 0:
                path = "android.resource://" + getPackageName() + "/" + R.raw.video;
                break;
            case 2:
                path = "android.resource://" + getPackageName() + "/" + R.raw.video2;
                break;
        }
        Uri uri = Uri.parse(path);
        videoFull.setVideoURI(uri);

    }

    @Override
    public void checkOrientation(int videoWidth, int videoHeight) {
        switch (display_mode){
            case Configuration.ORIENTATION_LANDSCAPE:
                if(ratioDisplay >= ratioVideo){
                    scX = Float.valueOf(metrics.heightPixels) / Float.valueOf(videoHeight);
                } else {
                    scX = Float.valueOf(metrics.widthPixels) / Float.valueOf(videoWidth);
                }
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                scX = 1;
                if(videoWidth >= metrics.widthPixels){
                    scX = 1.0f;
                } else {
                    scX = Float.valueOf(metrics.widthPixels) / Float.valueOf(videoWidth);
                }
                break;
        }
    }

    @Override
    public void configVideo(int continueVideo) {
        videoFull.setScaleX(scX);
        videoFull.setScaleY(scX);
        videoFull.seekTo(continueVideo);
    }

    @Override
    public void playVideo() {
        videoFull.start();
    }

    @Override
    public void pauseVideo() {
        videoFull.pause();
    }

    private class AsyncTaskVerificator extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... strings) {
            conD = true;
            while (conD) {

                try {
                    //Log.e(TAG, "Boom " + seekAudio.getProgress());
                    seekVideo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                            videoFull.pause();

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            videoFull.seekTo(seekVideo.getProgress());
//                            if(conD8) {
                            videoFull.start();
//                            }

                        }
                    });
                    if(videoFull.isPlaying()) {
                        seekVideo.setMax(videoFull.getDuration());
                        seekVideo.setProgress(videoFull.getCurrentPosition());

//
                        if ((videoFull.getCurrentPosition() >= videoFull.getDuration())) {
//                        buttonPlay.setImageResource(R.drawable.ic_button_play);
                            exitFunction(videoFull.getCurrentPosition(),true);
                            conD = false;
                            //conD2=false;
//                        seekAudio.setProgress(0);
////                        animPlay();
                        }
                    }

                }
                catch (Exception e) {
                    return e.getLocalizedMessage();
                }
            }
            return "Button Pressed";
        }
    }
}
