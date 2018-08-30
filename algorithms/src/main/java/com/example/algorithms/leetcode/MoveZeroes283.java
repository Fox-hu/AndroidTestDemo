package com.example.algorithms.leetcode;

/**
 * Created by fox.hu on 2018/8/30.
 */

public class MoveZeroes283 {
    //要求是将0后移，实际上就是将非0前移
    //两个指针控制 一个遍历整改数组 一个记录非0项 然后将数组剩余项填充为0

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k++] = nums[i];
            }
        }

        while (k < nums.length) {
            nums[k++] = 0;
        }
    }
}
