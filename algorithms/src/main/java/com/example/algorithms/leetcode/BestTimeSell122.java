package com.example.algorithms.leetcode;

/**
 * Created by fox.hu on 2018/8/30.
 */

public class BestTimeSell122 {
    public int maxProfit(int[] prices) {
        //采用贪心算法 比较相邻两边的值，如果后者比前者大，就增加两者的差值到计数器
        if (prices.length == 0) {
            return 0;
        }

        int cnt = 0;
        for (int i = 1; i < prices.length; i++) {
            int k = prices[i] - prices[i - 1];
            if (k > 0) {
                cnt += k;
            }
        }
        return cnt;
    }
}
