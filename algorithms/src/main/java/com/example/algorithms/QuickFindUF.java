package com.example.algorithms;

/**
 * Created by Administrator on 2018/7/10.
 */

public class QuickFindUF {
    private int[] id;

    public QuickFindUF(int n) {
        id = new int[n];
        for (int i : id) {
            id[i] = i;
        }
    }

    public boolean isConnected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i : id) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }
}
