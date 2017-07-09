/**
 * Leetcode - Algorithm - Happy Number
 */
package com.ciaoshen.leetcode;
import java.util.*;

class HappyNumber {
    public class SolutionV1 {
        public boolean isHappy(int n) {
            Set<Integer> memo = new HashSet<>();
            while (n != 1) {
                int copy = n;
                int sum = 0;
                while (copy != 0) {
                    int remainder = copy % 10;
                    copy /= 10;
                    sum += remainder * remainder;
                }
                if (!memo.add(sum)) { return false; } // find circle
                n = sum;
            }
            return true;
        }
    }
    public class Solution {
        public boolean isHappy(int n) {
            int slow = n, fast = n;
            do {
                slow = squareSum(slow);
                fast = squareSum(fast);
                fast = squareSum(fast);
                if (fast == 1) { return true; }
            } while (slow != fast);
            return false; // find circle, but fast != 1
        }
        public int squareSum(int n) {
            int sum = 0;
            while (n != 0) {
                int remainder = n % 10;
                sum += remainder * remainder;
                n /= 10;
            }
            return sum;
        }
    }
    private static HappyNumber test = new HappyNumber();
    private static Solution solution = test.new Solution();
    private static void callIsHappy(int n, String ans) {
        System.out.println("Is " + n + " a Happy Number? " + solution.isHappy(n) + "    [answer should be " + ans + "]");
    }
    private static void test() {
        callIsHappy(19,"true");
        callIsHappy(1,"true");
        callIsHappy(2,"false");
    }
    public static void main(String[] args) {
        test();
    }
}
