package com.umeng.soexample.thirdLogin;

/**
 * @author fox.hu
 * @date 2018/8/16
 */

public interface onAuthListener {

    void onStart(PlatForm platform);

    void onComplete(PlatFormInfo platformInfo);

    void onError(String errorMsg);

    void onCancel();
}
