package com.example.webviewtest.threadtest;

import android.util.Log;

/**
 * Created by fox.hu on 2018/4/27.
 */

public class Parent extends Thread {
    private static final String TAG = Parent.class.getSimpleName();

    @Override
    public void run() {
        Log.d(TAG,"Parent run");

        Child child =new Child();
        child.start();
        try {
            child.join();
            Log.d(TAG,"child join");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
