package com.example.algorithms.leetcode;

/**
 * Created by fox.hu on 2018/8/30.
 */

public class RemoveElement27 {
    public int removeElement(int[] nums, int val) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k++] = nums[i];
            }
        }
        return k;
    }
}
