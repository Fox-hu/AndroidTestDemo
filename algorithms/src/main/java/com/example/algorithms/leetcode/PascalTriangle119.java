package com.example.algorithms.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fox.hu on 2018/8/30.
 */

public class PascalTriangle119 {
    public List<Integer> getRow(int rowIndex) {
        Integer[] integers = new Integer[rowIndex + 1];
        Arrays.fill(integers, 0);

        integers[0] = 1;
        for (int i = 1; i < integers.length; i++) {
            for (int j = i; j > 0; j--) {
                integers[j] = integers[j] + integers[j - 1];
            }
        }

        return Arrays.asList(integers);
    }
}
