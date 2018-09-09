/**
 * Leetcode - #896 - Monotonic Array
 */
package com.ciaoshen.leetcode.monotonic_array;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Solution1 implements Solution {
    public boolean isMonotonic(int[] A) {
        if (A == null || A.length == 0) {
            return false;
        }
        int sign = 0;
        for (int i = 1; i < A.length; i++) {
            int diff = A[i] - A[i-1];
            if (sign == 0) {
                sign = diff;
            } else if ((sign > 0 && diff < 0) || (sign < 0 && diff > 0)) {
                return false;
            }
        }
        return true;    
    }
}