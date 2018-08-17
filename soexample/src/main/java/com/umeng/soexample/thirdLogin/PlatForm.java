package com.umeng.soexample.thirdLogin;

import com.umeng.soexample.PlatformName;

/**
 * Created by fox.hu on 2018/8/16.
 */

public enum PlatForm {
    QQ("QQ", PlatformName.QQ, "100424468", ""), WEIXIN("WEIXIN", PlatformName.WEIXIN, "",
            ""), ALIPAY("ALIPAY", PlatformName.ALIPAY, "", ""), SINA("SINA", PlatformName.SINA, "",
            "");

    private String mKeyword;
    private String mShowWord;
    private String mAppId;
    private String mAppKey;

    PlatForm(String mKeyword, String mShowWord, String mAppId, String mAppKey) {
        this.mKeyword = mKeyword;
        this.mShowWord = mShowWord;
        this.mAppId = mAppId;
        this.mAppKey = mAppKey;
    }

    public String getKeyword() {
        return mKeyword;
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
