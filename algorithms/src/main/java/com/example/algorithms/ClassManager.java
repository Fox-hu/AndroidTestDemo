package com.example.algorithms;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/16.
 */

public class ClassManager {
    private static final String TAG = ClassManager.class.getCanonicalName();

    private ClassManager() {

    }

    public static ClassManager get() {
        return SingletonHolder.sInstance;
    }

    private Map<String, Class<?>> singletonHolder = new HashMap<>();

    public void registerSingleton(@NonNull String tag, @NonNull Class<?> clazz) {
        if (!singletonHolder.containsKey(tag)) {
            singletonHolder.put(tag, clazz);
            Log.d(TAG, "TAG = " + TAG + " class = " + clazz.getSimpleName());
        }
    }

    public Class<?> getSingleton(String tag) {
        return singletonHolder.get(tag);
    }

    private static class SingletonHolder {
        private static ClassManager sInstance = new ClassManager();
    }
}
