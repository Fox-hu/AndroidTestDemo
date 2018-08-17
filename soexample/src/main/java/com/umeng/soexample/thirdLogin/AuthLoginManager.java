package com.umeng.soexample.thirdLogin;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fox.hu
 * @date 2018/8/16
 */

public final class AuthLoginManager {
    private static final String TAG = AuthLoginManager.class.getSimpleName();
    private Map<PlatForm, ILogin> mAuthLoginMap = new HashMap<>();
    private PlatForm mCurrentPlatForm;

    private AuthLoginManager() {
    }

    public static AuthLoginManager getDefault() {
        return Holder.INSTANCE;
    }

    public void authLogin(@NonNull PlatForm platForm, @NonNull Activity activity,
            LoginParam loginParam, @NonNull onAuthListener listener) {
        ILogin iLogin = mAuthLoginMap.get(platForm);
        if (iLogin == null) {
            iLogin = AuthLoginFactory.generate(activity, platForm);
            mAuthLoginMap.put(platForm, iLogin);
        }
        mCurrentPlatForm = platForm;
        listener.onStart(mCurrentPlatForm);
        iLogin.getPlatFormInfo(activity, loginParam, listener);
    }

    public void onActivityResultData(int requestCode, int resultCode, Intent data) {
        if (mCurrentPlatForm != null) {
            ILogin iLogin = mAuthLoginMap.get(mCurrentPlatForm);
            if (iLogin != null) {
                iLogin.onActivityResultData(requestCode, resultCode, data);
            }
        }
    }

    private static class Holder {
        static final AuthLoginManager INSTANCE = new AuthLoginManager();
    }
}
