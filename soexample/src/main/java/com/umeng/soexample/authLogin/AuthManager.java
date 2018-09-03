package com.umeng.soexample.authLogin;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.umeng.soexample.PlatForm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fox.hu
 * @date 2018/8/16
 */

public final class AuthManager {
    private static final String TAG = AuthManager.class.getSimpleName();
    private Map<PlatForm, IAuth> mAuthLoginMap = new HashMap<>();
    private Map<PlatForm, AuthListener> mAuthListenerMap = new HashMap<>();
    private PlatForm mCurrentPlatForm;

    private AuthManager() {
    }

    public static AuthManager getDefault() {
        return Holder.INSTANCE;
    }

    public void fetchPlatFormInfo(@NonNull PlatForm platForm, @NonNull Activity activity, @NonNull
            AuthListener listener) {
        IAuth iAuth = mAuthLoginMap.get(platForm);
        if (iAuth == null) {
            iAuth = AuthFactory.generate(activity, platForm);
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

    public AuthListener getIAuthListener(PlatForm platForm) {
        AuthListener listener = mAuthListenerMap.get(platForm);
        if (listener != null) {
            return listener;
        } else {
            throw new NullPointerException(
                    "AuthListener null, " + platForm.getShowWord() + "not register");
        }
    }

    private static class Holder {
        static final AuthManager INSTANCE = new AuthManager();
    }
}