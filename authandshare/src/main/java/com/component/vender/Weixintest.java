package com.component.vender;

import android.content.Context;

import com.component.vender.share.wechat.WeChatShareBean;
import com.component.vender.share.wechat.WeChatShareHelper;

/**
 * Created by fox.hu on 2018/9/4.
 */

public class Weixintest {

    public static WeChatShareBean getText() {
        return WeChatShareHelper.createText("hello,world", "hello world");
    }

    public static WeChatShareBean getImg(Context context) {
        return WeChatShareHelper.createImg(
                context.getExternalFilesDir(null) + "/test.png");
    }

    public static WeChatShareBean getVideo() {
        return WeChatShareHelper.createVideo("http://www.qq.com", "Video Title",
                "Video Description", R.drawable.umeng_socialize_alipay);
    }

    public static WeChatShareBean getMp3() {
        return WeChatShareHelper.createMp3(
                "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3",
                "Music Title", "usic Album", R.drawable.umeng_socialize_alipay);
    }
}
