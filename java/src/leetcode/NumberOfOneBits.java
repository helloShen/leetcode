/**
 * Leetcode - Algorithm - NumberOfOneBits
 */
package com.ciaoshen.leetcode;
import java.util.*;

class NumberOfOneBits {
    public class Solution {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int count = 0;
            for (int i = 0; i < 32; i++) {
                count += (n & 1);
                n >>>= 1;
            }
            return count;
        }
    }
    private static NumberOfOneBits test = new NumberOfOneBits();
    private static Solution solution = test.new Solution();
    private static void callHammingWeight(int n) {
        System.out.println("Number " + getBits(n) + " has " + solution.hammingWeight(n) + " 1 bits!");
    }
    private static void test() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            int n = r.nextInt(1000);
            callHammingWeight(n);
        }
    }
    private static String getBits(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            sb.insert(0,n & 1);
            n >>>= 1;
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        test();
    }
}
