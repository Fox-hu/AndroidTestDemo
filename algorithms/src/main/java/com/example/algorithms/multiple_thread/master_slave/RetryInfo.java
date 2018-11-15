package com.example.algorithms.multiple_thread.master_slave;

import java.util.concurrent.Callable;

/**
 * Created by fox.hu on 2018/11/15.
 */

public class RetryInfo<T, V> {
    public final T subTask;
    public final Callable<V> redoCommand;

    public RetryInfo(T subTask, Callable<V> redoCommand) {
        this.subTask = subTask;
        this.redoCommand = redoCommand;
    }
}
