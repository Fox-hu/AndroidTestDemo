package com.example.algorithms.multiple_thread.twoPhase_termination;

/**
 * Created by fox.hu on 2018/11/12.
 */

public abstract class AbstractTerminableThread extends Thread implements Terminable {
    protected final TerminationToken token = new TerminationToken();

    public AbstractTerminableThread() {
        token.register(this);
    }

    @Override
    public void run() {
        Exception ex = null;
        try {
            for (; ; ) {
                if (token.isToShutdown()) {
                    break;
                }
                doRun();
            }
        } catch (Exception e) {
            ex = e;
            if (e instanceof InterruptedException) {
                //线程终止
            }
        } finally {
            try {
                doCleanup(ex);
            } finally {
                //此线程终止，使其他线程依次终止
                token.notifyThreadTermination(this);
            }
        }
    }

    protected abstract void doCleanup(Exception ex);

    protected abstract void doRun();

    protected abstract void doTerminate();

    @Override
    public void terminate() {
        token.setToShutdown(true);
        try {
            doTerminate();
        } finally {
            super.interrupt();
        }
    }
}
