package com.example.webviewtest.threadtest;

import android.util.Log;

/**
 * Created by fox.hu on 2018/4/27.
 */

public class Child extends Thread {
    private static final String TAG = Child.class.getSimpleName();
    @Override
    public void run() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG,"Child run");
    }
}
