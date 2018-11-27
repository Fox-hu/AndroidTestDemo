package com.example.algorithms.data_structures.queue;

/**
 * Created by fox.hu on 2018/11/27.
 */

public interface Queue<T> {
    boolean isEmpty();

    int size();

    void enQueue(T item);

    T deQueue();
}
