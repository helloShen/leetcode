/**
 * Leetcode - #365 - Water and Jub Problem
 */
package com.ciaoshen.leetcode.water_and_jug_problem;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Solution1 {
    public boolean canMeasureWater(int x, int y, int z) {
        if (x < 0 || y < 0 || z < 0 || z > x + y) {
            return false;
        }
        // assert: x,y,c are all valid input
        if (z == 0) { return true; }
        // assert: z != 0
        // special cases: x == 0 || y == 0
        if (x == 0 && y == 0) { return false; } 
        if (x == 0) {
            return y == z;
        } else if (y == 0) {
            return x == z;
        }
        // assert: a > 0 && b > 0 && z > 0
        return (z % gcd(x, y)) == 0;
    }
    // 计算a和b的最大公约数
    // assert: a > 0 && b > 0
    private int gcd(int a, int b) {
        int mod = a % b;
        if (mod == 0) { return b; }
        return gcd(b, mod);
    }

}
