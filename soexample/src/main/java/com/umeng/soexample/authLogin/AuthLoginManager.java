package com.umeng.soexample.authLogin;

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
    private Map<PlatForm, IAuth> mAuthLoginMap = new HashMap<>();
    private Map<PlatForm, onAuthListener> mAuthListenerMap = new HashMap<>();
    private PlatForm mCurrentPlatForm;

    private AuthLoginManager() {
    }

    public static AuthLoginManager getDefault() {
        return Holder.INSTANCE;
    }

    public void fetchPlatFormInfo(@NonNull PlatForm platForm, @NonNull Activity activity, @NonNull onAuthListener listener) {
        IAuth iAuth = mAuthLoginMap.get(platForm);
        if (iAuth == null) {
            iAuth = AuthLoginFactory.generate(activity, platForm);
            mAuthLoginMap.put(platForm, iAuth);
        }
        mCurrentPlatForm = platForm;
        mAuthListenerMap.put(platForm, listener);
        listener.onStart(mCurrentPlatForm);
        iAuth.fetchPlatFormInfo(activity, listener);
    }

    public void onActivityResultData(int requestCode, int resultCode, Intent data) {
        if (mCurrentPlatForm != null) {
            IAuth iAuth = mAuthLoginMap.get(mCurrentPlatForm);
            if (iAuth != null) {
                iAuth.onActivityResultData(requestCode, resultCode, data);
            }
        }
    }

    public IAuth getIAuth(PlatForm platForm) {
        IAuth iAuth = mAuthLoginMap.get(platForm);
        if (iAuth != null) {
            return iAuth;
        } else {
            throw new NullPointerException(
                    "IAuth null, " + platForm.getShowWord() + " not register");
        }
    }

    public onAuthListener getIAuthListener(PlatForm platForm) {
        onAuthListener listener = mAuthListenerMap.get(platForm);
        if (listener != null) {
            return listener;
        } else {
            throw new NullPointerException(
                    "onAuthListener null, " + platForm.getShowWord() + "not register");
        }
    }

    private static class Holder {
        static final AuthLoginManager INSTANCE = new AuthLoginManager();
    }
}
