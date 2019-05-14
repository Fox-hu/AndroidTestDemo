package com.example.algorithms.java8.completableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTest {
    //在一秒内返回一个异步任务
    public static void  test(){
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final Future<Double> future = executorService.submit(() -> {
            //异步任务
            return 2.0;
        });
        try {
            future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
