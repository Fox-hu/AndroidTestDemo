package com.umeng.soexample.share;

import com.umeng.soexample.platform.PlatForm;

/**
 * Created by fox.hu on 2018/8/31.
 */

public interface ShareListener {
    void onStart(PlatForm platForm);

    void onComplete(Object response);

    void onError(String errorMsg);

    void onCancel();
}
