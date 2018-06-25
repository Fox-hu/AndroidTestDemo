package com.example.algorithms.efficient.future;

import com.example.algorithms.efficient.Data;

/**
 * Created by fox.hu on 2018/6/25.
 */

public class Client {
    public Data request(final String requestStr){
        final FutureData futureData = new FutureData();

        new Thread(){
            @Override
            public void run() {
                RealData realData = new RealData(requestStr);
                futureData.setResult(realData);
            }
        }.start();

        return futureData;
    }
}
