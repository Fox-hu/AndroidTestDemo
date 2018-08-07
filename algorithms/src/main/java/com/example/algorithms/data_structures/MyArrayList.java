package com.example.algorithms.data_structures;

import android.support.annotation.NonNull;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by fox.hu on 2018/8/3.
 */

public class MyArrayList<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int theSize;
    private T[] theItems;

    public MyArrayList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    public void set(int index, T value) {
        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException();
        }
        theItems[index] = value;
    }

    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException();
        }

        return theItems[index];
    }

    public void add(T value) {
        add(size(), value);
    }

    public void add(int index, T value) {
        if (size() == theItems.length) {
            ensureCapacity(size() * 2 + 1);
        }

        for (int i = size(); i > index; i--) {
            theItems[i] = theItems[i - 1];
        }
        theItems[index] = value;
        theSize++;
    }


    public T remove(int index) {
        T value = theItems[index];
        for (int i = index; i < size() - 1; i++) {
            theItems[i] = theItems[i + 1];
        }
        theSize--;
        return value;
    }


    private void doClear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize) {
            return;
        }

        T[] oldItems = theItems;

        theItems = (T[]) new Object[newCapacity];

        for (int i = 0; i < size(); i++) {
            theItems[i] = oldItems[i];
        }
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    private class ArrayListIterator implements Iterator<T> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return theItems[current++];
        }
    }
}
