package com.example.algorithms;

/**
 * Created by Administrator on 2018/7/10.
 */

public class QuickUnionUF {
    private int[] id;

    public QuickUnionUF(int n) {
        id = new int[n];
        for (int i : id) {
            id[i] = i;
        }
    }

    private int root(int index) {
        while (index != id[index]) {
            index = id[index];
        }
        return index;
    }

    public boolean isConnected(int p, int q) {
        return root(q) == root(p);
    }

    public void union(int p, int q){
        int rootp = root(p);
        int rootq = root(q);
        id[rootp] = rootq;
    }
}
