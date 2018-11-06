package com.example.algorithms.multiple_thread.guardedSuspension;

/**
 * Created by fox.hu on 2018/11/6.
 */

public interface Predicate {

    /**
     * 执行目标动作的开关，是目标动作的保护条件是否成立
     * @return true成立 false不成立
     */
    boolean evaluate();
}
