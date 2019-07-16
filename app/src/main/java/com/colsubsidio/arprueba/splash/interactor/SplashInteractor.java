package com.colsubsidio.arprueba.splash.interactor;

import android.app.Activity;
import android.content.Context;

public interface SplashInteractor {
    void InitialAnimation();
    void requestPermissionCamera(Activity activity);
    Boolean checkPermissionCamera(Context context,Boolean installRequested);
    Boolean checkARCore(Context context,Boolean installRequested);
}
