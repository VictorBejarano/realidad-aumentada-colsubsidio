package com.colsubsidio.arprueba.splash.interactor;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.LinearLayout;

import com.colsubsidio.arprueba.R;
import com.colsubsidio.arprueba.splash.presenter.SplashPresenter;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;

public class SplashInteractorImpl implements SplashInteractor {
    private SplashPresenter presenter;

    public SplashInteractorImpl(SplashPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Boolean checkVersion() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
            presenter.splashError(presenter.getValueString(R.string.error_version));
            presenter.cerrarApp();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void InitialAnimation() {
        presenter.animationWelcome();
        presenter.animationIntroView();
    }

    @Override
    public void requestPermissionCamera(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, 100);
    }

    @Override
    public Boolean checkPermissionCamera(Context context,Boolean installRequested) {
        int permissionCheck = (int) ContextCompat.checkSelfPermission( context, Manifest.permission.CAMERA);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            return presenter.chekARCore(context,installRequested);
        } else {
            return true;
        }
    }

    @Override
    public Boolean checkARCore(Context context, Boolean installRequested) {
        Exception exception = null;
        String message = null;
        try {
            switch (ArCoreApk.getInstance().requestInstall((Activity) context, !installRequested)) {
                case INSTALL_REQUESTED:
                    //installRequested = true;
                    //Were is the device dont have ARCore
                    break;
                case INSTALLED:
                    presenter.goWelcome();
                    return false;
            }
            //Solicita el permiso de camara
        } catch (UnavailableUserDeclinedInstallationException e) {
            message = "Please install ARCore ";
            exception = e;
            presenter.splashError(message + exception.toString());
        } catch (UnavailableDeviceNotCompatibleException e) {
            message = "This device does not support AR ";
            exception = e;
            presenter.splashError(message + exception.toString());
        } catch (Exception e) {
            message = "Failed to create AR session ";
            exception = e;
            presenter.splashError(message + exception.toString());
        }

        return true;
    }
}
