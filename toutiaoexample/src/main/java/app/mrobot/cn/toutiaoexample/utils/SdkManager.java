package app.mrobot.cn.toutiaoexample.utils;

import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by fox on 2018/3/7.
 */

public class SdkManager {
    public static void initStetho(Context context){
        Stetho.initializeWithDefaults(context);
    }
}
