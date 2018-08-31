package com.example.algorithms.leetcode;

/**
 * Created by fox.hu on 2018/8/31.
 */

public class TwoSum1 {
    public int[] twoSum(int[] nums, int target) {
        int[] ints = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    ints[0] = i;
                    ints[1] = j;
                    break;
                }
            }
        }
        return ints;
    }
}
