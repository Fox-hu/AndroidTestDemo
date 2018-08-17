package com.umeng.soexample.thirdLogin;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by fox.hu on 2018/8/16.
 */

public interface ILogin {
    void getPlatFormInfo(Activity activity, LoginParam loginParam, onAuthListener listener);

    void onActivityResultData(int requestCode, int resultCode, Intent data);
}
