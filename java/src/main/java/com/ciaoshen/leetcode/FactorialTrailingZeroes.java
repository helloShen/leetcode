/**
 * Leetcode - Algorithm - Factorial Trailing Zeroes
 */
package com.ciaoshen.leetcode;
import java.util.*;

class FactorialTrailingZeroes {
    public class SolutionV1 {
        public int trailingZeroes(int n) {
            int ret = 0;
            int lev = 1;
            while (true) {
                int fivePower = (int)Math.pow(5,lev);
                if (n >= fivePower) {
                    ret += n / fivePower;
                } else {
                    break;
                }
                lev++;
            }
            return ret;
        }
    }
    public class SolutionV2 {
        public int trailingZeroes(int n) {
            int ret = 0;
            long fivePower = 1l;
            while (true) {
                fivePower = fivePower * 5;
                if (n >= fivePower) {
                    ret += n / fivePower;
                } else {
                    break;
                }
            }
            return ret;
        }
    }
    public class Solution {
        public int trailingZeroes(int n) {
            int ret = 0;
            while (n >= 5) {
                ret += n / 5;
                n /= 5;
            }
            return ret;
        }
    }
    public class SolutionV4 {
        public int trailingZeroes(int n) {
            return (n == 0)? 0 : n / 5 + trailingZeroes(n/5);
        }
    }
    private static FactorialTrailingZeroes test = new FactorialTrailingZeroes();
    private static Solution solution = test.new Solution();
    private static void callTrailingZeroes(int n) {
        System.out.println("The trailing zeros of " + n + "! is: " + solution.trailingZeroes(n));
    }
    private static void test() {
        for (int i = 0; i < 100; i++) {
            callTrailingZeroes(i);
        }
    }
    public static void main(String[] args) {
        test();
    }
}
