package com.colsubsidio.arprueba.wellcome.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.Window;
import android.view.WindowManager;

import com.colsubsidio.arprueba.wellcome.presenter.WelcomePresenter;

public class WelcomeInteractorImpl implements WelcomeInteractor {

    private WelcomePresenter presenter;

    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "intro_slider-welcome";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    public WelcomeInteractorImpl(WelcomePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void PreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    @Override
    public void checkFistTimeLaunch() {
        if (!presenter.isFirstTimeLaunch()) {
            presenter.launchHomeScreen();
            presenter.finishWelcome();
        }
    }

    @Override
    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    @Override
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

}
