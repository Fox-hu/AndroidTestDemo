package com.example.algorithms.sort;

import org.junit.Test;

/**
 * Created by fox.hu on 2018/11/28.
 */
public class SortTest {
    private final Integer[] integers = new Integer[]{5, 8, 7, 2, 3, 12, 9, 10, 11, 1};

    @Test
    public void selectionSort() throws Exception {
        Sort.selectionSort(integers);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }

    @Test
    public void insertSort() throws Exception {
        Sort.insertSort(integers);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }

    @Test
    public void shellSort() throws Exception {
        Sort.shellSort(integers);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }

    @Test
    public void mergeSort() throws Exception {
        MergeSort.sort(integers);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }
}