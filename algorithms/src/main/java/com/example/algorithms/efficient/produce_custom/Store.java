package com.example.algorithms.efficient.produce_custom;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by fox.hu on 2018/8/2.
 */

public class Store {
    static final int MAX_SIZE = 100;

    public static void test() {
        Store store = new Store();
        store.init(3, 2);
        store.start();
    }

    List<Integer> integerList = new LinkedList<>();

    List<Thread> produceList = new ArrayList<>();

    List<Thread> customList = new ArrayList<>();

    public void init(int productThread, int customThread) {
        for (int i = 0; i < productThread; i++) {
            produceList.add(new Produce("" + i, integerList, MAX_SIZE));
        }
        for (int i = 0; i < customThread; i++) {
            customList.add(new Custom("" + i, integerList, MAX_SIZE));
        }
    }

    public void start() {
        for (Thread produce : produceList) {
            produce.start();
        }

        for (Thread custom : customList) {
            custom.start();
        }
    }
}
