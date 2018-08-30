package com.example.algorithms.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fox.hu on 2018/8/30.
 */
public class MajorityElement169Test {
    @Test
    public void majorityElement() throws Exception {
        int[] nums = new int[]{2,2,1,1,1,2,2};
        MajorityElement169 majorityElement169 = new MajorityElement169();
        int i = majorityElement169.majorityElement(nums);
        Assert.assertEquals(2,i);
    }
}