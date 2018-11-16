package com.example.algorithms.multiple_thread.master_slave;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * @author fox.hu
 * @date 2018/11/16
 */

public class DefaultDispatchStrategy<T, V> implements SubTaskDispatchStrategy<T, V> {

    @SuppressWarnings("unchecked")
    @Override
    public Iterator<Future<V>> dispatch(Set<? extends Slave<T, V>> slaves,
            TaskDivideStrategy<T> taskDivideStrategy) throws InterruptedException {
        final List<Future<V>> subResults = new LinkedList<>();
        T subTask;

        Object[] arrSlaves = slaves.toArray();
        //因为需要在循环中+1 所以此处初值设为-1;
        int i = -1;

        final int slaveCount = arrSlaves.length;

        Future<V> subTaskResultPromise;

        //返回null则退出循环 subResults列表中的所有线程结果都会被占位
        while (null != (subTask = taskDivideStrategy.nextChunk())) {
            i = (i + 1) % slaveCount;
            subTaskResultPromise = ((Slave) arrSlaves[i]).submit(subTask);
            subResults.add(subTaskResultPromise);
        }
        return subResults.iterator();
    }
}
