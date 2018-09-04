package com.umeng.soexample.auth;

import android.app.Activity;

import com.umeng.soexample.PlatForm;
import com.umeng.soexample.auth.qq.QQAuth;
import com.umeng.soexample.auth.sina.SinaAuth;
import com.umeng.soexample.auth.weixin.WeiXinAuth;

/**
 * @author fox.hu
 * @date 2018/8/16
 */

public class AuthFactory {

    public static IAuth generate(Activity activity, PlatForm platForm) {
        IAuth iAuth = null;
        switch (platForm) {
            case QQ:
                iAuth = new QQAuth(activity);
                break;
            case WEIXIN:
                iAuth = new WeiXinAuth(activity);
                break;
            case SINA:
                iAuth = new SinaAuth(activity);
                break;
            default:
                break;
        }
        return iAuth;
    }
}
