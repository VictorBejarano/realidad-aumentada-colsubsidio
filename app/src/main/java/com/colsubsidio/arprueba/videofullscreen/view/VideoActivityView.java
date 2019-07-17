package com.colsubsidio.arprueba.videofullscreen.view;

public interface VideoActivityView {
    void loadVideo(int numVideo);
    void checkOrientation(int videoWidth, int videoHeight);
    void configVideo(int continueVideo);
    void playVideo();
    void pauseVideo();
}
