package com.sliver.fox.nexuslib;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by fox on 2018/4/29.
 */

public class ThreadPoolHelper {
    private static final String TAG = ThreadPoolHelper.class.getSimpleName();

    private static ExecutorService sCachedThreadPool = Executors.newCachedThreadPool();

    private static ScheduledExecutorService sScheduledThreadPool = Executors.newScheduledThreadPool(50);

    public static ExecutorService getCachedThreadPool() {
        return sCachedThreadPool;
    }

    public static ScheduledExecutorService getScheduledThreadPool() {
        return sScheduledThreadPool;
    }
}
