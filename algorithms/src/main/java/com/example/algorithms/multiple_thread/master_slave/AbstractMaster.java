package com.example.algorithms.multiple_thread.master_slave;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * Created by fox.hu on 2018/11/16.
 */

public abstract class AbstractMaster<T, V, R> {
    private static final String TAG = AbstractMaster.class.getSimpleName();

    protected volatile Set<? extends Slave<T, V>> slaveSet;

    protected volatile SubTaskDispatchStrategy dispatchStrategy;

    public void init() {
        slaveSet = createSlaves();
        dispatchStrategy = new DefaultDispatchStrategy();
        for (Slave slave : slaveSet) {
            slave.init();
        }
    }

    public R service(Object... params) throws Exception {
        //1.获取子任务分配策略
        final TaskDivideStrategy<T> taskDivideStrategy = newTaskDivideStrategy(params);

        //2.原始任务分解 子任务派发两个功能。subResults 汇集了slave中的结果
        Iterator<Future<V>> subResults = dispatchStrategy.dispatch(slaveSet, taskDivideStrategy);

        //3.关闭slave线程 因为具体的任务分配都在dispatchStrategy中实现
        for (Slave slave : slaveSet) {
            slave.shutdown();
        }

        R result = combineResults(subResults);
        return result;
    }

    protected abstract R combineResults(Iterator<Future<V>> subResults);

    protected abstract TaskDivideStrategy<T> newTaskDivideStrategy(Object... params);

    protected abstract Set<? extends Slave<T, V>> createSlaves();

}
