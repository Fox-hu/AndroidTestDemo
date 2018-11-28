package com.example.algorithms.sort;

import org.junit.Test;

/**
 * Created by fox.hu on 2018/11/28.
 */
public class SelectionSortTest {
    @Test
    public void sort() throws Exception {
        Integer[] integers = new Integer[]{2, 3, 12, 3, 4, 5};
        SelectionSort.sort(integers);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }
}