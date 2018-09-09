/**
 * Leetcode - Algorithm - Unique Paths
 */
package com.ciaoshen.leetcode;
import java.util.*;
import java.math.*;

class UniquePaths {
    public int uniquePathsV1(int m, int n) {
        if (m <= 0 || n <= 0) { return 0; }
        if (m == 1 || n == 1) { return 1; }
        int big = Math.max(m-1,n-1);
        int small = Math.min(m-1,n-1);
        BigDecimal numerator = fromTo(big+1,big+small);
        BigDecimal denominator = factorial(small);
        return numerator.divide(denominator).intValue();
    }
    //左右闭区间，例如from=8, to=8. 就等于8.
    public BigDecimal fromTo(int from, int to) {
        BigDecimal small = new BigDecimal(from);
        BigDecimal big = new BigDecimal(to);
        BigDecimal res = new BigDecimal(1);
        for (int i = from; i <= to; i++) {
            res = res.multiply(new BigDecimal(i));
        }
        return res;
    }
    // long范围太小，差不多20!的阶乘就超过long的取值范围
    public BigDecimal factorial(int n) {
        BigDecimal fac = new BigDecimal(1);
        for (int i = 1; i <= n; i++) {
            fac = fac.multiply(new BigDecimal(i));
        }
        return fac;
    }
    public int uniquePathsV2(int m, int n) {
        if (m <= 0 || n <= 0) { return 0; }
        if (m == 1 || n == 1) { return 1; }
        int big = Math.max(m-1,n-1);
        int small = Math.min(m-1,n-1);
        BigDecimal res = new BigDecimal(1);
        for (int i = 1; i <= small; i++) {
            res = res.multiply(new BigDecimal(big+i)).divide(new BigDecimal(i));
        }
        return res.intValue();
    }
    public int uniquePathsV3(int m, int n) {
        if (m <= 0 || n <= 0) { return 0; }
        if (m == 1 || n == 1) { return 1; }
        int big = Math.max(m-1,n-1);
        int small = Math.min(m-1,n-1);
        long res = 1;
        for (int i = 1; i <= small; i++) {
            res = res * (big+i) / i;
        }
        return (int)res;
    }
    public int uniquePathsV4(int m, int n) {
        if (m <= 0 || n <= 0) { return 0; }
        int[] count = new int[]{ 0 };
        recursive(m,n,count);
        return count[0];
    }
    public void recursive(int m, int n, int[] count) {
        if (m == 1 && n == 1) { count[0]++; } // reach the end
        if (m > 1) { recursive(m-1, n, count); }
        if (n > 1) { recursive(m,n-1,count); }
    }
    public int uniquePathsV5(int m, int n) {
        if (m <= 0 || n <= 0) { return 0; }
        int[][] memo = new int[m+1][n+1];
        return dp(m,n,memo);
    }
    public int dp(int m, int n, int[][] memo) {
        if (memo[m][n] != 0) { return memo[m][n]; }
        if (m == 1 && n == 1) {
            memo[1][1] = 1; return 1;
        }
        int rightCount = 0, downCount = 0;
        if (m > 1) { rightCount = dp(m-1,n,memo); }
        if (n > 1) { downCount = dp(m,n-1,memo); }
        memo[m][n] = rightCount + downCount;
        return memo[m][n];
    }
    private static UniquePaths test = new UniquePaths();
    private static void testFactorial() {
        for (int i = 1; i <= 100; i++) {
            System.out.println("Factorial of " + i + " is: " + test.factorial(i));
        }
    }
    private static void testFromTo() {
        int[][] pairs = new int[][] {
            {2,2},
            {2,4},
            {8,8},
            {6,8},
            {9,8}
        };
        for (int[] pair : pairs) {
            System.out.println("From " + pair[0] + ", To " + pair[1] + " = " + test.fromTo(pair[0],pair[1]));
        }
    }
    private static void testUniquePaths() {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 100; j++) {
                System.out.println(i + " x " + j + " = " + test.uniquePaths(i,j));
            }
        }
        System.out.println("100 x 1" + " = " + test.uniquePaths(100,1));
    }
    public static void main(String[] args) {
        testUniquePaths();
        //testFactorial();
        //testFromTo();
    }
}
