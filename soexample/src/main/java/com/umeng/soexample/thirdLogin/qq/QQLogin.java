package com.umeng.soexample.thirdLogin.qq;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.umeng.soexample.thirdLogin.ILogin;
import com.umeng.soexample.thirdLogin.LoginParam;
import com.umeng.soexample.thirdLogin.PlatForm;
import com.umeng.soexample.thirdLogin.PlatFormInfo;
import com.umeng.soexample.thirdLogin.onAuthListener;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by fox.hu on 2018/8/17.
 */

public class QQLogin implements ILogin {
    private static final String TAG = QQLogin.class.getSimpleName();

    private Tencent mTencent;
    private Activity mActivity;
    private onAuthListener authListener;
    private UserInfo mInfo;

    public QQLogin(@NonNull Activity activity) {
        mActivity = activity;
        mTencent = Tencent.createInstance(PlatForm.QQ.getAppId(), activity.getApplicationContext());
    }

    @Override
    public void getPlatFormInfo(Activity activity, LoginParam loginParam,
            final onAuthListener listener) {
        authListener = listener;
        mTencent.login(activity, loginParam.getScope(), loginListener);
    }

    @Override
    public void onActivityResultData(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN || requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
        }
    }

    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
            initOpenidAndToken(values);
            updateUserInfo();
        }
    };

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            if (null == response) {
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                return;
            }
            doComplete((JSONObject) response);
        }

        protected void doComplete(JSONObject values) {

        }

        @Override
        public void onError(UiError e) {
            Log.e(TAG, "onLogin onError E = " + e.errorMessage);
        }

        @Override
        public void onCancel() {
            Log.e(TAG, "onLogin onCancel");
        }
    }

    private void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires) && !TextUtils.isEmpty(
                    openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch (Exception e) {
        }
    }

    private void updateUserInfo() {
        if (mTencent != null && mTencent.isSessionValid()) {
            IUiListener listener = new IUiListener() {

                @Override
                public void onError(UiError e) {
                    Log.e(TAG, "updateUserInfo onError E = " + e.errorMessage);
                    authListener.onError(e.errorMessage);
                }

                @Override
                public void onComplete(final Object response) {
                    Log.e(TAG, "updateUserInfo response = " + response.toString());
                    authListener.onComplete(response2PlatFormInfo((JSONObject) response));
                }

                @Override
                public void onCancel() {
                    authListener.onCancel();
                    Log.e(TAG, "updateUserInfo onCancel");
                }
            };
            mInfo = new UserInfo(mActivity, mTencent.getQQToken());
            mInfo.getUserInfo(listener);
        }
    }

    private PlatFormInfo response2PlatFormInfo(JSONObject response) {
        try {
            String nickName = response.getString(Constants.PARAM_NICK_NAME);
            String gender = response.getString(Constants.PARAM_GENDER);
            String iconUrl = response.getString(Constants.PARAM_ICONURL);
            String openId = mTencent.getOpenId();

            return new PlatFormInfo(openId, nickName, gender, iconUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new PlatFormInfo("", "", "", "");
    }
}
