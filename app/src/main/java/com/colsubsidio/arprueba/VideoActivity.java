package com.colsubsidio.arprueba;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.media.VolumeShaper;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {
    public VideoView videoFull;
    private MediaPlayer mediaPlayer;
    private int display_mode = 0;
    private float scX = 0.0f;
    private float ratioDisplay = 0.0f;
    private float ratioVideo = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        videoFull = findViewById(R.id.videofull);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(path);
        videoFull.setVideoURI(uri);
        mediaPlayer = MediaPlayer.create(this, R.raw.video);

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


        Log.e("VideoActivity", "Parse " + String.valueOf(display_mode));
        videoFull.getHeight();

        videoFull.start();

    }
}
