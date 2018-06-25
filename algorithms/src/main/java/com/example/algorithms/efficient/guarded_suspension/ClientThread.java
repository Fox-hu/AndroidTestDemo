package com.example.algorithms.efficient.guarded_suspension;

import android.util.Log;

/**
 * Created by fox.hu on 2018/6/25.
 */

public class ClientThread extends Thread {
    private static final String TAG = ClientThread.class.getSimpleName();
    private RequestQueue mRequestQueue;

    public ClientThread(String name, RequestQueue requestQueue) {
        super(name);
        this.mRequestQueue = requestQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Request request = new Request(
                    "RequestId = " + i + "thread name = " + Thread.currentThread().getName());
            Log.i(TAG, Thread.currentThread().getName() + " request = " + request);
            mRequestQueue.addQueue(request);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.i(TAG, Thread.currentThread().getName() + "request end");
    }
}
