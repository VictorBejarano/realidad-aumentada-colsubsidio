package com.colsubsidio.arprueba.wellcome.interactor;

import android.content.Context;

public interface WelcomeInteractor {
    void PreferenceManager(Context context);
    void checkFistTimeLaunch();
    boolean isFirstTimeLaunch();
    void setFirstTimeLaunch(boolean isFirstTime);
}
