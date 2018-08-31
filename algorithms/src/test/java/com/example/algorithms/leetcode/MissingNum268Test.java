package com.example.algorithms.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fox.hu on 2018/8/31.
 */
public class MissingNum268Test {
    @Test
    public void missingNumber() throws Exception {
        MissingNum268 missingNum268 = new MissingNum268();
        int[] ints = {0};
        int i = missingNum268.missingNumber(ints);
        Assert.assertEquals(1, i);
    }

}