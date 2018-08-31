package com.example.algorithms.leetcode;

/**
 * Created by fox.hu on 2018/8/31.
 */

public class TwoSum167 {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 1) {
            return null;
        }
        //本题设中 一定会有解 采用双指针法 指向数组两端
        //相加大于target则头指针后移 小于target则尾指针前移，直到相等

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
