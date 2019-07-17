package com.colsubsidio.arprueba.videofullscreen.interactor;

import android.widget.ImageButton;

import com.colsubsidio.arprueba.R;
import com.colsubsidio.arprueba.videofullscreen.presenter.VideoPresenter;

public class VideoInteractorImpl implements VideoInteractor {
    private VideoPresenter presenter;

    public boolean conditionSwitch = true;

    public VideoInteractorImpl(VideoPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void switchVideo(ImageButton buttonPlay) {
        if(conditionSwitch) {
            presenter.pauseVideo();
            buttonPlay.setImageResource(R.drawable.ic_button_play);
            conditionSwitch=false;
        }else{
            presenter.playVideo();
            buttonPlay.setImageResource(R.drawable.ic_button_pause);
            conditionSwitch=true;
        }
    }
}
