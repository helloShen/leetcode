/**
 * Leetcode - #377 - Combination Sum Four
 */
package com.ciaoshen.leetcode.combination_sum_four;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/** 直观解【超时】 */
class Solution1 implements Solution {
    public int combinationSum4(int[] nums, int target) {
        if (nums == null && nums.length == 0) {
            return 0;
        }
        count = 0;
        localNums = nums;
        helper(target);
        return count;
    }
    private int[] localNums;
    private int count;
    private void helper(int remain) {
        if (remain == 0) {
            count++;
            return;
        } else if (remain > 0) {
            for (int i = 0; i < localNums.length; i++) {
                helper(remain - localNums[i]);
            }
        }
    }
}