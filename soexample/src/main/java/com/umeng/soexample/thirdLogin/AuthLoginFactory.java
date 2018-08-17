package com.umeng.soexample.thirdLogin;

import android.app.Activity;

import com.umeng.soexample.thirdLogin.qq.QQLogin;

/**
 * Created by fox.hu on 2018/8/16.
 */

public class AuthLoginFactory {
    public static ILogin generate(Activity activity, PlatForm platForm) {
        ILogin iLogin;
        switch (platForm) {
            case QQ:
                iLogin = new QQLogin(activity);
                break;
            default:
                iLogin = new QQLogin(activity);
                break;
        }
        return iLogin;
    }
}
