package com.example.algorithms.efficient.future;

import com.example.algorithms.efficient.Data;

/**
 * Created by fox.hu on 2018/6/25.
 */

public class FutureData implements Data {
    private RealData realData;
    private boolean isReady = false;


    public void setResult(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    @Override
    public String getResult() {
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return realData.getResult();
    }
}
