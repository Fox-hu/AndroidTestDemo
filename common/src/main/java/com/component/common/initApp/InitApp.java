package com.component.common.initApp;

import android.app.Application;
import android.content.Context;

/**
 * Created by admin on 2018/1/26.
 */

public class InitApp extends Application {
    private static final String TAG = InitApp.class.getSimpleName();

    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }
}
