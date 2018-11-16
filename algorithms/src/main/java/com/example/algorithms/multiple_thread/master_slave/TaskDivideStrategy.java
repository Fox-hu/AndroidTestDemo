package com.example.algorithms.multiple_thread.master_slave;

/**
 * 对原始任务进行分解算法策略的抽象
 * @author fox.hu
 * @date 2018/11/16
 */

public interface TaskDivideStrategy<T> {
    /**
     * @return T 下一个子任务 必须有返回null的时候
     */
    T nextChunk();
}
