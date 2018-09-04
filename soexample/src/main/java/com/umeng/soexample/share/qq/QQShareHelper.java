package com.umeng.soexample.share.qq;

import android.os.Bundle;

import com.tencent.connect.share.QQShare;
import com.umeng.soexample.share.ShareType;

/**
 * Created by fox.hu on 2018/9/4.
 */

public class QQShareHelper {
    private static final String TAG = QQShareHelper.class.getSimpleName();

    public Bundle createBundle(ShareType type, QQShareBean bean) {
        final Bundle params = new Bundle();
        int shareType;
        switch (type) {
            case TEXT:
                shareType = QQShare.SHARE_TO_QQ_TYPE_DEFAULT;
                break;
            case IMAGE:
                shareType = QQShare.SHARE_TO_QQ_TYPE_IMAGE;
                break;
            case VIDEO:
                shareType = QQShare.SHARE_TO_QQ_TYPE_AUDIO;
                break;
            case APP:
                shareType = QQShare.SHARE_TO_QQ_TYPE_APP;
                break;
            default:
                shareType = QQShare.SHARE_TO_QQ_TYPE_DEFAULT;
                break;
        }
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, shareType);

        if (type != ShareType.IMAGE) {
            params.putString(QQShare.SHARE_TO_QQ_TITLE, bean.getTitle());
            params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, bean.getTargetUrl());
            params.putString(QQShare.SHARE_TO_QQ_SUMMARY, bean.getSummary());
            params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, bean.getImgUrl());
        } else {
            params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, bean.getImgLocalUrl());
        }

        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, bean.getAppName());
        if (type == ShareType.VIDEO) {
            params.putString(QQShare.SHARE_TO_QQ_AUDIO_URL, bean.getAudioUrl());
        }

        return params;
    }
}
