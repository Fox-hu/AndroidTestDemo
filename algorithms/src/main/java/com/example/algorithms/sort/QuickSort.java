package com.example.algorithms.sort;

/**
 * Created by fox.hu on 2018/12/20.
 */

public class QuickSort {
    public static void sort(Comparable[] a) {
        Sort.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        //1 寻找切分点 切分点的要点在于 在切分点的左侧 都比它小 在切分点的右侧 都比它大
        //2 递归的进行
        int partition = partition(a, lo, hi);
        sort(a, lo, partition - 1);
        sort(a, partition + 1, hi);
    }

    public static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            //只为了移动小端的指针
            while (Sort.less(a[++i], a[lo])) {
                if (i >= hi) {
                    break;
                }
            }
            //只为了移动大端的指针
            while (Sort.less(a[lo], a[--j])) {
                if (j <= lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            Sort.swap(a, i, j);
        }
        Sort.swap(a, lo, j);
        //交换lo j的位置后，在j的左侧都是比j小的 在j的右侧都是比j大的
        return j;
    }
}
