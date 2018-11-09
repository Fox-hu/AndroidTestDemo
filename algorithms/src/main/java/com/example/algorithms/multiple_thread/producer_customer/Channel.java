package com.example.algorithms.multiple_thread.producer_customer;

/**
 * Created by fox.hu on 2018/11/9.
 */

public interface Channel<P> {
    P take() throws InterruptedException ;

    void put(P product) throws InterruptedException ;

    int size()throws InterruptedException ;
}
