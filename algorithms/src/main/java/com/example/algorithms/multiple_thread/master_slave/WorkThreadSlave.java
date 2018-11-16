package com.example.algorithms.multiple_thread.master_slave;

import com.example.algorithms.multiple_thread.two_phase_termination.AbstractTerminableThread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 *
 * @author fox.hu
 * @date 2018/11/15
 */

public abstract class WorkThreadSlave<T, V> extends AbstractTerminableThread implements Slave<T, V> {
    public WorkThreadSlave(BlockingQueue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    private final BlockingQueue<Runnable> taskQueue;

    @Override
    public Future<V> submit(final T task) throws InterruptedException {
        FutureTask<V> ft = new FutureTask<>(new Callable<V>() {
            @Override
            public V call() throws Exception {
                V result = null;
                try {
                    result = doProcess(task);
                } catch (Exception e) {
                    throw newSubTaskFailureException(task,e);
                }
                return result;
            }
        });
        taskQueue.put(ft);
        token.reservations.incrementAndGet();
        return ft;
    }

    protected abstract V doProcess(T task);

    @Override
    public void init() {
        start();
    }

    @Override
    public void shutdown() {
        terminate(true);
    }

    @Override
    protected void doRun() throws Exception {
        try {
            //先执行init时,taskqueue中并未数据此时会阻塞
            //执行submit时,队列不为空 doRun会执行
            Runnable task = taskQueue.take();
            task.run();
        } finally {
            token.reservations.decrementAndGet();
        }
    }

    private SubTaskFailureException newSubTaskFailureException(final T subTask, Exception cause) {
        RetryInfo<T, V> retryInfo = new RetryInfo<>(subTask, new Callable<V>() {
            @Override
            public V call() throws Exception {
                V ret;
                ret = doProcess(subTask);
                return ret;
            }
        });
        return new SubTaskFailureException(cause, retryInfo);
    }
}
