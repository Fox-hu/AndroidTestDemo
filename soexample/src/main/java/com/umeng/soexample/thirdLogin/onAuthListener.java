package com.umeng.soexample.thirdLogin;

/**
 * 第三方登录的通用回调
 * @author fox.hu
 * @date 2018/8/16
 */

public interface onAuthListener {

    /**
     * @param platform
     */
    void onStart(PlatForm platform);

    /**
     * @param platformInfo
     */
    void onComplete(PlatFormInfo platformInfo);

    /**
     * @param errorMsg
     */
    void onError(String errorMsg);

    /**
     *
     */
    void onCancel();
}
