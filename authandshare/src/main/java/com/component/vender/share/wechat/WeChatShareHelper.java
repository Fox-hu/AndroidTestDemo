package com.component.vender.share.wechat;

import android.os.Bundle;

import com.component.vender.share.ShareParamsHelper;
import com.component.vender.share.ShareType;

/**
 * Created by fox.hu on 2018/9/7.
 */

public class WeChatShareHelper implements ShareParamsHelper<com.component.vender.share.wechat.WeChatShareBean> {
    public static final String WE_CHAT_BUNDLE = "weixin_bundle";

    @Override
    public Bundle createParams(ShareType type, com.component.vender.share.wechat.WeChatShareBean weChatShareBean) {
        final Bundle params = new Bundle();
        params.putParcelable(WE_CHAT_BUNDLE, weChatShareBean);
        return params;
    }

    public static com.component.vender.share.wechat.WeChatShareBean createText(String text, String desc) {
        com.component.vender.share.wechat.WeChatShareBean.Builder builder = new com.component.vender.share.wechat.WeChatShareBean.Builder();
        return builder.text(text).mediaDescr(desc).bulid();
    }

    public static com.component.vender.share.wechat.WeChatShareBean createImg(String imgPath) {
        com.component.vender.share.wechat.WeChatShareBean.Builder builder = new com.component.vender.share.wechat.WeChatShareBean.Builder();
        return builder.imgPath(imgPath).bulid();
    }

    public static com.component.vender.share.wechat.WeChatShareBean createMp3(String mp3Url, String title, String desc,
            int thumbRes) {
        com.component.vender.share.wechat.WeChatShareBean.Builder builder = new com.component.vender.share.wechat.WeChatShareBean.Builder();
        return builder.mp3Url(mp3Url).mediaTitle(title).mediaDescr(desc).thumbImgId(thumbRes)
                .bulid();
    }

    public static com.component.vender.share.wechat.WeChatShareBean createVideo(String videoUrl, String title, String desc,
            int thumbRes) {
        com.component.vender.share.wechat.WeChatShareBean.Builder builder = new com.component.vender.share.wechat.WeChatShareBean.Builder();
        return builder.videoUrl(videoUrl).mediaTitle(title).mediaDescr(desc).thumbImgId(thumbRes)
                .bulid();
    }
}
