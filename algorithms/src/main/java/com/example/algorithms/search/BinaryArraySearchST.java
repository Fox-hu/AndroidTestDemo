package com.example.algorithms.search;

/**
 * 基于有序数组的二分查找
 *
 * @author fox.hu
 * @date 2019/1/3
 */

public class BinaryArraySearchST<K extends Comparable<K>, V> implements SymbolTable<K, V> {
    private K[] keys;
    private V[] vlas;
    private int N;

    public BinaryArraySearchST(int capacity) {
        keys = (K[]) new Comparable[capacity];
        vlas = (V[]) new Object[capacity];

    }

    @Override
    public void put(K key, V value) {
        // 查找键 找到就更新 没找到就创建新的元素 调整数组的代码忽略
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vlas[i] = value;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vlas[j] = vlas[j - 1];
        }
        keys[i] = key;
        vlas[i] = value;
        N++;
    }

    @Override
    public V get(K key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return vlas[i];
        } else {
            return null;
        }
    }

    @Override
    public void delete(K key) {

    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public K min() {
        return null;
    }

    @Override
    public K max() {
        return null;
    }

    @Override
    public K floor(K key) {
        return null;
    }

    @Override
    public K ceiling(K key) {
        return null;
    }

    @Override
    public int rank(K key) {
        //如果表中不存在该键，rank还是返回该表中小于它键的数量
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    @Override
    public K select(int k) {
        return null;
    }
}
