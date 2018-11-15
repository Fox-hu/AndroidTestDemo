package com.example.algorithms.multiple_thread.master_slave;

import java.util.concurrent.Future;

/**
 * Created by fox.hu on 2018/11/15.
 */

public interface Slave<T, V> {

    Future<V> submit(final T task) throws InterruptedException;

    void init();

    void shutdown();
}
