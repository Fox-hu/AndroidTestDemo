package com.component.vender.auth;

import android.app.Activity;

import com.component.vender.platform.PlatForm;
import com.component.vender.auth.qq.QQAuth;
import com.component.vender.auth.sina.SinaAuth;
import com.component.vender.auth.wechat.WeChatAuth;

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
                iAuth = new WeChatAuth(activity);
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
