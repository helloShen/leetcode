/**
 * Leetcode - Algorithm - Best Time to Buy and Sell Stock Two
 */
package com.ciaoshen.leetcode;
import java.util.*;

class BestTimeToBuyAndSellStockTwo {
    public int maxProfitV1(int[] prices) {
        if (prices.length == 0) { return 0; }
        int buy = 0, sell = 0;
        int profit = 0;
        while (sell+1 < prices.length) {
            if (prices[sell+1] <= prices[sell]) {
                profit += (prices[sell] - prices[buy]);
                sell++; buy = sell;
            } else {
                sell++;
            }
        }
        profit += (prices[sell] - prices[buy]);
        return profit;
    }
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int todayProfit = prices[i] - prices[i-1];
            if (todayProfit > 0) { profit += todayProfit; } // 计算每天的受益，正受益才加入总收益。
        }
        return profit;
    }
    private static BestTimeToBuyAndSellStockTwo test = new BestTimeToBuyAndSellStockTwo();
    private static void testMaxProfit() {
        int[] pricesOne = new int[]{7, 1, 5, 3, 6, 4};
        int[] pricesTwo = new int[]{7, 6, 4, 3, 1};
        System.out.println(Arrays.toString(pricesOne) + "    >>>     " + "Best Profit = " + test.maxProfit(pricesOne));
        System.out.println(Arrays.toString(pricesTwo) + "    >>>     " + "Best Profit = " + test.maxProfit(pricesTwo));
    }
    public static void main(String[] args) {
        testMaxProfit();
    }
}
