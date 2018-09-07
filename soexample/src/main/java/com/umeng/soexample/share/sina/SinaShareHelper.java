package com.umeng.soexample.share.sina;

import android.os.Bundle;

import com.umeng.soexample.share.ShareParamsHelper;
import com.umeng.soexample.share.ShareType;

/**
 * Created by fox.hu on 2018/9/4.
 */

public class SinaShareHelper implements ShareParamsHelper<SinaShareBean> {
    public static final String SINA_BUNDLE = "sina_bundle";

    @Override
    public Bundle createParams(ShareType type, SinaShareBean sinaShareBean) {
        final Bundle params = new Bundle();
        params.putParcelable(SINA_BUNDLE, sinaShareBean);
        return params;
    }

    public static SinaShareBean createText(String text, String title, String actionUrl) {
        SinaShareBean.Builder builder = new SinaShareBean.Builder();
        return builder.text(text).title(title).actionUrl(actionUrl).bulid();
    }

    public static SinaShareBean createImg(int resId) {
        SinaShareBean.Builder builder = new SinaShareBean.Builder();
        return builder.resId(resId).bulid();
    }

    public static SinaShareBean createVideo(String audioPath) {
        SinaShareBean.Builder builder = new SinaShareBean.Builder();
        return builder.audioPath(audioPath).bulid();
    }

    public static SinaShareBean createMultiple(String text, String title, String actionUrl,
            int resId, String audioPath) {
        SinaShareBean.Builder builder = new SinaShareBean.Builder();
        return builder.text(text).title(title).actionUrl(actionUrl).resId(resId).audioPath(
                audioPath).bulid();
    }

}
