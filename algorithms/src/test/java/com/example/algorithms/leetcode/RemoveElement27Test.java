package com.example.algorithms.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fox.hu on 2018/8/30.
 */
public class RemoveElement27Test {
    @Test
    public void removeElement() throws Exception {
        RemoveElement27 removeElement27 = new RemoveElement27();
        int[] nums = {0,1,2,2,3,0,4,2};
        int i = removeElement27.removeElement(nums, 2);
        Assert.assertEquals(5, i);
    }
}