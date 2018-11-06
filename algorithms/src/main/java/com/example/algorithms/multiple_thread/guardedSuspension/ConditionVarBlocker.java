package com.example.algorithms.multiple_thread.guardedSuspension;

import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by fox.hu on 2018/11/6.
 */

public class ConditionVarBlocker implements Blocker {
    private static final String TAG = ConditionVarBlocker.class.getSimpleName();
    private final Lock lock;
    private final Condition condition;

    public ConditionVarBlocker() {
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
    }

    @Override
    public <V> V callWithGuard(GuardedAction<V> guardedAction) throws Exception {
        lock.lockInterruptibly();
        V result;
        try {
            final Predicate guard = guardedAction.guard;
            while (!guard.evaluate()) {
                Log.d(TAG, "waiting...");
                condition.await();
            }
            result = guardedAction.call();
            return result;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void signalAfter(Callable<Boolean> stateOperation) throws Exception {
        lock.lockInterruptibly();
        try {
            if (stateOperation.call()) {
                condition.notify();
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void signal() throws Exception {
        lock.lockInterruptibly();
        try {
            condition.notifyAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void broadcastAfter(Callable<Boolean> stateOperation) throws Exception {
        lock.lockInterruptibly();
        try {
            if (stateOperation.call()) {
                condition.notifyAll();
            }
        } finally {
            lock.unlock();
        }
    }
}
