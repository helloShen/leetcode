/**
 * Leetcode - Algorithm - BestTimeToBuyAndSellStockWithCooldown
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class BestTimeToBuyAndSellStockWithCooldown implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private BestTimeToBuyAndSellStockWithCooldown() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int maxProfit(int[] prices); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public int maxProfit(int[] prices) {
            int[] dp = new int[prices.length+2];
            for (int i = 3; i < dp.length; i++) {
                int today = prices[i-2];
                if (today <= prices[i-3]) {
                    dp[i] = dp[i-1];
                } else {
                    int max = 0;
                    for (int j = 2; j <= i; j++) {
                        max = Math.max(max, dp[j-2] + today - prices[j-2]);
                    }
                    dp[i] = max;
                }
            }
            // System.out.println("DP array = " + Arrays.toString(dp));
            return dp[dp.length-1];
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public int maxProfit(int[] prices) {
            int[] buy = new int[]{0,-prices[0]};
            int[] sell = new int[]{0,0,0};
            for (int i = 1; i < prices.length; i++) {
                buy[0] = buy[1];
                sell[0] = sell[1];
                sell[1] = sell[2];
                buy[1] = Math.max(buy[0],sell[0]-prices[i]);
                sell[2] = Math.max(sell[1],buy[0]+prices[i]);
            }
            return sell[2];
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public int maxProfit(int[] prices) {
            return 3;
        }
    }
    // you can expand more solutions HERE if you want...


    /**
     * register a solution in the solution registry
     * return false if this type of solution already exist in the registry.
     */
    private boolean register(Solution s) {
        return (solutions.put(s.id,s) == null)? true : false;
    }
    /**
     * chose one of the solution to test
     * return null if solution id does not exist
     */
    private Solution solution(int id) {
        return solutions.get(id);
    }

    private static class Test {
        private BestTimeToBuyAndSellStockWithCooldown problem = new BestTimeToBuyAndSellStockWithCooldown();
        private Solution solution = null;

        // call method in solution
        private void call(int[] prices) {
            System.out.println("For stock: " + Arrays.toString(prices));
            System.out.println("Maximum Profit = " + solution.maxProfit(prices));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] prices1 = new int[]{1,2,3,0,2};
            int[] prices2 = new int[]{1,7,2,4};

            /** involk call() method HERE */
            call(prices1);
            call(prices2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
    }
}
