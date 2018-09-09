/**
 * Leetcode - Algorithm - IntegerBreak
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class IntegerBreak implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private IntegerBreak() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int integerBreak(int n); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int integerBreak(int n) {
            int[] memo = new int[n+1];
            memo[1] = 1;
            for (int i = 2; i <= n; i++) {
                for (int j = 1; j <= i/2; j++) {
                    memo[i] = Math.max(memo[i],Math.max(j,memo[j]) * Math.max(i-j,memo[i-j]));
                }
            }
            return memo[n];
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int integerBreak(int n) {
            if (n == 2) { return 1; }
            if (n == 3) { return 2; }
            int product = 1;
            while (n > 4) {
                product *= 3;
                n -= 3;
            }
            return product * n;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int integerBreak(int n) {
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
        private IntegerBreak problem = new IntegerBreak();
        private Solution solution = null;

        // call method in solution
        private void call(int n) {
            System.out.println(n + " -> " + solution.integerBreak(n));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int n1 = 2;
            int n2 = 10;

            /** involk call() method HERE */
            // call(n1);
            // call(n2);
            for (int i = 1; i < 58; i++) {
                call(i);
            }
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
