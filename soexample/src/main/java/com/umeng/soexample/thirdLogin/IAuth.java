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

    void fetchPlatFormInfo(Activity activity, onAuthListener listener);

    void onActivityResultData(int requestCode, int resultCode, Intent data);

}
