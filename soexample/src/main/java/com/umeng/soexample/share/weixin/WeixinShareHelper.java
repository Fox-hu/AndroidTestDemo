package com.umeng.soexample.share.weixin;

import android.os.Bundle;

import com.umeng.soexample.share.ShareParamsHelper;
import com.umeng.soexample.share.ShareType;

/**
 * Created by fox.hu on 2018/9/7.
 */

public class WeixinShareHelper implements ShareParamsHelper<WeixinShareBean> {
    public static final String WEIXIN_BUNDLE = "weixin_bundle";

    @Override
    public Bundle createParams(ShareType type, WeixinShareBean weixinShareBean) {
        final Bundle params = new Bundle();
        params.putParcelable(WEIXIN_BUNDLE, weixinShareBean);
        return params;
    }

    public static WeixinShareBean createText(String text, String desc) {
        WeixinShareBean.Builder builder = new WeixinShareBean.Builder();
        return builder.text(text).mediaDescr(desc).bulid();
    }

    public static WeixinShareBean createImg(String imgPath) {
        WeixinShareBean.Builder builder = new WeixinShareBean.Builder();
        return builder.imgPath(imgPath).bulid();
    }

    public static WeixinShareBean createMp3(String mp3Url, String title, String desc,
            int thumbRes) {
        WeixinShareBean.Builder builder = new WeixinShareBean.Builder();
        return builder.mp3Url(mp3Url).mediaTitle(title).mediaDescr(desc).thumbImgId(thumbRes)
                .bulid();
    }

    public static WeixinShareBean createVideo(String videoUrl, String title, String desc,
            int thumbRes) {
        WeixinShareBean.Builder builder = new WeixinShareBean.Builder();
        return builder.videoUrl(videoUrl).mediaTitle(title).mediaDescr(desc).thumbImgId(thumbRes)
                .bulid();
    }
}
