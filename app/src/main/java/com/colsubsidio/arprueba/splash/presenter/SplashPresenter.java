package com.colsubsidio.arprueba.splash.presenter;

import android.app.Activity;
import android.content.Context;

public interface SplashPresenter {
    void splashError(String string);
    String getValueString(Integer value);
    void animationWelcome();
    void animationIntroView();
    void InitialAnimation();
    void goWelcome();
    void cerrarApp();
    void requestPermissionCamera(Activity activity);
    Boolean checkVersion();
    Boolean checkPermissionCamera(Context context,Boolean installRequested);
    Boolean chekARCore(Context context,Boolean installRequested);
}
