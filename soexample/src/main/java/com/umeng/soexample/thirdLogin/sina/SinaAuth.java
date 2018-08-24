package com.umeng.soexample.thirdLogin.sina;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.umeng.soexample.thirdLogin.IAuth;
import com.umeng.soexample.thirdLogin.PlatForm;
import com.umeng.soexample.thirdLogin.PlatFormInfo;
import com.umeng.soexample.thirdLogin.onAuthListener;
import com.umeng.soexample.thirdLogin.weixin.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by fox.hu on 2018/8/24.
 */

public class SinaAuth implements IAuth {
    private static final String TAG = SinaAuth.class.getSimpleName();
    private Activity mActivity;
    private Oauth2AccessToken mAccessToken;
    private SsoHandler mSsoHandler;
    private onAuthListener mAuthListener;

    public SinaAuth(@NonNull Activity activity) {
        mActivity = activity;
        WbSdk.install(activity,
                new AuthInfo(activity, PlatForm.SINA.getAppId(), PlatForm.SINA.getAppKey(),
                        Constants.SCOPE));
        mSsoHandler = new SsoHandler(activity);
    }

    @Override
    public boolean isInstall(Context context) {
        return false;
    }

    @Override
    public void fetchPlatFormInfo(Activity activity, onAuthListener listener) {
        mAuthListener = listener;
        mSsoHandler.authorize(sinaAuthListener);
    }

    @Override
    public void onActivityResultData(int requestCode, int resultCode, Intent data) {
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    private WbAuthListener sinaAuthListener = new WbAuthListener() {
        @Override
        public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
            mAccessToken = oauth2AccessToken;
            if (mAccessToken.isSessionValid()) {
                AccessTokenKeeper.writeAccessToken(mActivity, mAccessToken);
                getAccessToken(mAccessToken.getToken());
            }
        }

        @Override
        public void cancel() {
            mAuthListener.onCancel();
        }

        @Override
        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
            mAuthListener.onError(wbConnectErrorMessage.getErrorMessage());
        }
    };


    private void getAccessToken(String access_token) {
        StringBuffer loginUrl = new StringBuffer();
        loginUrl.append("https://api.weibo.com/2/users/show.json").append("?access_token=")
                .append(access_token).append("&uid=").append(mAccessToken.getUid());
        HttpUtils.get(loginUrl.toString(), resultCallback);
    }

    HttpUtils.ResultCallback<String> resultCallback = new HttpUtils.ResultCallback<String>() {
        @Override
        public void onSuccess(String response) {
            String id = null;
            String screenName = null;
            String gender = null;
            String avatarLarge = null;
            try {
                JSONObject jsonObject = new JSONObject(response);
                id = jsonObject.getString("id");
                screenName = jsonObject.getString("screen_name");
                gender = jsonObject.getString("gender");
                avatarLarge = jsonObject.getString("avatar_large");

                String sex = gender.equals("m") ? "男" : "女";
                mAuthListener.onComplete(new PlatFormInfo(id, screenName, sex, avatarLarge));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Exception e) {
            mAuthListener.onError(e.getMessage());
        }
    };
}
