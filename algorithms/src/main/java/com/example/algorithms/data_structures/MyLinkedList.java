package com.example.algorithms.data_structures;

import android.support.annotation.NonNull;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by fox.hu on 2018/8/6.
 */

public class MyLinkedList<T> implements Iterable<T> {

    private int theSize;
    private int modCount;
    private Node<T> beginMarker;
    private Node<T> endMarker;


    public MyLinkedList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    public void add(T data) {
        add(size(), data);
    }

    private void add(int index, T data) {
        addBefore(getNode(index, 0, size()), data);
    }

    public T get(int index) {
        return getNode(index).data;
    }

    public int size() {
        return theSize;
    }

    public T set(int index, T data) {
        Node<T> node = getNode(index);
        T oldData = node.data;
        node.data = data;
        return oldData;
    }

    private void doClear() {
        //创建头结点
        beginMarker = new Node<>(null, null, null);
        //创建尾结点
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    public void remove(int index) {

    }

    private Node<T> getNode(int index) {
        return getNode(index, 0, size() - 1);
    }

    private Node<T> getNode(int index, int low, int high) {
        if (index > high || index < low) {
            throw new IllegalArgumentException();
        }

        Node<T> p;
        if (index < size() / 2) {
            //从头遍历
            p = beginMarker.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
        } else {
            //从尾遍历
            p = endMarker;
            for (int i = size(); i > index; i--) {
                p = p.pre;
            }
        }
        return p;
    }

    /**
     * 在node前端插入一个data域为data的结点
     * 采用先连后断的方式，为链表添加一个结点
     *
     * @param node
     * @param data
     */
    private void addBefore(Node<T> node, T data) {
        Node<T> tNode = new Node<>(data, node.pre, node.next);
        tNode.pre.next = tNode;
        tNode.next.pre = tNode;

        theSize++;
        modCount++;
    }

    private T remove(Node<T> node) {
        Node<T> pre = node.pre;
        Node<T> next = node.next;
        pre.next = next;
        next.pre = pre;

        theSize--;
        modCount++;
        return node.data;
    }


    private static class Node<T> {
        T data;
        Node<T> pre;
        Node<T> next;

        public Node(T data, Node<T> pre, Node<T> next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    private class LinkedListIterator implements Iterator<T> {

        private Node<T> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public T next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T data = current.data;
            current = current.next;
            okToRemove = true;
            return data;
        }

        @Override
        public void remove() {
            if(modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }

            if(!okToRemove){
                throw new IllegalStateException();
            }

            //此处是current.pre 和ArrayList不相同
            MyLinkedList.this.remove(current.pre);
        }
    }
}
