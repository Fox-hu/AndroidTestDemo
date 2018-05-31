package com.example.algorithms;

/**
 *
 * @author fox.hu
 * @date 2018/5/29
 */

public class BinarySearch {
    //args必须有序
    public static int rank(int key, int[] args) {
        int lo = 0;
        int hi = args.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key > mid) {
                lo = mid + 1;
            } else if (key < mid){
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
