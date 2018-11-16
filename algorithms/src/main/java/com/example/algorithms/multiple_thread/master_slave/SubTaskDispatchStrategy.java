package com.example.algorithms.multiple_thread.master_slave;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * 派发子任务的策略抽象
 *
 * @author fox.hu
 * @date 2018/11/16
 */

public interface SubTaskDispatchStrategy<T, V> {
    /**
     * @param slaves             可接受子任务的一组slave实例
     * @param taskDivideStrategy 原始任务分解策略
     * @return 子任务的结果集合
     * @throws InterruptedException
     */
    Iterator<Future<V>> dispatch(Set<? extends Slave<T, V>> slaves,
            TaskDivideStrategy<T> taskDivideStrategy) throws InterruptedException;
}
