package com.example.algorithms.data_structures.stack;

/**
 * Created by fox.hu on 2018/11/27.
 */

public class FixedCapacityStack<T> implements Stack<T> {
    private T[] arrays;
    private int length = 0;

    @SuppressWarnings("unchecked")
    FixedCapacityStack(int cap) {
        arrays = (T[]) new Object[cap];
    }

    @Override
    public void push(T item) {
        arrays[length++] = item;
    }

    @Override
    public T pop() {
        //注意，此处是--length，push时是++length
        return arrays[--length];
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int size() {
        return length;
    }
}
