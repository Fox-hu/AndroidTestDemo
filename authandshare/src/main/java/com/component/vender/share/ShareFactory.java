package com.component.vender.share;

import android.app.Activity;

import com.component.vender.platform.PlatForm;
import com.component.vender.share.qq.QQShare;
import com.component.vender.share.sina.SinaShare;
import com.component.vender.share.wechat.WeChatShare;

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
