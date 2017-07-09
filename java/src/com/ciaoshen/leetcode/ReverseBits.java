/**
 * Leetcode - Algorithm - Reverse Bits
 */
package com.ciaoshen.leetcode;
import java.util.*;

class ReverseBits {
    public class SolutionV1 {
        // you need treat n as an unsigned value
        public int reverseBits(int n) {
            int reverse = 0;
            for (int i = 0; i < 32; i++) {
                int bit = n & 1;
                reverse |= bit << (31 - i);
                n = n >> 1;
            }
            return reverse;
        }
    }
    public class SolutionV2 {
        public int reverseBits(int n) {
            int reverse = 0;
            for (int i = 0; i < 32; i++) {
                int bit = n & 1;
                reverse <<= 1;
                reverse |= bit;
                n >>>= 1;
            }
            return reverse;
        }
    }
    public class SolutionV3 {
        public int reverseBits(int n) {
            int reverse = 0;
            for (int i = 0; i < 32; i++) {
                if (n == 0) { reverse <<= (32 - i); break; }
                int bit = n & 1;
                reverse <<= 1;
                reverse |= bit;
                n >>>= 1;
            }
            return reverse;
        }
    }
    public class SolutionV4 {
        public int reverseBits(int n) {
            int reverse = 0;
            for (int i = 0; i < 16; i++) {
                if (n == 0) { reverse <<= (32 - 2 * i); break; }
                int bit = n & 3;
                reverse <<= 2;
                if (bit == 2) {
                    reverse |= 1;
                } else if (bit == 1) {
                    reverse |= 2;
                } else {
                    reverse |= bit;
                }
                n >>>= 2;
            }
            return reverse;
        }
    }
    public class SolutionV5 {
        private final int[] MAP = new int[]{ 0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15 };
        public int reverseBits(int n) {
            int reverse = 0;
            for (int i = 0; i < 8; i++) {
                int bit = n & 15;
                reverse <<= 4;
                reverse |= MAP[bit];
                n >>>= 4;
            }
            return reverse;
        }
    }
    public class SolutionV6 {
        public int reverseBits(int n) {
            int ret=n;
            ret = ret >>> 16 | ret<<16;
            ret = (ret & 0xff00ff00) >>> 8 | (ret & 0x00ff00ff) << 8;
            ret = (ret & 0xf0f0f0f0) >>> 4 | (ret & 0x0f0f0f0f) << 4;
            ret = (ret & 0xcccccccc) >>> 2 | (ret & 0x33333333) << 2;
            ret = (ret & 0xaaaaaaaa) >>> 1 | (ret & 0x55555555) << 1;
            return ret;
        }
    }
    private static ReverseBits test = new ReverseBits();
    private static Solution solution = test.new Solution();
    private static void printBits(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            sb.insert(0,n & 1);
            n = n >> 1;
        }
        System.out.println(sb.toString());
    }
    private static String getBits(int n) {
        return recursion(n,32);
    }
    private static String recursion(int n, int remain) {
        if (remain == 0) { return ""; }
        return recursion(n >> 1, remain-1) + (n & 1);
    }
    private static void testPrintBits() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            int num = r.nextInt(100) + 1;
            System.out.print("Number " + num + ": ");
            printBits(num);
        }
    }
    private static void testGetBits() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            int num = r.nextInt(100) + 1;
            System.out.println("Number " + num + ": " + getBits(num));
        }
    }
    private static void callReverseBits(int n) {
        System.out.println("Reverse of " + n + " ["+ getBits(n) + "] is: ");
        int reverse = solution.reverseBits(n);
        System.out.println("    >>> " + reverse + " ["+getBits(reverse) + "]");
    }
    private static void test() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            int num = r.nextInt(1000)+1;
            callReverseBits(num);
        }
    }
    public static void main(String[] args) {
        // testPrintBits();
        // testGetBits();
        test();
    }
}
