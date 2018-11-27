package com.example.algorithms.data_structures.queue;

/**
 * Created by fox.hu on 2018/11/27.
 */

public class ArrayQueue<T> implements Queue<T> {
    private int first = 0, last = 0;
    private T[] arrays = (T[]) new Object[1];

    @SuppressWarnings("unchecked")
    private void resize(int cap) {
        T[] temp = (T[]) new Object[cap];
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] == null) {
                first--;
                last--;
            } else {
                temp[i] = arrays[i];
            }
        }
        arrays = temp;
    }

    @Override
    public boolean isEmpty() {
        return (last - first) == 0 && last != 0;
    }

    @Override
    public int size() {
        return last - first;
    }

    @Override
    public void enQueue(T item) {
        if (last == arrays.length) {
            resize(arrays.length * 2);
        }
        arrays[last++] = item;
    }

    @Override
    public T deQueue() {
        final T item = arrays[first];
        arrays[first++] = null;
        return item;
    }
}
