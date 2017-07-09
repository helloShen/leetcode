/**
 * Leetcode - Algorithm - Climbing Stairs
 */
package com.ciaoshen.leetcode;
import java.util.*;

class ClimbingStairs {
    public int climbStairs(int n) { // 实际上是个Fibonicci数列
        if (n <= 0) { return 0; } // 防御
        int[] res = new int[n+3];
        res[n+1] = 0; res[n+2] = 1; // 哨兵
        for (int i = n; i >= 0; i--) {
            res[i] = res[i+1] + res[i+2];
        }
        return res[0];
    }
    private static ClimbingStairs test = new ClimbingStairs();
    private static void testClimbStairs(int range) {
        for (int i = 0; i <= range; i++) {
            System.out.println("For " + i + " stairs, possible way = " + test.climbStairs(i));
        }
    }
    public static void main(String[] args) {
        testClimbStairs(10);
    }
}
