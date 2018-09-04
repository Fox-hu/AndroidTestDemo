package com.umeng.soexample.share.sina;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.VideoSourceObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.umeng.soexample.share.IShare;
import com.umeng.soexample.share.ShareListener;
import com.umeng.soexample.share.ShareType;

import java.io.File;

/**
 * Created by fox.hu on 2018/9/3.
 */

public class SinaShare implements IShare, WbShareCallback {
    private static final String TAG = SinaShare.class.getSimpleName();
    private WbShareHandler shareHandler;
    private ShareListener listener;
    private Context context;

    public SinaShare(@NonNull Activity activity) {
        context = activity.getApplicationContext();
        shareHandler = new WbShareHandler(activity);
        shareHandler.registerApp();
    }

    @Override
    public boolean isInstall(Context context) {
        return WbSdk.isWbInstall(context);
    }

    @Override
    public void shareTo(ShareType type, Activity activity, Bundle bundle, ShareListener listener) {
        this.listener = listener;
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        switch (type) {
            case TEXT:
                weiboMessage.textObject = getTextObj(bundle);
                break;
            case IMAGE:
                weiboMessage.imageObject = getImageObj(bundle);
                break;
            case VIDEO:
                weiboMessage.videoSourceObject = getVideoObject(bundle);
                break;
            case APP:
                break;
            case MULTIPLE:
                weiboMessage.textObject = getTextObj(bundle);
                weiboMessage.imageObject = getImageObj(bundle);
                weiboMessage.videoSourceObject = getVideoObject(bundle);
                break;
            default:
                break;
        }
        shareHandler.shareMessage(weiboMessage, false);
    }

    private VideoSourceObject getVideoObject(Bundle bundle) {
        String filePath = bundle.getString(SinaShareHelper.VIDEO_PATH_KEY, "");
        if (TextUtils.isEmpty(filePath)) {
            return null;
        } else {
            VideoSourceObject videoSourceObject = new VideoSourceObject();
            videoSourceObject.videoPath = Uri.fromFile(new File(filePath));
            return videoSourceObject;
        }
    }

    private ImageObject getImageObj(Bundle bundle) {
        int imgUrl = bundle.getInt(SinaShareHelper.IMG_URL_KEY, 0);
        if (imgUrl == 0) {
            return null;
        } else {
            ImageObject imageObject = new ImageObject();
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imgUrl);
            imageObject.setImageObject(bitmap);
            return imageObject;
        }
    }

    private TextObject getTextObj(Bundle bundle) {
        TextObject textObject = new TextObject();
        textObject.text = bundle.getString(SinaShareHelper.TEXT_CONTENT_KEY, "");
        textObject.title = bundle.getString(SinaShareHelper.TEXT_TITLE_KEY, "");
        textObject.actionUrl = bundle.getString(SinaShareHelper.TEXT_ACTION_KEY, "");
        return textObject;
    }

    @Override
    public void onNewIntent(Intent intent) {
        shareHandler.doResultIntent(intent, this);
    }

    @Override
    public void onActivityResultData(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onWbShareSuccess() {
        listener.onComplete("share success!");
    }

    @Override
    public void onWbShareCancel() {
        listener.onCancel();
    }

    @Override
    public void onWbShareFail() {
        listener.onError("share failed");
    }
}
