package app.mrobot.cn.toutiaoexample;

import android.app.Application;
import android.content.Context;

import app.mrobot.cn.toutiaoexample.utils.SdkManager;

/**
 * Created by admin on 2018/1/26.
 */

public class InitApp extends Application {
    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;

        if (BuildConfig.DEBUG) {
            SdkManager.initStetho(this);
        }
    }
}
