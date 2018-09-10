package com.example.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fox.hu on 2018/9/7.
 */

public class DisappearedNumInArray448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        Arrays.sort(nums);

        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == nums[i+1]){

            }
        }

        return integers;
    }
}
