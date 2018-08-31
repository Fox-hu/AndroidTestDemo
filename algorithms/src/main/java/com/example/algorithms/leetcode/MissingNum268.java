package com.example.algorithms.leetcode;

/**
 * Created by fox.hu on 2018/8/31.
 */

public class MissingNum268 {
    public int missingNumber(int[] nums) {
        //利用等差公式求和 再减去数组中所有的值，剩下的即为所缺值
        int n = nums.length + 1;
        int sum = n * (n - 1) / 2;

        for (int num : nums) {
            sum -= num;
        }
        return sum;
    }
}
