package com.example.algorithms.sort;

/**
 * Created by fox.hu on 2018/11/28.
 */

public class SelectionSort {
    public static void sort(Comparable[] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (Sort.less(input[j], input[i])) {
                    Sort.swap(input, i, j);
                }
            }
        }
    }
}
