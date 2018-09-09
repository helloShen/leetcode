/**
 * Leetcode - Algorithm - PowerOfThree
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class PowerOfThree implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private PowerOfThree() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public boolean isPowerOfThree(int n); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public boolean isPowerOfThree(int n) {
            if (n == 0) { return false; }
            while (n % 3 == 0) { n /= 3; }
            return n == 1;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public boolean isPowerOfThree(int n) {
            if (n == 1) {
                return true;
            } else if (n == 0 || n % 3 != 0) {
                return false;
            } else {
                return isPowerOfThree(n / 3);
            }
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public boolean isPowerOfThree(int n) {
            return n > 0 && 1162261467 % n==0;
        }
        protected void sometest() {
            for (int i = 0, n = 1; i < 10; i++) {
                System.out.println(n + "\t" + getbits(n));
                n *= 3;
            }
        }
        private String getbits(int n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 32; i++) {
                if (n == 0) {
                    sb.insert(0,0);
                } else {
                    sb.insert(0,n & 1);
                    n >>>= 1;
                }
            }
            return sb.toString();
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
        private PowerOfThree problem = new PowerOfThree();
        private Solution solution = null;

        // call method in solution
        private void call(int n) {
            String res = (solution.isPowerOfThree(n))? "YES" : "NO";
            System.out.println(n + " is power of 3? " + res);
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            for (int i = 0; i < 30; i++) {
                call(i);
            }
            // solution.sometest();
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        test.test(3);
    }
}
