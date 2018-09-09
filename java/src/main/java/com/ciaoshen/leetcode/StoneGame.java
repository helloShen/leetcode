/**
 * Leetcode - Algorithm - StoneGame
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class StoneGame implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private StoneGame() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public boolean stoneGame(int[] piles); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private int[] p;
        private int[][] dp;

        public boolean stoneGame(int[] piles) {
            p = piles;
            int len = piles.length;
            dp = new int[len][len];
            return dp(0,piles.length-1) > 0;
        }
        public int dp(int lo, int hi) {
            if (lo == hi) { return 0; }
            if (dp[lo][hi] != 0) { return dp[lo][hi]; }
            int res = 0;
            if ((hi - lo + 1) % 2 == 0) {
                res = Math.max(dp(lo+1,hi) + p[lo], dp(lo,hi-1) + p[hi]);
            } else {
                res = Math.min(dp(lo+1,hi) - p[lo], dp(lo,hi-1) - p[hi]);
            }
            dp[lo][hi] = res;
            return res;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        // 先下的必胜
        // 答案就是return true
        public boolean stoneGame(int[] piles) {
            return true;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public boolean stoneGame(int[] piles) {
            return false;
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
        private StoneGame problem = new StoneGame();
        private Solution solution = null;

        // call method in solution
        private void call() {
            /** write your code HERE */
            // ... ...
            // ... ...
            // ... ...
            // ... ...
            // ... ...
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            //... ...
            //... ...
            //... ...
            //... ...
            //... ...
            //... ...

            /** involk call() method HERE */
            call();
            call();
            call();
            call();
            call();
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1); // call by the solution id
    }
}
