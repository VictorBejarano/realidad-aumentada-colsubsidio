package com.colsubsidio.arprueba.videofullscreen.presenter;

import android.widget.ImageButton;

public interface VideoPresenter {
    void loadVideo(int numVideo);
    void checkOrientation(int videoWidth, int videoHeight);
    void configVideo(int continueVideo);
    void playVideo();
    void pauseVideo();
    void switchVideo(ImageButton buttonPlay);
}
