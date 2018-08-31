package com.example.algorithms.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fox.hu on 2018/8/31.
 */
public class TwoSum1Test {
    @Test
    public void twoSum() throws Exception {
        TwoSum1 twoSum1 = new TwoSum1();
        int[] ints = {3,3};
        int[] ints2 = twoSum1.twoSum(ints, 6);
        int[] ints1 = {0, 1};
        Assert.assertArrayEquals(ints1, ints2);
    }

}