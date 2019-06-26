package com.colsubsidio.arprueba;

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

import com.colsubsidio.arprueba.augmentedimage.AugmentedImageActivity;

public class VideoActivity extends AppCompatActivity {
    public VideoView videoFull;
    private MediaPlayer mediaPlayer;
    private int display_mode = 0;
    private float scX = 0.0f;
    private float ratioDisplay = 0.0f;
    private float ratioVideo = 0.0f;
    private SeekBar seekVideo;
    private ImageButton buttonPlay;

    public boolean conD = true;
    public boolean conD2 = true;

    Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        seekVideo = findViewById(R.id.seekvideo);
        buttonPlay = findViewById(R.id.buttonplayvideo);

        datos = getIntent().getExtras();
        Integer continueVideo = datos.getInt("stateVideo");
        Integer numVideo = datos.getInt("numVideo");
        Log.e("VideoActivity" , "loco " + String.valueOf(numVideo));

        videoFull = findViewById(R.id.videofull);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.video;

        if(numVideo == 0){
            path = "android.resource://" + getPackageName() + "/" + R.raw.video;
            mediaPlayer = MediaPlayer.create(this, R.raw.video);
        }
        if(numVideo == 2){
            path = "android.resource://" + getPackageName() + "/" + R.raw.video2;
            mediaPlayer = MediaPlayer.create(this, R.raw.video2);
        }
        Uri uri = Uri.parse(path);
        videoFull.setVideoURI(uri);


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        display_mode = getResources().getConfiguration().orientation;
        ratioDisplay = Float.valueOf(metrics.widthPixels) / Float.valueOf(metrics.heightPixels);
        ratioVideo  = Float.valueOf(mediaPlayer.getVideoWidth()) / Float.valueOf(mediaPlayer.getVideoHeight());

        if(display_mode == Configuration.ORIENTATION_LANDSCAPE){
            if(ratioDisplay >= ratioVideo){
                scX = Float.valueOf(metrics.heightPixels) / Float.valueOf(mediaPlayer.getVideoHeight());
            } else {
                scX = Float.valueOf(metrics.widthPixels) / Float.valueOf(mediaPlayer.getVideoWidth());
            }

        } else if(display_mode == Configuration.ORIENTATION_PORTRAIT) {
            scX = 1;
            if(mediaPlayer.getVideoWidth() >= metrics.widthPixels){
                scX = 1.0f;
            } else {
                scX = Float.valueOf(metrics.widthPixels) / Float.valueOf(mediaPlayer.getVideoWidth());
            }
        }

        videoFull.setScaleX(scX);
        videoFull.setScaleY(scX);

        videoFull.seekTo(continueVideo);
        videoFull.start();
        buttonPlay.setImageResource(R.drawable.ic_button_pause);
        new AsyncTaskVerificator().execute();
    }

    public void playButton(View view){
        if(conD2) {
            videoFull.pause();
            buttonPlay.setImageResource(R.drawable.ic_button_play);
            conD2=false;
        }else{
            videoFull.start();
            buttonPlay.setImageResource(R.drawable.ic_button_pause);
            conD2=true;
//            if(!conD9){
//                new AugmentedImageActivity.AsyncTaskVerificatorC().execute();
//            }
        }
    }

    public void exitFullButton(View view) {
//        Intent intent = new Intent(this, AugmentedImageActivity.class);
//        startActivity(intent);
        exitFunction(videoFull.getCurrentPosition());
    }
    public void exitButton(View view){
        exitFunction(videoFull.getDuration());
    }
    public void exitFunction(int duration){
        Intent i = getIntent();
        i.putExtra("continuar Video", duration);
        Log.e("VideoActivity", "Bun A " + String.valueOf(videoFull.getCurrentPosition()));
        setResult(RESULT_OK, i);
        videoFull.pause();
        finish();
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
                            exitFunction(videoFull.getCurrentPosition());
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
