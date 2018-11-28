package com.example.algorithms.sort;

/**
 * Created by fox.hu on 2018/11/28.
 */

public class Sort {

    public static void SelectionSort(Comparable[] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (less(input[j], input[i])) {
                    swap(input, i, j);
                }
            }
        }
    }

    public static void InsertSort(Comparable[] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(input[j], input[j - 1])) {
                    swap(input, j, j - 1);
                }
            }
        }
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void swap(Comparable[] a, int i, int j) {
        Comparable t = a[i];
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
