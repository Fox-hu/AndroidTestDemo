package com.example.materialdesigntestdemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by admin on 2018/1/26.
 */

public class InitApp extends Application {
    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }
}
