/**
 * Leetcode - Algorithm - Power of Two
 */
package com.ciaoshen.leetcode;
import java.util.*;

class PowerOfTwo {
    /** Straightforward Bit manipulation */
    public class SolutionV1 {
        public boolean isPowerOfTwo(int n) {
            if (n <= 0) { return false; }
            while ((n & 1) == 0) { n >>>= 1; } // eliminate 0s in the end
            n >>>= 1; // remove the first 1
            return n == 0;
        }
    }
    /** Use Integer.bitCount() library method */
    public class SolutionV2 {
        public boolean isPowerOfTwo(int n) {
            return (n > 0) && (Integer.bitCount(n) == 1);
        }
    }
    /** Check if (n & (n-1)) == 0 */
    public class Solution {
        public boolean isPowerOfTwo(int n) {
            return (n > 0) && ( (n & (n-1)) == 0 );
        }
    }
    private class Test {
        private void callIsPowerOfTwo(int n) {
            System.out.println((solution.isPowerOfTwo(n))? n+"\tis power of two!" : n + "\tis not power of two!");
        }
        private void test() {
            int max = 100;
            Random r = new Random();
            for (int i = 0; i < 10; i++) {
                callIsPowerOfTwo(r.nextInt(max)+1);
            }
            for (int i = 0, twoPower = 1; i < 10; i++, twoPower *= 2) {
                callIsPowerOfTwo(twoPower);
            }
        }
    }
    private static PowerOfTwo problem = new PowerOfTwo();
    private static Solution solution = problem.new Solution();
    private static Test test = problem.new Test();
    public static void main(String[] args) {
        test.test();
    }
}
