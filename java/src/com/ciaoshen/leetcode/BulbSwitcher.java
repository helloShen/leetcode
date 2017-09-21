/**
 * Leetcode - Algorithm - BulbSwitcher
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class BulbSwitcher implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private BulbSwitcher() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int bulbSwitch(int n); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int bulbSwitch(int n) {
            if (n == 0) { return 0; }
            int sub = bulbSwitch(n-1);
            boolean nth = false;
            for (int i = 1; i <= n; i++) {
                if (n % i == 0) { nth = !nth; }
            }
            return (nth)? sub + 1 : sub;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int bulbSwitch(int n) {
            int result = 0;
            for (int i = 1; i <= n; i++) {
                boolean ith = false;
                for (int j = 1; j <= i; j++) {
                    if (i % j == 0) { ith = !ith; }
                }
                if (ith) { ++result; }
            }
            return result;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int bulbSwitch(int n) {
            int result = 0;
            int odd = 1;
            n -= odd;
            while (n >= 0) {
                ++result; ++odd; ++odd;
                n -= odd;
            }
            return result;
        }
    }
    // you can expand more solutions HERE if you want...

    private class Solution4 extends Solution {
        { super.id = 4; }

        public int bulbSwitch(int n) {
            return (int)Math.sqrt(n);
        }
    }

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
        private BulbSwitcher problem = new BulbSwitcher();
        private Solution solution = null;

        // call method in solution
        private void call(int n) {
            System.out.println("n = " + n + ", result = " + solution.bulbSwitch(n));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            for (int i = 1; i < 50; i++) {
                call(i);
            }
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        // test.test(3);
        test.test(4);
    }
}
