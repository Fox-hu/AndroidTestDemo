package com.example.algorithms.efficient.guarded_suspension;

/**
 * Created by fox.hu on 2018/6/25.
 */

public class GuardedSuspensionTest {

    public static void test() {
        RequestQueue requestQueue = new RequestQueue();
        for (int i = 0; i < 10; i++) {
            new ServerThread("ServerThread " + i, requestQueue).start();
        }

        for (int i = 0; i < 10; i++) {
            new ClientThread("ClientThread " + i, requestQueue).start();
        }
    }
}
