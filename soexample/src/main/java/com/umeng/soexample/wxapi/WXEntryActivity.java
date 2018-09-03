package com.umeng.soexample.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.umeng.soexample.authLogin.AuthManager;
import com.umeng.soexample.authLogin.IAuth;
import com.umeng.soexample.PlatForm;
import com.umeng.soexample.authLogin.PlatFormInfo;
import com.umeng.soexample.authLogin.AuthListener;
import com.umeng.soexample.authLogin.HttpUtils;
import com.umeng.soexample.authLogin.weixin.WeiXinAuth;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by fox.hu on 2018/8/17.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = WXEntryActivity.class.getSimpleName();
    private AuthListener listener;
    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IAuth iAuth = AuthManager.getDefault().getIAuth(PlatForm.WEIXIN);
        listener = AuthManager.getDefault().getIAuthListener(PlatForm.WEIXIN);
        api = ((WeiXinAuth) iAuth).getApi();
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        //登录回调
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                Log.e(TAG, "onResp ERR_OK");
                String code = ((SendAuth.Resp) baseResp).code;
                Log.e(TAG, "onResp ERR_OK code = " + code);
                //获取用户信息
                getAccessToken(code);
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                Log.e(TAG, "onResp 用户拒绝授权");
                listener.onError("用户拒绝授权");
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
                Log.e(TAG, "onResp 用户取消");
                listener.onCancel();
                break;
            default:
                listener.onError("授权失败");
                break;
        }
        finish();
    }


    private void getAccessToken(String code) {
        StringBuffer loginUrl = new StringBuffer();
        loginUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token").append("?appid=")
                .append(PlatForm.WEIXIN.getAppId()).append("&secret=").append(
                PlatForm.WEIXIN.getAppKey()).append("&code=").append(code).append(
                "&grant_type=authorization_code");
        HttpUtils.get(loginUrl.toString(), resultCallback);
    }

    HttpUtils.ResultCallback<String> resultCallback = new HttpUtils.ResultCallback<String>() {
        @Override
        public void onSuccess(String response) {
            String access = null;
            String openId = null;
            try {
                JSONObject jsonObject = new JSONObject(response);
                access = jsonObject.getString("access_token");
                openId = jsonObject.getString("openid");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //获取个人信息
            String getUserInfo =
                    "https://api.weixin.qq.com/sns/userinfo?access_token=" + access + "&openid=" +
                    openId;
            HttpUtils.get(getUserInfo, reCallback);
        }

        @Override
        public void onFailure(Exception e) {
            Toast.makeText(WXEntryActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
            finish();
            listener.onError("授权失败");
        }
    };

    HttpUtils.ResultCallback<String> reCallback = new HttpUtils.ResultCallback<String>() {
        @Override
        public void onSuccess(String responses) {

            String nickName = null;
            String sex = null;
            String city = null;
            String province = null;
            String country = null;
            String headimgurl = null;
            String openId = null;
            String unionid = null;
            try {
                JSONObject jsonObject = new JSONObject(responses);
                openId = jsonObject.getString("openid");
                nickName = jsonObject.getString("nickname");
                sex = jsonObject.getString("sex");
                city = jsonObject.getString("city");
                province = jsonObject.getString("province");
                country = jsonObject.getString("country");
                headimgurl = jsonObject.getString("headimgurl");
                unionid = jsonObject.getString("unionid");

                String gender = sex.equals("1") ? "男" : "女";
                listener.onComplete(new PlatFormInfo(openId, nickName, gender, headimgurl));
                finish();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Exception e) {
            Toast.makeText(WXEntryActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
            finish();
            listener.onError("授权失败");
        }
    };
}
