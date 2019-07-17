package com.colsubsidio.arprueba.wellcome.presenter;

import android.content.Context;

public interface WelcomePresenter {
    void PreferenceManager(Context context);
    void launchHomeScreen();
    void checkFistTimeLaunch();
    void finishWelcome();
    boolean isFirstTimeLaunch();
    void setFirstTimeLaunch(boolean isFirstTime);
}
