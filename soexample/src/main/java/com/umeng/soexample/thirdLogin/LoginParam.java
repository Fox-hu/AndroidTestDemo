package com.umeng.soexample.thirdLogin;

/**
 * Created by fox.hu on 2018/8/17.
 */

public class LoginParam {
    private static final String TAG = LoginParam.class.getSimpleName();

    public String getScope() {
        return mScope;
    }

    private String mScope;

    public LoginParam(String scope) {
        this.mScope = scope;
    }
}
