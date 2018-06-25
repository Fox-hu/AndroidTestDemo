package com.example.algorithms.efficient.work_master;

import android.util.Log;

import java.util.Map;
import java.util.Queue;

/**
 * Created by fox.hu on 2018/6/20.
 */

public class Worker implements Runnable {
    private static final String TAG = Worker.class.getSimpleName();

    Queue<Object> mWorkQueue;
    Map<String, Object> mResultMap;

    public void setResultMap(Map<String, Object> mResultMap) {
        this.mResultMap = mResultMap;
    }

    public void setWorkQueue(Queue<Object> mWorkQueue) {
        this.mWorkQueue = mWorkQueue;
    }

    @Override
    public void run() {
        while (true) {
            Object input = mWorkQueue.poll();
            if (null == input) {
                break;
            }
            Object output = handle(input);
            Log.i(TAG, "thread = " + Thread.currentThread().getName() + " input = " + input.toString() +
                       " output = " + output.toString());
            mResultMap.put(input.toString(), output);
        }
    }

    Object handle(Object input) {
        return input;
    }
}
