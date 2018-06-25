package com.example.algorithms.efficient.future;

import android.util.Log;

import com.example.algorithms.efficient.Data;

/**
 *
 * future模式的关键在于 在获取request的response时 返回一个空的result容器
 * 在返回的同时开启线程 获取正真的response
 * 同样 getResult时，需等待获取过程真正完成后 才能获取结果
 * @author fox.hu
 * @date 2018/6/25
 */

public class FutureTest {
    private static final String TAG = FutureTest.class.getSimpleName();

    public static void test() {
        Client client = new Client();
        Data data = client.request("name");

        Log.i(TAG, "data result = " + data.getResult());
    }
}
