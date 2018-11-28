package com.example.algorithms.data_structures.stack;

import android.support.annotation.NonNull;

import java.util.Iterator;

/**
 * Created by fox.hu on 2018/11/27.
 */

public class ResizingArrayStack<T> implements Stack<T>, Iterable<T> {

    private T[] arrays = (T[]) new Object[1];
    private int length = 0;


    @Override
    public void push(T item) {
        if (length == arrays.length) {
            resize(2 * length);
        }
        arrays[length++] = item;
    }

    @SuppressWarnings("unchecked")
    private void resize(int cap) {
        T[] temp = (T[]) new Object[cap];
        for (int i = 0; i < length; i++) {
            temp[i] = arrays[i];
        }
        arrays = temp;
    }

    @Override
    public T pop() {
        final T item = arrays[--length];
        //避免对象游离
        arrays[length] = null;
        if (length > 0 && length == arrays.length / 4) {
            resize(arrays.length / 2);
        }
        return item;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int size() {
        return length;
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<T> {
        private int i = length;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return arrays[--i];
        }
    }
}
