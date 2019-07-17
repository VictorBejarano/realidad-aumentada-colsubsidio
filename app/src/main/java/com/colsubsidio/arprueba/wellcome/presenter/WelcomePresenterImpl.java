package com.colsubsidio.arprueba.wellcome.presenter;

import android.content.Context;

import com.colsubsidio.arprueba.wellcome.interactor.WelcomeInteractor;
import com.colsubsidio.arprueba.wellcome.interactor.WelcomeInteractorImpl;
import com.colsubsidio.arprueba.wellcome.view.WelcomeView;

public class WelcomePresenterImpl implements WelcomePresenter {
    private WelcomeView WelcomeView;
    private WelcomeInteractor interactor;

    public WelcomePresenterImpl(com.colsubsidio.arprueba.wellcome.view.WelcomeView welcomeView) {
        WelcomeView = welcomeView;
        interactor = new WelcomeInteractorImpl(this);
    }

    @Override
    public void PreferenceManager(Context context) {
        interactor.PreferenceManager(context);
    }

    @Override
    public void launchHomeScreen() {
        WelcomeView.launchHomeScreen();
    }

    @Override
    public void checkFistTimeLaunch() {
        interactor.checkFistTimeLaunch();

    }

    @Override
    public void finishWelcome() {
        WelcomeView.finishWelcome();
    }

    @Override
    public boolean isFirstTimeLaunch() {
        return interactor.isFirstTimeLaunch();
    }

    @Override
    public void setFirstTimeLaunch(boolean isFirstTime) {
        interactor.setFirstTimeLaunch(isFirstTime);
    }
}
