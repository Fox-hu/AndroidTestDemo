package com.example.algorithms.multiple_thread.twoPhase_termination;

import android.os.Handler;
import android.util.Log;

import com.example.algorithms.multiple_thread.producer_customer.BlockQueueChannel;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by fox.hu on 2018/11/9.
 */

public class ProducerCustomerTerminate {
    private static final String TAG = ProducerCustomerTerminate.class.getSimpleName();
    private BlockQueueChannel<Integer> channel = new BlockQueueChannel<>(
            new ArrayBlockingQueue<Integer>(100));
    private ExecutorService producerExecutor = Executors.newCachedThreadPool();
    private ExecutorService customerExecutor = Executors.newCachedThreadPool();

    public void test() {
        final Producer producer = new Producer();
        final Customer customer = new Customer();
        producer.start();
        customer.start();
//        producerExecutor.execute(producer);
//        customerExecutor.execute(customer);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                producer.terminate();
            }
        }, 10000);

    }

    private class Customer extends AbstractTerminableThread {

        @Override
        protected void doCleanup(Exception ex) {

        }

        @Override
        protected void doRun() {
            try {
                Thread.sleep(1000);
                channel.take();
                Log.e(TAG, "current thread = " + Thread.currentThread() +
                           "Customer take, channel size = " + channel.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
                Log.e(TAG, "InterruptedException = " + e.getMessage());
            }
        }

        @Override
        protected void doTerminate() {

        }
    }

    private class Producer extends AbstractTerminableThread {


        @Override
        protected void doCleanup(Exception ex) {

        }

        @Override
        protected void doRun() {
            try {
                Thread.sleep(500);
                channel.put(1);
                Log.e(TAG, "current thread = " + Thread.currentThread() +
                           "Producer put, channel size = " + channel.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
                Log.e(TAG, "InterruptedException = " + e.getMessage());
            }
        }

        @Override
        protected void doTerminate() {

        }
    }
}
