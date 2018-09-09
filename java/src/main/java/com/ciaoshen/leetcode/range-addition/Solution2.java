/**
 * Leetcode - #370 - Range Addition
 */
package com.ciaoshen.leetcode.range_addition;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

interface Solution2 implements Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        if (length <= 0) {
            return null;
        }
        int[] res = new int[length];
        for (int[] update : updates) {
            res[update[0]] += update[2];
            if (update[1] + 1 < res.length) {
                res[update[1]+1] -= update[2];
            }
        }
        for (int i = 1; i < res.length; i++) {
            res[i] += res[i-1];
        }
        return res;
    }
}
