package com.colsubsidio.arprueba.splash.presenter;

import android.app.Activity;
import android.content.Context;

import com.colsubsidio.arprueba.splash.interactor.SplashInteractor;
import com.colsubsidio.arprueba.splash.interactor.SplashInteractorImpl;
import com.colsubsidio.arprueba.splash.view.SplashView;

public class SplashPresenterImpl implements SplashPresenter {

    private SplashView SplashView;
    private SplashInteractor interactor;

    public SplashPresenterImpl(com.colsubsidio.arprueba.splash.view.SplashView splashView) {
        SplashView = splashView;
        interactor = new SplashInteractorImpl(this);
    }

    @Override
    public void splashError(String string) {
        SplashView.splashError(string);
    }

    @Override
    public void animationWelcome() {
        SplashView.animationWelcome();
    }

    @Override
    public void animationIntroView() {
        SplashView.animationIntroView();
    }

    @Override
    public void InitialAnimation() {
        interactor.InitialAnimation();
    }

    @Override
    public void goWelcome() {
        SplashView.goWelcome();
    }

    @Override
    public void requestPermissionCamera(Activity activity) {
        interactor.requestPermissionCamera(activity);

    }

    @Override
    public Boolean checkPermissionCamera(Context context,Boolean installRequested) {
        return interactor.checkPermissionCamera(context,installRequested);
    }

    @Override
    public Boolean chekARCore(Context context,Boolean installRequested) {
        return interactor.checkARCore(context, installRequested);
    }
}
