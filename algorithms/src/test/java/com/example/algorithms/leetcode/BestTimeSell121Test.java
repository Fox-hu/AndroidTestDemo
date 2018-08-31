package com.example.algorithms.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fox.hu on 2018/8/31.
 */
public class BestTimeSell121Test {
    @Test
    public void maxProfit() throws Exception {
        BestTimeSell121 bestTimeSell121 = new BestTimeSell121();
        int[] ints = {7,6,4,3,1};
        int i = bestTimeSell121.maxProfit(ints);
        Assert.assertEquals(0,i);
    }

}