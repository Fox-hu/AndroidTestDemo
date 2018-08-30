package com.example.algorithms.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fox.hu on 2018/8/30.
 */
public class PascalTriangle119Test {
    @Test
    public void getRow() throws Exception {
        PascalTriangle119 pascalTriangle119 = new PascalTriangle119();
        List<Integer> row = pascalTriangle119.getRow(3);
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(3);
        integers.add(3);
        integers.add(1);
        Assert.assertEquals(integers,row);
    }

}