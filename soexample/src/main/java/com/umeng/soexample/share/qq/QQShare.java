package com.umeng.soexample.share.qq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.umeng.soexample.authLogin.PlatForm;
import com.umeng.soexample.share.IShare;
import com.umeng.soexample.share.ShareListener;

/**
 * Created by fox.hu on 2018/8/31.
 */

public class QQShare implements IShare {
    private static final String TAG = QQShare.class.getSimpleName();
    private int shareType = com.tencent.connect.share.QQShare.SHARE_TO_QQ_TYPE_DEFAULT;
    private Tencent mTencent;
    private Activity activity;
    private ShareListener listener;

    QQShare(@NonNull Activity activity) {
        this.activity = activity;
        mTencent = Tencent.createInstance(PlatForm.QQ.getAppId(), activity.getApplicationContext());
    }


    @Override
    public void shareTo(@NonNull Activity activity, Bundle bundle,
            @NonNull ShareListener listener) {
        this.listener = listener;
        mTencent.shareToQQ(activity, bundle, qqShareListener);
    }

    @Override
    public void onActivityResultData(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_QQ_SHARE) {
            Tencent.onActivityResultData(requestCode,resultCode,data,qqShareListener);
        }
    }

    IUiListener qqShareListener = new IUiListener() {
        @Override
        public void onCancel() {
            if (shareType != com.tencent.connect.share.QQShare.SHARE_TO_QQ_TYPE_IMAGE) {

            }
        }

        @Override
        public void onComplete(Object response) {

        }

        @Override
        public void onError(UiError e) {

        }
    };
}
