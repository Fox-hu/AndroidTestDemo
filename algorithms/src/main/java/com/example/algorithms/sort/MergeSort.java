package com.example.algorithms.sort;

/**
 * Created by fox.hu on 2018/12/17.
 */

public class MergeSort {
    private static Comparable[] axu;

    public static void sort(Comparable[] a) {
        axu = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            axu[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = axu[j++];
            } else if (j > hi) {
                a[k] = axu[i++];
            } else if (Sort.less(axu[j], axu[i])) {
                a[k] = axu[j++];
            } else {
                a[k] = axu[i++];
            }
        }
    }
}
