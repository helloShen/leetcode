/**
 * Leetcode - Algorithm - BitwiseAndOfNumbersRange
 */
package com.ciaoshen.leetcode;
import java.util.*;

class BitwiseAndOfNumbersRange {
    /**
     * 一位一位切
     */
    public class SolutionV1 {
        public int rangeBitwiseAnd(int m, int n) {
            int ret = 0;
            int mask = 1 << 30;
            for (int i = 1; i < 32; i++) {
                int mBit = m & mask;
                int nBit = n & mask;
                if ((mBit == 0 && nBit == mask) || (mBit == mask && nBit == 0)) { return ret; }
                if (mBit == mask && nBit == mask) { ret |= mask; } // 开头的连续的1
                mask >>>= 1;
            }
            return ret;
        }
    }
    /**
     * 往右推出去，再往左推回来
     */
    public class SolutionV2 {
        public int rangeBitwiseAnd(int m, int n) {
            int step = 0;
            while (m != n) {
                m >>= 1;
                n >>= 1;
                step++;
            }
            return n << step;
        }
    }
    /**
     * 从右往左，不需要的位，一位位挖空
     */
    public class Solution {
        public int rangeBitwiseAnd(int m, int n) {
            while (n > m) {
                n &= (n-1);
            }
            return m & n;
        }
    }
    private static BitwiseAndOfNumbersRange test = new BitwiseAndOfNumbersRange();
    private static Solution solution = test.new Solution();
    private static void callRangeBitwiseAnd(int m, int n) {
        int small = Math.min(m,n);
        int large = Math.max(m,n);
        System.out.println("From    [" + getBits(small) + "]");
        System.out.println("To      [" + getBits(large) + "]");
        System.out.println("    >>>     Result  [" + solution.rangeBitwiseAnd(small,large) + "]");
        System.out.println("\n\n");
    }
    private static void test() {
        int max = 10000;
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            callRangeBitwiseAnd(r.nextInt(max)+1,r.nextInt(max)+1);
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
