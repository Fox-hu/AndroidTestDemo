package com.component.vender.auth;

import com.component.vender.platform.PlatForm;

/**
 * 第三方登录的通用回调
 * @author fox.hu
 * @date 2018/8/16
 */

public interface AuthListener {

    /**
     * @param platform
     */
    void onStart(PlatForm platform);

    /**
     * @param platformInfo
     */
    void onComplete(com.component.vender.auth.PlatFormInfo platformInfo);

    /**
     * @param errorMsg
     */
    void onError(String errorMsg);

    /**
     *
     */
    void onCancel();
}
