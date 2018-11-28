package com.example.algorithms.data_structures.stack;

import android.support.annotation.NonNull;

import java.util.Iterator;

/**
 * Created by fox.hu on 2018/11/27.
 */

public class LinkStack<T> implements Stack<T>, Iterable<T> {

    //first的node是栈顶
    private Node first = null;
    private int length = 0;

    private class Node {
        T item;
        Node next;
    }

    @Override
    public void push(T item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    @Override
    public T pop() {
        if (first == null) {
            throw new IllegalStateException();
        }
        T item = first.item;
        first = first.next;
        length--;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return length;
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            final T item = current.item;
            current = current.next;
            return item;
        }
    }
}
