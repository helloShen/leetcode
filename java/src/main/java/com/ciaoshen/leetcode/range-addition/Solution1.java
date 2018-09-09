/**
 * Leetcode - #370 - Range Addition
 */
package com.ciaoshen.leetcode.range_addition;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

interface Solution1 implements Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        if (length <= 0) {
            return null;
        }
        int[] res = new int[length];
        // assert: numbers in updates are legal
        for (int[] update : updates) {
            int num = update[2];
            for (int i = update[0]; i <= update[1]; i++) {
                res[i] += num;
            }
        }
        return res;
    }
}
