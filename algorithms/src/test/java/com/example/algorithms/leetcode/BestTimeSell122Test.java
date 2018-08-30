package com.example.algorithms.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fox.hu on 2018/8/30.
 */
public class BestTimeSell122Test {
    @Test
    public void maxProfit() throws Exception {
        BestTimeSell122 bestTimeSell122 = new BestTimeSell122();
        int[] ints = {7,6,4,3,1};
        int i = bestTimeSell122.maxProfit(ints);
        Assert.assertEquals(0, i);
    }

}