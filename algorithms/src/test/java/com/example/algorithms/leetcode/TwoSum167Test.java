package com.example.algorithms.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fox.hu on 2018/8/31.
 */
public class TwoSum167Test {
    @Test
    public void twoSum() throws Exception {
        TwoSum167 twoSum167 = new TwoSum167();
        int[] ints = {2, 7, 11, 15};
        int[] expected = {1,2};
        int[] actual = twoSum167.twoSum(ints, 9);
        Assert.assertArrayEquals(expected,actual);
    }

}