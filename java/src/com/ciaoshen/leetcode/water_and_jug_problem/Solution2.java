/**
 * Leetcode - #365 - Water and Jub Problem
 */
package com.ciaoshen.leetcode.water_and_jug_problem;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Solution2 {
    public boolean canMeasureWater(int x, int y, int z) {
        if (x < 0 || y < 0 || z < 0 || z > x + y) {
            return false;
        }
        // assert: x,y,c are all valid input
        if (z == 0) {
            return true;
        }
        // special cases: x == 0 || y == 0 && z > 0
        if (x == 0 || y == 0) { 
            return x + y == z; 
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
