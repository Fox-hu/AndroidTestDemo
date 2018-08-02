package com.example.algorithms.efficient.produce_custom;

import android.util.Log;

import java.util.List;

/**
 * Created by fox.hu on 2018/8/2.
 */

public class Produce extends Thread {
    private static final String TAG = Produce.class.getSimpleName();
    List<Integer> integerList;
    int mMaxSize;

    Produce(String name, List<Integer> list, int maxSize) {
        super(name);
        integerList = list;
        mMaxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (integerList) {
                if (integerList.size() == mMaxSize) {
                    try {
                        Log.e(TAG, "Produce thread name = " + getName() + " integerList full");
                        integerList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                integerList.add(1);
                Log.e(TAG, "Produce thread name = " + getName() +
                           " integerList add, integerList size = " + integerList.size());
                integerList.notifyAll();

            }

        }
    }
}
