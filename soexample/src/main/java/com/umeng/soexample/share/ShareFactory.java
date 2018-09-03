package com.umeng.soexample.share;

import android.app.Activity;

import com.umeng.soexample.PlatForm;
import com.umeng.soexample.share.qq.QQShare;

/**
 * Created by fox.hu on 2018/9/3.
 */

public class ShareFactory {

    public static IShare generate(Activity activity, PlatForm platForm) {
        IShare iShare = null;
        switch (platForm) {
            case QQ:
                iShare = new QQShare(activity);
                break;
            default:
                break;
        }
        return iShare;
    }
}
