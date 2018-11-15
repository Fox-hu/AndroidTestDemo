package com.example.algorithms.multiple_thread.master_slave;

/**
 * Created by fox.hu on 2018/11/15.
 */

public class SubTaskFailureException extends Exception {
    public final RetryInfo retryInfo;

    public SubTaskFailureException(Exception cause, RetryInfo retryInfo) {
        super(cause);
        this.retryInfo = retryInfo;
    }
}
