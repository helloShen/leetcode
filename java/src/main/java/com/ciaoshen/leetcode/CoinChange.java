/**
 * Leetcode - Algorithm - CoinChange
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class CoinChange implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private CoinChange() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int coinChange(int[] coins, int amount); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /** 标准DFS */
    private class Solution1 extends Solution {
        { super.id = 1; }
        private int[] coins = new int[0];
        private int minCount = -1;
        public int coinChange(int[] coins, int amount) {
            this.coins = coins;
            minCount = -1;
            dfs(0,amount);
            return minCount;
        }
        private void dfs(int count, int amount) {
            if (amount < 0) { return; }
            if (amount == 0) { minCount = (minCount == -1)? count : Math.min(minCount,count); return; }
            ++count;
            for (int coin : coins) {
                dfs(count,amount-coin);
            }
        }
    }

    /** 带剪枝的DFS */
    private class Solution2 extends Solution {
        { super.id = 2; }
        private int[] coins = new int[0];
        private int minCount = -1;
        public int coinChange(int[] coins, int amount) {
            Arrays.sort(coins);
            // System.out.println(Arrays.toString(coins));
            this.coins = coins;
            minCount = -1;
            dfs(0,amount);
            return minCount;
        }
        private void dfs(int count, int amount) {
            if (minCount != -1 && count >= minCount) { return; }    // 及时剪枝
            if (amount < 0) { return; }
            if (amount == 0) { minCount = (minCount == -1)? count : Math.min(minCount,count); return; }
            ++count;
            for (int i = coins.length-1; i >= 0; i--) {             // 为了配合剪枝，从大到小遍历
                dfs(count,amount-coins[i]);
            }
        }
    }

    /** 标准的DP */
    private class Solution3 extends Solution {
        { super.id = 3; }
        private int[] coins = new int[0];
        private int[] memo = new int[0];
        public int coinChange(int[] coins, int amount) {
            this.coins = coins;
            memo = new int[amount+1];
            dp(amount);
            return memo[amount];
        }
        private void dp(int amount) {
            for (int i = 1; i <= amount; i++) {
                int localMin = -1;
                for (int coin : coins) {
                    int subid = i - coin;
                    if (subid >= 0) {
                        int sub = memo[subid];
                        int newVal = (sub == -1)? -1 : sub + 1;
                        if (newVal != -1) { localMin = (localMin == -1)? newVal : Math.min(localMin,newVal); }
                    }
                }
                memo[i] = localMin;
            }
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
        private CoinChange problem = new CoinChange();
        private Solution solution = null;

        // call method in solution
        private void call(int[] coins, int amount) {
            System.out.println("For coins " + Arrays.toString(coins) + ", (amount = " + amount + ")" + "), min = " + solution.coinChange(coins,amount));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] coins1 = new int[]{1,2,5};
            int amount1 = 11;
            int[] coins2 = new int[]{2};
            int amount2 = 3;
            int[] coins3 = new int[]{186,419,83,408};
            int amount3 = 6249;

            /** involk call() method HERE */
            // for (int i = 1; i <= 100; i++) {
            //     call(coins1,i);
            // }
            call(coins1,amount1);
            call(coins2,amount2);
            call(coins3,amount3);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        test.test(3);
    }
}
