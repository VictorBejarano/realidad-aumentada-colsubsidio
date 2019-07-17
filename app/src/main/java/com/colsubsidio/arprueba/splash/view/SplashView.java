package com.colsubsidio.arprueba.splash.view;

public interface SplashView {
    void animationWelcome();
    void animationIntroView();
    void splashError(String error);
    String getValueString(Integer value);
    void buttonAccept();
    void buttonCancel();
    void goWelcome();
}
