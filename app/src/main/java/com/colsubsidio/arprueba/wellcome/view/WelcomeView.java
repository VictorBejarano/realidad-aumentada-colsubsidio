package com.colsubsidio.arprueba.wellcome.view;

public interface WelcomeView {
    void launchHomeScreen();
    void finishWelcome();
    void addBottomDots(int currentPage);
    void changeStatusBarColor();
    int getItem(int i);
}
