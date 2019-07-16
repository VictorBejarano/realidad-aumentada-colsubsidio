package com.colsubsidio.arprueba.splash.view;

public interface SplashView {
    void animationWelcome();
    void animationIntroView();
    void checkVersion();
    void splashError(String error);
    void buttonAccept();
    void buttonCancel();
    void goWelcome();
}
