package com.example.algorithms.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fox.hu on 2018/8/30.
 */
public class MoveZeroes283Test {
    @Test
    public void moveZeroes() throws Exception {
        MoveZeroes283 moveZeroes283 = new MoveZeroes283();
        int[] ints = new int[]{0,1,0,3,12};
        moveZeroes283.moveZeroes(ints);
        Assert.assertArrayEquals(new int[]{1,3,12,0,0},ints);
    }

}