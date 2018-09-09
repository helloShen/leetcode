/**
 * Leetcode - Algorithm - SumOfTwoIntegers
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class SumOfTwoIntegers implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private SumOfTwoIntegers() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int getSum(int a, int b); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /* 老老实实一位一位切。第一版 */
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public int getSum(int a, int b) {
            int result = 0;
            int register = 0;
            int mask = 1;
            for (int i = 0; i < 32; i++) {
                // 切出下一位
                int x = a & mask; a >>>= 1;
                int y = b & mask; b >>>= 1;
                // 当前位并到结果中
                int thisbyte = (x ^ y ^ register) << i;
                result |= thisbyte;
                // 更新进位状态
                int count = 0;
                if (x != 0) { ++count; }
                if (y != 0) { ++count; }
                if (register != 0) { ++count; }
                register = (count > 1)? 1 : 0;
            }
            return result;
        }
    }

    /* 老老实实一位一位切。用位操作更新进位。*/
    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public int getSum(int a, int b) {
            int result = 0;
            int register = 0;
            int mask = 1;
            for (int i = 0; i < 32; i++) {
                // 切出下一位
                int x = a & mask; a >>>= 1;
                int y = b & mask; b >>>= 1;
                // 当前位并到结果中
                int thisbyte = (x ^ y ^ register) << i;
                result |= thisbyte;
                // 更新进位状态
                int mix = x & y;
                if (mix == 0) { mix = (x | y) & register; }
                register = mix;
            }
            return result;
        }
    }

    /* 不用老老实实一位一位切。一下子用位操作完成 */
    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public int getSum(int a, int b) {
            while (b != 0) {
                // System.out.println("a = " + intToString(a));
                // System.out.println("b = " + intToString(b));
                int carry = a & b;
                // System.out.println("carry = " + intToString(carry));
                a = a ^ b;
                // System.out.println("a becomes = " + intToString(a));
                b = (carry << 1);
            }
            return a;
        }
        private String intToString(int a) {
            char[] digits = new char[32];
            for (int i = 31; i >= 0; i--) {
                digits[i] = (char)((a & 1) + '0');
                a >>>= 1;
            }
            return new String(digits);
        }
        protected void sometest() {
            compare(0);
            compare(3);
            compare(-1);
            compare(100);
            compare(88888);
            compare(1234567);
            compare(-7654321);
            compare(Integer.MIN_VALUE);
            compare(Integer.MAX_VALUE);
        }
        private void compare(int n) {
            System.out.println(n + " = " + intToString(n));
            System.out.println((-n) + " = " + intToString(-n));
            int lastBit = n & (-n);
            System.out.println("Last bit of " + n + ": " + lastBit + " = " + intToString(lastBit) + "\n");
        }
    }
    // you can expand more solutions HERE if you want...

    /* 递归版的32位一起位操作 */
    private class Solution4 extends Solution {
        { super.id = 4; }
        // implement your solution's method HERE...
        public int getSum(int a, int b) {
            if (b == 0) { return a; }
            return getSum(a^b,(a&b)<<1);
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
        private SumOfTwoIntegers problem = new SumOfTwoIntegers();
        private Solution solution = null;

        // call method in solution
        private void call(int a, int b) {
            System.out.println(a + " + " + b + " = " + solution.getSum(a,b));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);


            /** involk call() method HERE */
            // for (int i = 0; i < 5; i++) {
            //     for (int j = 0; j < 5; j++) {
            //         call(i,j);
            //     }
            // }
            // call(Integer.MIN_VALUE,Integer.MAX_VALUE);
            // call(10,-5);
            // call(-10,5);
            // call(Integer.MAX_VALUE,Integer.MAX_VALUE);
            // call(Integer.MIN_VALUE,Integer.MIN_VALUE);
            solution.sometest();
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        test.test(3);
        // test.test(4);
    }
}
