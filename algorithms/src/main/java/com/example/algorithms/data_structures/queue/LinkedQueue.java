package com.example.algorithms.data_structures.queue;

/**
 * Created by fox.hu on 2018/11/27.
 */

public class LinkedQueue<T> implements Queue<T> {
    private int lenght;
    //队列是从头删除，从尾添加 注意 两个游标此时未被初始化
    private Node first, last;

    private class Node {
        T item;
        Node next;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return lenght;
    }

    @Override
    public void enQueue(T item) {
        //从尾添加
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
    }

    @Override
    public T deQueue() {
        //从头删除
        final T item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        lenght--;
        return item;
    }
}
