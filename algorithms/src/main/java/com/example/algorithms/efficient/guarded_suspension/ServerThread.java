package com.example.algorithms.efficient.guarded_suspension;

import android.util.Log;

import com.example.algorithms.efficient.future.FutureData;
import com.example.algorithms.efficient.future.RealData;

/**
 * Created by fox.hu on 2018/6/25.
 */

public class ServerThread extends Thread {
    private static final String TAG = ServerThread.class.getSimpleName();
    private RequestQueue requestQueue;

    public ServerThread(String name, RequestQueue requestQueue) {
        super(name);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        while (true) {
            Request request = requestQueue.getRequest();
            FutureData futureData = (FutureData) request.getResponse();
            RealData realData = new RealData(request.getName());
            futureData.setResult(realData);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i(TAG, Thread.currentThread().getName() + "handles " + request);
        }
    }
}
