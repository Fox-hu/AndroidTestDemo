package com.umeng.soexample.share;

import android.app.Activity;

import com.umeng.soexample.platform.PlatForm;
import com.umeng.soexample.share.qq.QQShare;
import com.umeng.soexample.share.sina.SinaShare;
import com.umeng.soexample.share.wechat.WeChatShare;

/**
 * Created by fox.hu on 2018/9/3.
 */

public class ShareFactory {

    public static IShare generateShare(Activity activity, PlatForm platForm) {
        IShare iShare = null;
        switch (platForm) {
            case QQ:
                iShare = new QQShare(activity);
                break;
            case SINA:
                iShare = new SinaShare(activity);
                break;
            case WEIXIN:
                iShare = new WeChatShare(activity);
                break;
            default:
                break;
        }
        return iShare;
    }
}
