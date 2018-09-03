package com.umeng.soexample.share;

import android.app.Activity;
import android.os.Bundle;

import com.umeng.soexample.PlatForm;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by fox.hu on 2018/9/3.
 */

public class ShareManager {
    private static final String TAG = ShareManager.class.getSimpleName();
    private Map<PlatForm, IShare> mShareMap = new HashMap<>();
    private Map<PlatForm, ShareListener> mShareListenerMap = new HashMap<>();
    private PlatForm mCurrentPlatForm;

    private ShareManager() {}

    public static ShareManager getDefault() {
        return Holder.INSTANCE;
    }

    public void shareTo(PlatForm platForm, ShareType type, Activity activity, Bundle bundle,
            ShareListener listener) {
        IShare iShare = mShareMap.get(platForm);
        if (iShare == null) {
            iShare = ShareFactory.generate(activity, platForm);
            mShareMap.put(platForm, iShare);
        }
        mCurrentPlatForm = platForm;
        mShareListenerMap.put(platForm, listener);
        listener.onStart(platForm);
        iShare.shareTo(type, activity, bundle, listener);
    }

    private static class Holder {
        static final ShareManager INSTANCE = new ShareManager();
    }
}
