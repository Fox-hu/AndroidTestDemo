/*
授权声明：
本源码系《Java多线程编程实战指南（设计模式篇）》一书（ISBN：978-7-121-27006-2，以下称之为“原书”）的配套源码，
欲了解本代码的更多细节，请参考原书。
本代码仅为原书的配套说明之用，并不附带任何承诺（如质量保证和收益）。
以任何形式将本代码之部分或者全部用于营利性用途需经版权人书面同意。
将本代码之部分或者全部用于非营利性用途需要在代码中保留本声明。
任何对本代码的修改需在代码中以注释的形式注明修改人、修改时间以及修改内容。
本代码可以从以下网址下载：
https://github.com/Viscent/javamtp
http://www.broadview.com.cn/27006
*/

package com.example.algorithms.multiple_thread.producer_customer;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.algorithms.multiple_thread.two_phase_termination.AbstractTerminableThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class PCTerminatableDemo {
    private static final String TAG = PCTerminatableDemo.class.getSimpleName();
    private final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);

    private final Producer producer = new Producer();
    private final Customer customer = new Customer();

    private class Producer extends AbstractTerminableThread {
        private int anInt = 0;

        @Override
        protected void doRun() throws Exception {
            try {
                Thread.sleep(100);
            } finally {
                queue.put(String.valueOf(anInt++));
                Log.e(TAG, "Product = " + anInt);
                //注意 此处是消费者的token！！！
                customer.token.reservations.incrementAndGet();
            }
        }
    }

    private class Customer extends AbstractTerminableThread {

        @Override
        protected void doRun() throws Exception {
            try {
                Thread.sleep(500);
            } finally {
                String take = queue.take();
                Log.e(TAG, "Customer = " + take);
                token.reservations.decrementAndGet();
            }
        }
    }

    void init() {
        producer.start();
        customer.start();
    }

    void shutdown() {
        producer.terminate(true);
        customer.terminate();
    }

    public static void test() {
        final PCTerminatableDemo ss = new PCTerminatableDemo();
        ss.init();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                ss.shutdown();
            }
        }, 2000);
    }
}
