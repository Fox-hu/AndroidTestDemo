package com.example.algorithms.efficient.future;

import com.example.algorithms.efficient.Data;

/**
 * Created by fox.hu on 2018/6/25.
 */

public class RealData implements Data {
    protected final String resultStr;

    public RealData(String requestStr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            stringBuffer.append(i);
            try {
                Thread.sleep(100);
                //模拟realData生成的过程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.resultStr = stringBuffer.toString();
    }

    @Override
    public String getResult() {
        return resultStr;
    }
}
