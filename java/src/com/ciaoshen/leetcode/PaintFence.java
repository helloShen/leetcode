/**
 * Leetcode - Algorithm - PaintFence
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class PaintFence implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private PaintFence() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int numWays(int n, int k); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public int numWays(int n, int k) {
            if (n == 0 || k == 0) { return 0; }
            if (n == 1) { return k; }
            if (n == 2) { return k * k; }
            return (numWays(n-1,k) + numWays(n-2,k)) * (k-1);
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public int numWays(int n, int k) {
            int[] memo = new int[n+1];
            return helper(n,k,memo);
        }
        private int helper(int n, int k, int[] memo) {
            if (n == 0 || k == 0) { return 0; }
            if (n == 1) { return k; }
            if (n == 2) { return k * k; }
            memo[n] = (((memo[n-1] == 0)? helper(n-1,k,memo) : memo[n-1]) + ((memo[n-2] == 0)? helper(n-2,k,memo) : memo[n-2])) * (k-1);
            return memo[n];
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public int numWays(int n, int k) {
            if (n == 0 || k == 0) { return 0; }
            if (n == 1) { return k; }
            if (n == 2) { return k * k; }
            int a = k, b = k * k;
            int m = 3;
            while (m++ <= n) {
                int next = (a + b)*(k-1);
                a = b;
                b = next;
            }
            return b;
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
        private PaintFence problem = new PaintFence();
        private Solution solution = null;

        // call method in solution
        private void call(int n ,int k) {
            System.out.format("Paint %d fence with %d color: %d\n", n, k, solution.numWays(n,k));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */

            /** involk call() method HERE */
            for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        call(i,j);
                    }
                }
            }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1); // call by the solution id
        test.test(2); // call by the solution id
        test.test(3); // call by the solution id
    }
}
