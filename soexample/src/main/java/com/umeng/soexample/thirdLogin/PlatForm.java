package com.umeng.soexample.thirdLogin;

import com.umeng.soexample.PlatformName;
import com.umeng.soexample.thirdLogin.sina.Constants;

/**
 *
 * @author fox.hu
 * @date 2018/8/16
 */

public enum PlatForm {
    QQ(PlatformName.QQ, "100424468", "", ""),
    WEIXIN(PlatformName.WEIXIN, "wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0", ""),
    ALIPAY(PlatformName.ALIPAY, "", "", ""),
    SINA(PlatformName.SINA, "2200713412", "http://sns.whalecloud.com", Constants.SCOPE);

    private String mShowWord;
    private String mAppId;
    private String mAppKey;
    private String mScope;

    PlatForm(String mShowWord, String mAppId, String mAppKey, String mScope) {
        this.mScope = mScope;
        this.mShowWord = mShowWord;
        this.mAppId = mAppId;
        this.mAppKey = mAppKey;
    }

    public String getScope() {
        return mScope;
    }

    public String getShowWord() {
        return mShowWord;
    }

    public String getAppId() {
        return mAppId;
    }

    public String getAppKey() {
        return mAppKey;
    }

}
