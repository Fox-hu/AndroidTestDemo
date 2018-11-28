package com.example.algorithms.sort;

/**
 * Created by fox.hu on 2018/11/28.
 */

public class Sort {

    public static void selectionSort(Comparable[] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (less(input[j], input[i])) {
                    swap(input, i, j);
                }
            }
        }
    }

    public static void insertSort(Comparable[] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(input[j], input[j - 1])) {
                    swap(input, j, j - 1);
                }
            }
        }
    }

    public static void shellSort(Comparable[] input) {
        int h = 1;
        while (h < input.length / 3) {
            h = h * 3 + 1;
        }
        while (h >= 1) {
            for (int i = h; i < input.length; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (less(input[j], input[j - h])) {
                        swap(input, j, j - h);
                    }
                }
            }
            h = h / 3;
        }
    }

    //线性复杂度实现的随机打乱 洗牌功能
    public static void shuffle(Object[] input) {
        for (int i = 0; i < input.length; i++) {
            int r = (int) (Math.random() * (i + 1));
            swap(input, i, r);
        }
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void swap(Object[] a, int i, int j) {
        Object t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }
}
