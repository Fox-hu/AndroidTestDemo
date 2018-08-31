package com.example.algorithms.leetcode;

/**
 * Created by fox.hu on 2018/8/31.
 */

public class TwoSum167 {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 1) {
            return null;
        }

        int i = 0;
        int j = numbers.length - 1;

        while (i < j) {
            if (numbers[i] + numbers[j] > target) {
                j--;
            } else if (numbers[i] + numbers[j] < target) {
                i++;
            } else {
                return new int[]{i + 1, j + 1};
            }
        }
        return null;
    }
}
