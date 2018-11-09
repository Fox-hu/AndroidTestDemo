package com.example.algorithms.multiple_thread.producer_customer;

import android.os.Handler;
import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by fox.hu on 2018/11/9.
 */

public class ProducerCustomerExecutor {
    private static final String TAG = ProducerCustomerExecutor.class.getSimpleName();
    private BlockQueueChannel<Integer> channel = new BlockQueueChannel<>(
            new ArrayBlockingQueue<Integer>(100));
    private ExecutorService producerExecutor = Executors.newCachedThreadPool();
    private ExecutorService customerExecutor = Executors.newCachedThreadPool();

    public void test() {
        producerExecutor.execute(new Producer());
        customerExecutor.execute(new Customer());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //shutdown只会停止ExecutorService接受新的task 如果在shutdown后继续添加task
                //线程会抛出RejectedExecutionException异常
                //shutdownNow方法的作用是向所有执行中的线程发出interrupted以中止线程的运行
                //线程会抛出InterruptedException异常
                //在这个例子中刚好用于跳出循环
                producerExecutor.shutdownNow();
            }
        },10000);

    }

    private class Customer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    channel.take();
                    Log.e(TAG, "current thread = " + Thread.currentThread() +
                               "Customer take, channel size = " + channel.size());
                } catch (InterruptedException  e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    private class Producer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(500);
                    channel.put(1);
                    Log.e(TAG, "current thread = " + Thread.currentThread() +
                               "Producer put, channel size = " + channel.size());
                } catch (InterruptedException  e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}
