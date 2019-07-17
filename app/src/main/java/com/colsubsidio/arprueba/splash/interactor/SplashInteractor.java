package com.colsubsidio.arprueba.splash.interactor;

import android.app.Activity;
import android.content.Context;

public interface SplashInteractor {
    boolean checkVersion();
    void InitialAnimation();
    void requestPermissionCamera(Activity activity);
    boolean checkPermissionCamera(Context context,Boolean installRequested);
    boolean checkARCore(Context context,Boolean installRequested);
}
