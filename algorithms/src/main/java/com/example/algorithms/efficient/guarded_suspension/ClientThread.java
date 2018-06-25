package com.example.algorithms.efficient.guarded_suspension;

import android.util.Log;

import com.example.algorithms.efficient.future.FutureData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fox.hu on 2018/6/25.
 */

public class ClientThread extends Thread {
    private static final String TAG = ClientThread.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private List<Request> requestList = new ArrayList<>();

    public ClientThread(String name, RequestQueue requestQueue) {
        super(name);
        this.mRequestQueue = requestQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Request request = new Request(
                    "RequestId = " + i + " thread name = " + Thread.currentThread().getName());
            Log.i(TAG, Thread.currentThread().getName() + " request = " + request);
            request.setResponse(new FutureData());
            mRequestQueue.addQueue(request);
            requestList.add(request);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.i(TAG, Thread.currentThread().getName() + "request end");

        for (Request request : requestList) {
            Log.i(TAG, "clientThread name is " + Thread.currentThread().getName() +
                       " request result = " + request.getResponse().getResult());
        }
    }
}
