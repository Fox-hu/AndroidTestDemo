package com.umeng.soexample;

import android.content.Context;

import com.umeng.soexample.share.qq.QQShareBean;
import com.umeng.soexample.share.qq.QQShareHelper;

/**
 * Created by fox.hu on 2018/9/4.
 */

public class QQtest {
    private static final String TARGET_URL = "http://c.y.qq.com/v8/playsong.html?songid=109325260&songmid=000kuo2H2xJqfA&songtype=0&source=mqq&_wv=1";

    public static QQShareBean getText(Context context) {
        String title = context.getString(R.string.qqshare_title_content);
        String target = context.getString(R.string.qqshare_targetUrl_content);
        String img = context.getString(R.string.qqshare_imageUrl_content);
        String summary = context.getString(R.string.qqshare_summary_content);
        String app = context.getString(R.string.qqshare_appName_content);
        return QQShareHelper.createText(title, target, img, summary, app);
    }

    public static QQShareBean getImg(Context context) {
        String imageLocal = context.getString(R.string.qqshare_local_img);
        return QQShareHelper.createImg(imageLocal);
    }

    public static QQShareBean getAudio(Context context) {
        String title = context.getString(R.string.qqshare_audio_title);
        String imgUrl = context.getString(R.string.qqshare_audio_imgurl);
        String summary = context.getString(R.string.qqshare_audio_summary);
        String app = context.getString(R.string.qqshare_audio_appname);
        String audio = context.getString(R.string.qqshare_audioUrl);
        return QQShareHelper.createAudio(title, TARGET_URL, imgUrl, summary, app, audio);
    }

    public static QQShareBean getAPP(Context context) {
        String title = context.getString(R.string.qqshare_app_title);
        String targetUrl = context.getString(R.string.qqshare_app_targeturl);
        String imgUrl = context.getString(R.string.qqshare_app_imgurl);
        String summary = context.getString(R.string.qqshare_app_summary);
        return QQShareHelper.createApp(title, targetUrl, imgUrl, summary);
    }
}
