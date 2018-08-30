package com.example.algorithms.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fox.hu on 2018/8/30.
 */
public class MaxConsecutiveOnes485Test {
    @Test
    public void findMaxConsecutiveOnes() throws Exception {
        MaxConsecutiveOnes485 maxConsecutiveOnes485 = new MaxConsecutiveOnes485();
        int[] ints = {1, 1, 1, 1, 1, 1,0,0,0,0,0,0,0};
        int maxConsecutiveOnes = maxConsecutiveOnes485.findMaxConsecutiveOnes(ints);
        Assert.assertEquals(6,maxConsecutiveOnes);
    }

}