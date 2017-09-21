/**
 * Leetcode - Algorithm - NumberComplement
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class NumberComplement implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private NumberComplement() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int findComplement(int num); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        private final int INT_SIZE = 32;
        public int findComplement(int num) {
            int len = 0;
            int copy = num;
            while (copy != 0) {
                copy >>>= 1; ++len;
            }
            int mask = (~0) >>> (INT_SIZE - len);
            // System.out.println("Mask \t\t=\t " + Test.getbits(mask));
            return (num ^ mask);
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public int findComplement(int num) {
            int mask = (Integer.highestOneBit(num) << 1) - 1;
            return num ^ mask;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public int findComplement(int num) {
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
        private NumberComplement problem = new NumberComplement();
        private Solution solution = null;

        // call method in solution
        private void call(int num) {
            System.out.println("Number \t\t=\t " + getbits(num));
            System.out.println("Complement \t=\t " + getbits(solution.findComplement(num)) + "\n");
        }
        // represent an integer with it's bits
        private static String getbits(int num) {
            char[] nc = new char[32];
            Arrays.fill(nc,'0');
            int mask = 1;
            for (int i = 31; i >= 0 && num != 0; i--, num >>>= 1) {
                if ((num & mask) != 0) { nc[i] = '1'; }
            }
            return new String(nc);
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int num1 = 1;
            int num2 = 5;

            /** involk call() method HERE */
            call(num1);
            call(num2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
