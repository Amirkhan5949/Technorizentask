package com.codeinger.technorizentask.utils;

import android.app.Application;
import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPrefsManager.initialize(this);
    }
}
