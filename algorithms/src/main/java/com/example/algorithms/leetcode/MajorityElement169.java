package com.example.algorithms.leetcode;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by fox.hu on 2018/8/30.
 */

public class MajorityElement169 {


    public int majorityElement(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 0);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }

        Integer sum = 0;
        Integer index = 0;
        Set<Integer> integers = map.keySet();
        for (Integer integer : integers) {
            if (map.get(integer) > sum) {
                sum = map.get(integer);
                index = integer;
            }
        }

        return index;
    }
}
