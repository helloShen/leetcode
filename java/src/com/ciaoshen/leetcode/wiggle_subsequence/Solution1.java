/**
 * Leetcode - #376 - Wiggle Subsequence
 */
package com.ciaoshen.leetcode.wiggle_subsequence;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Solution1 implements Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 1, sign = 0;
        for (int i = 1; i < nums.length; i++) {
            int diff = nums[i] - nums[i-1];
            if ((sign >= 0 && diff < 0) || (sign <= 0 && diff > 0)) {
                count++;
                sign = diff;
            }
        }
        return count;
    }
}