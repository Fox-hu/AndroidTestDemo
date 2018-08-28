package com.example.algorithms.leetcode;

/**
 * Created by fox.hu on 2018/8/28.
 */

public class ReshapeMatrix566 {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int rowLength = nums.length;
        int columnLength = nums[0].length;
        if (rowLength * columnLength != r * c) {
            return nums;
        }

        int[] temp = new int[rowLength * columnLength];

        //将此二维数组转换为一个临时一位数组
        int index = 0;
        for (int[] num : nums) {
            for (int i : num) {
                temp[index++] = i;
            }
        }

        //将此一维数组重新赋值到新的二维数组中
        int index1 = 0;
        int[][] ret = new int[r][c];
        for (int k = 0; k < r; k++) {
            for (int l = 0; l < c; l++) {
                ret[k][l] = temp[index1++];
            }
        }
        return ret;
    }
}
