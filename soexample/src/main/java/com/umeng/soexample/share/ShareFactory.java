package com.umeng.soexample.share;

import android.app.Activity;

import com.umeng.soexample.PlatForm;
import com.umeng.soexample.share.qq.QQShare;
import com.umeng.soexample.share.qq.QQShareHelper;
import com.umeng.soexample.share.sina.SinaShare;
import com.umeng.soexample.share.sina.SinaShareHelper;

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
            default:
                break;
        }
        return iShare;
    }

    public static ShareParamsHelper generateHelper(PlatForm platForm) {
        ShareParamsHelper helper = null;
        switch (platForm) {
            case QQ:
                helper = new QQShareHelper();
                break;
            case SINA:
                helper = new SinaShareHelper();
                break;
            default:
                break;
        }
        return helper;
    }
}
