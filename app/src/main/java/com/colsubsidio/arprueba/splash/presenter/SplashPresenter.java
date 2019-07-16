package com.colsubsidio.arprueba.splash.presenter;

import android.app.Activity;
import android.content.Context;

public interface SplashPresenter {
    void splashError(String string);
    void animationWelcome();
    void animationIntroView();
    void InitialAnimation();
    void goWelcome();
    void requestPermissionCamera(Activity activity);
    Boolean checkPermissionCamera(Context context,Boolean installRequested);
    Boolean chekARCore(Context context,Boolean installRequested);
}
