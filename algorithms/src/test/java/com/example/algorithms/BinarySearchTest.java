package com.example.algorithms;

import org.junit.Test;

/**
 * Created by fox.hu on 2018/5/30.
 */
public class BinarySearchTest {
    @Test
    public void rank() throws Exception {
        int[] args = new int[]{1,11,24,55,66};
        int rank = BinarySearch.rank(24, args);
        System.out.print(rank);
    }

}