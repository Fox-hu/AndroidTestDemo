package com.example.algorithms.leetcode;

/**
 * Created by fox.hu on 2018/8/31.
 */

public class BestTimeSell121 {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i; j < prices.length; j++) {
                if (prices[i] < prices[j]) {
                    int profit = prices[j] - prices[i];
                    if (maxProfit < profit) {
                        maxProfit = profit;
                    }
                }
            }
        }
        return maxProfit;
    }
}
