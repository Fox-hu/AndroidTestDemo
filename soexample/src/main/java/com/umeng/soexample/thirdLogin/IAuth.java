package com.umeng.soexample.thirdLogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 *
 * @author fox.hu
 * @date 2018/8/16
 */

public interface IAuth{
    /**
     * 第三方登录平台是否已经安装
     * @param context
     * @return 第三方登录平台是否已经安装
     */
    boolean isInstall(Context context);

    /**
     * 获取第三方登录平台的用户信息
     * @param activity
     * @param listener 通用回调方法，不同的平台使用统一的回调
     */
    void fetchPlatFormInfo(Activity activity, onAuthListener listener);

    /**
     * 在activity 的onActivityResult使用 QQ和新浪需要
     * @param requestCode
     * @param resultCode
     * @param data
     */
    void onActivityResultData(int requestCode, int resultCode, Intent data);

}
