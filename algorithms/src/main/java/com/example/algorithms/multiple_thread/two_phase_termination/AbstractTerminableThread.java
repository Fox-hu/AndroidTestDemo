package com.example.algorithms.multiple_thread.two_phase_termination;

import android.util.Log;

/**
 * Created by fox.hu on 2018/11/15.
 */

public abstract class AbstractTerminableThread extends Thread implements Terminable {
    private static final String TAG = AbstractTerminableThread.class.getSimpleName();
    public final TerminateToken token;

    public AbstractTerminableThread() {
        token = new TerminateToken();
        token.register(this);
    }

    @Override
    public void run() {
        Exception ex = null;
        try {
            for (; ; ) {
                if (token.isToShutdown() && token.reservations.get() <= 0) {
                    break;
                }
                doRun();
            }
        } catch (Exception e) {
            ex = e;
            if (e instanceof InterruptedException) {
                //此时不用置位 因为已经发生异常退出线程了
                Log.d(TAG, "thread interrupt");
            }
        } finally {
            try {
                doCleanup(ex);
            } finally {
                token.notifyThreadTermination(this);
            }
        }
    }

    protected abstract void doRun() throws Exception;

    /**
     * 留给子类实现。用于实现线程停止后的一些清理动作。
     *
     * @param cause
     */
    protected void doCleanup(Exception cause) {
        // 什么也不做
    }

    /**
     * 留给子类实现。用于执行线程停止所需的操作。
     */
    protected void doTerminate() {
        // 什么也不做
    }

    @Override
    public void terminate() {
        token.setToShutdown(true);
        try {
            doTerminate();
        } finally {
            if (token.reservations.get() <= 0) {
                super.interrupt();
            }
        }
    }

    public void terminate(boolean waitUtilThreadTerminated) {
        //进行终止线程的操作
        terminate();
        //当需要当前线程终止完成之后再进行下一步动作时，使用join，等待线程终止
        if (waitUtilThreadTerminated) {
            try {
                this.join();
            } catch (InterruptedException e) {
                //当join出现异常时，会把interrupt标志清零，此时需要把此线程的interrupt标志再置回来
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void interrupt() {
        terminate();
    }
}
