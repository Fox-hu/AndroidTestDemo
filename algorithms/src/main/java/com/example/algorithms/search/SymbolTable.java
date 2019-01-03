package com.example.algorithms.search;

/**
 * Created by fox.hu on 2019/1/3.
 */

public interface SymbolTable<K extends Comparable<K>, V> {

    void put(K key, V value);

    V get(K key);

    void delete(K key);

    boolean contains(K key);

    boolean isEmpty();

    int size();

    K min();

    K max();

    /**
     * 小于等于key的最大键
     * @param key
     * @return
     */
    K floor(K key);

    /**
     * 大于等于key的最小键
     * @param key
     * @return
     */
    K ceiling(K key);


    /**
     * 小于key的键的数量
     * @param key
     * @return
     */
    int rank(K key);

    /**
     * 排名为k的键
     * @param k
     * @return
     */
    K select(int k);
}
