/**
 * Leetcode - Algorithm - GuessNumberHigherOrLowerTwo
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class GuessNumberHigherOrLowerTwo implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private GuessNumberHigherOrLowerTwo() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int getMoneyAmount(int n); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private int[][] memo = new int[1][1];

        public int getMoneyAmount(int n) {
            memo = new int[n+1][n+1];
            return helper(1,n);
        }
        private int helper(int lo, int hi) {
            if (lo >= hi) { return 0; }
            if (memo[lo][hi] != 0) { return memo[lo][hi]; }
            int res = Integer.MAX_VALUE;
            for (int i = lo; i <= hi; i++) {
                int local = i + Math.max(helper(lo,i - 1), helper(i + 1,hi));
                res = Math.min(res,local);
            }
            memo[lo][hi] = res;
            return res;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int getMoneyAmount(int n) {
            return 2;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int getMoneyAmount(int n) {
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
        private GuessNumberHigherOrLowerTwo problem = new GuessNumberHigherOrLowerTwo();
        private Solution solution = null;

        // call method in solution
        private void call(int n, int ans) {
            System.out.println("For N = " + n + ", \tMin Value = " + solution.getMoneyAmount(n) + ", \tAnswer = " + ans);
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int n1 = 10;
            int ans1 = 16;

            int n2 = 9;
            int ans2 = 14;

            int n3 = 1;
            int ans3 = 0;

            int n4 = 4;
            int ans4 = 4;

            int n5 = 5;
            int ans5 = 6;

            /** involk call() method HERE */
            call(n1,ans1);
            call(n2,ans2);
            call(n3,ans3);
            call(n4,ans4);
            call(n5,ans5);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
