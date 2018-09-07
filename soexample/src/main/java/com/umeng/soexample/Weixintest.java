package com.umeng.soexample;

import android.content.Context;

import com.umeng.soexample.share.weixin.WeixinShareBean;
import com.umeng.soexample.share.weixin.WeixinShareHelper;

/**
 * Created by fox.hu on 2018/9/4.
 */

public class Weixintest {

    public static WeixinShareBean getText() {
        return WeixinShareHelper.createText("hello,world", "hello world");
    }

    public static WeixinShareBean getImg(Context context) {
        return WeixinShareHelper.createImg(
                context.getExternalFilesDir(null) + "/test.png");
    }

    public static WeixinShareBean getVideo() {
        return WeixinShareHelper.createVideo("http://www.qq.com", "Video Title",
                "Video Description", R.drawable.umeng_socialize_alipay);
    }

    public static WeixinShareBean getMp3() {
        return WeixinShareHelper.createMp3(
                "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3",
                "Music Title", "usic Album", R.drawable.umeng_socialize_alipay);
    }
}
