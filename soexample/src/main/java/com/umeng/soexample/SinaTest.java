package com.umeng.soexample;

import android.content.Context;

import com.umeng.soexample.share.sina.SinaShareBean;
import com.umeng.soexample.share.sina.SinaShareHelper;

/**
 * Created by fox.hu on 2018/9/4.
 */

public class SinaTest {
    public static SinaShareBean getText() {
        return SinaShareHelper.createText("我正在使用微博客户端发博器分享文字", "xxxx", "http://www.baidu.com");
    }

    public static SinaShareBean getImg() {
        return SinaShareHelper.createImg(R.drawable.umeng_socialize_alipay);
    }

    public static SinaShareBean getvideo(Context context) {
        return SinaShareHelper.createVideo(context.getExternalFilesDir(null) + "/eeee.mp4");
    }

    public static SinaShareBean getMulti() {
        return SinaShareHelper.createMultiple("hello world", "hello world", "www.baidu.com",
                R.drawable.umeng_socialize_alipay, null);
    }
}
