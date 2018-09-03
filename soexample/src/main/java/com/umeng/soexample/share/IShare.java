package com.umeng.soexample.share;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by fox.hu on 2018/8/31.
 */

public interface IShare {

    void shareTo(ShareType type, Activity activity, Bundle bundle, ShareListener listener);

    /**
     * 在activity 的onActivityResult使用 QQ和新浪需要
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    void onActivityResultData(int requestCode, int resultCode, Intent data);
}
