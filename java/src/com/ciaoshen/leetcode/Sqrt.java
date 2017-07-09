/**
 * Leetcode - Algorithm - Sqrt
 */
package com.ciaoshen.leetcode;
import java.util.*;

class Sqrt {
    public int mySqrtV1(int x) {
        if (x <= 0) { return 0; }
        int lo = 1, hi = x;
        while (lo < hi) {
            int mid = lo + ( (hi - lo + 1) / 2 ); // 注意！取上位中位数
            //System.out.println("low = " + lo + ", high = " + hi + ", mid = " + mid);
            long product = (long)mid * mid;
            //System.out.println(mid + " * " + mid + " = " + product);
            if (product > (long)x) {
                hi = mid - 1;
            } else if (product < (long)x) {
                lo = mid;
            } else { // product == x
                return mid;
            }
        }
        return lo;
    }
    public int mySqrt(int x) {
        if (x <= 0) { return 0; }
        return recursive(x,1,x);
    }
    public int recursive(int x, int lo, int hi) {
        if (lo == hi) { return lo; }
        int mid = lo + ( (hi - lo + 1) / 2);
        long product = (long)mid * mid;
        if (product > (long)x) {
            return recursive(x,lo,mid-1);
        } else if (product < (long)x) {
            return recursive(x,mid,hi);
        } else {
            return mid;
        }
    }
    private static Sqrt test = new Sqrt();
    private static int[] testCases = new int[]{ -100, 0, 1, 2, 1000000, 9999 };
    private static void testMySqrt() {
        for (int x : testCases) {
            System.out.println("Squart of " + x + " = " + test.mySqrt(x));
        }
    }
    public static void main(String[] args) {
        testMySqrt();
    }
}
