package com.example.algorithms.efficient.produce_custom;

import android.util.Log;

import java.util.List;

/**
 * Created by fox.hu on 2018/8/2.
 */

public class Custom extends Thread {
    private static final String TAG = Custom.class.getSimpleName();

    List<Integer> integerList;
    int mMaxSize;

    Custom(String name, List<Integer> list, int maxSize) {
        super(name);
        integerList = list;
        mMaxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (integerList) {
                while (integerList.isEmpty()) {
                    try {
                        Log.e(TAG, "Custom thread name = " + getName() + " integerList empty");
                        integerList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                integerList.remove(0);
                Log.e(TAG,
                        "Custom thread name = " + getName() + " integerList remove, list size = " +
                        integerList.size());
                integerList.notifyAll();

            }
        }
    }
}
