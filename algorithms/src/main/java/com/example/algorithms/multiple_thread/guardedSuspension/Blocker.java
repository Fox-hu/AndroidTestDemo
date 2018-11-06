package com.example.algorithms.multiple_thread.guardedSuspension;

import java.util.concurrent.Callable;

/**
 * Created by fox.hu on 2018/11/6.
 */

public interface Blocker {

    /**
     * 在保护条件成立下执行目标动作，否则就阻塞该线程
     * @param guardedAction 带保护条件的目标动作
     * @param <V> 目标动作的返回值
     * @return
     * @throws Exception
     */
    <V> V callWithGuard(GuardedAction<V> guardedAction) throws Exception;

    /**
     * 执行此操作后 决定是否唤醒blocker所暂挂的其中一个线程
     * @param stateOperation 当其call方法为true时 才会唤醒被暂挂的线程
     * @throws Exception
     */
    void signalAfter(Callable<Boolean> stateOperation) throws Exception;

    void signal() throws Exception;

    /**
     * 执行此操作后 决定是否唤醒blocker所暂挂的所有线程
     * @param stateOperation 当其call方法为true时 才会唤醒被暂挂的所有线程
     * @throws Exception
     */
    void broadcastAfter(Callable<Boolean> stateOperation) throws Exception;
}
