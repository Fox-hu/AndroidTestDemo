package com.example.algorithms.multiple_thread.twoPhase_termination;

import java.lang.ref.WeakReference;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by fox.hu on 2018/11/12.
 */

public class TerminationToken {

    volatile boolean toShutdown = false;
    public final AtomicInteger reservations = new AtomicInteger(0);

    private final Queue<WeakReference<Terminable>> coordinatedThreads;

    public TerminationToken() {
        coordinatedThreads = new ConcurrentLinkedQueue<WeakReference<Terminable>>();
    }

    public boolean isToShutdown() {
        return toShutdown;
    }

    public void setToShutdown(boolean toShutdown) {
        this.toShutdown = toShutdown;
    }

    public void register(Terminable thread) {
        coordinatedThreads.add(new WeakReference<>(thread));
    }

    public void notifyThreadTermination(Terminable thread) {
        WeakReference<Terminable> wrThread;
        Terminable otherThread;
        while (null != (wrThread = coordinatedThreads.poll())) {
            otherThread = wrThread.get();
            if (otherThread != null && otherThread != thread) {
                otherThread.terminate();
            }
        }
    }
}
