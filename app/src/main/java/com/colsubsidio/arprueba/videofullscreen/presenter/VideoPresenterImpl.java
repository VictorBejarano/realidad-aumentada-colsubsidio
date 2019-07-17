package com.colsubsidio.arprueba.videofullscreen.presenter;

import android.widget.ImageButton;

import com.colsubsidio.arprueba.videofullscreen.interactor.VideoInteractor;
import com.colsubsidio.arprueba.videofullscreen.interactor.VideoInteractorImpl;
import com.colsubsidio.arprueba.videofullscreen.view.VideoActivityView;

public class VideoPresenterImpl implements VideoPresenter {
    private VideoActivityView VideoActivityView;
    private VideoInteractor interactor;

    public VideoPresenterImpl(com.colsubsidio.arprueba.videofullscreen.view.VideoActivityView videoActivityView) {
        VideoActivityView = videoActivityView;
        interactor = new VideoInteractorImpl(this);
    }

    @Override
    public void loadVideo(int numVideo) {
        VideoActivityView.loadVideo(numVideo);
    }

    @Override
    public void checkOrientation(int videoWidth, int videoHeight) {
        VideoActivityView.checkOrientation(videoWidth,videoHeight);
    }

    @Override
    public void configVideo(int continueVideo) {
        VideoActivityView.configVideo(continueVideo);
    }

    @Override
    public void playVideo() {
        VideoActivityView.playVideo();
    }

    @Override
    public void pauseVideo() {
        VideoActivityView.pauseVideo();
    }

    @Override
    public void switchVideo(ImageButton buttonPlay) {
        interactor.switchVideo(buttonPlay);
    }
}
