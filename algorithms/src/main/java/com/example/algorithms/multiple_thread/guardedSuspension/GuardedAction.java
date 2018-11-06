package com.example.algorithms.multiple_thread.guardedSuspension;

import java.util.concurrent.Callable;

/**
 * Created by fox.hu on 2018/11/6.
 */

public abstract class GuardedAction<V> implements Callable<V> {
    protected final Predicate guard;

    public GuardedAction(Predicate guard) {
        this.guard = guard;
    }
}



