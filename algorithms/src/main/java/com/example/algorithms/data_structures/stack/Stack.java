package com.example.algorithms.data_structures.stack;

/**
 * Created by fox.hu on 2018/11/27.
 */

public interface Stack<T> {
    void push(T item);

    T pop();

    boolean isEmpty();

    int size();
}
