package com.umeng.soexample.share;

/**
 * Created by fox.hu on 2018/8/31.
 */

public interface ShareListener {
    void onComplete(Object response);

    void onError(String errorMsg);

    void onCancel();
}
