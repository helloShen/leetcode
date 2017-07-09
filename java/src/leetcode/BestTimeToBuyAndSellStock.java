/**
 * Leetcode - Algorithm - Best Time to Buy and Sell Stock
 */
package com.ciaoshen.leetcode;
import java.util.*;

class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min,prices[i]);
            maxProfit = Math.max(maxProfit,prices[i] - min);
        }
        return maxProfit;
    }
    private static BestTimeToBuyAndSellStock test = new BestTimeToBuyAndSellStock();
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
