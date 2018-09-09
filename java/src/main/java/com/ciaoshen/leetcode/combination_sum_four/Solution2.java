/**
 * Leetcode - #377 - Combination Sum Four
 */
package com.ciaoshen.leetcode.combination_sum_four;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/** 自顶向下的动态规划  */
class Solution2 implements Solution {
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        localNums = nums;
        dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        return dp(target);
    }
    private int[] dp;
    private int[] localNums;
    private int dp(int remain) {
        if (remain < 0) {
            return 0;
        }
        if (dp[remain] >= 0) {
            return dp[remain];
        }
        int sum = 0;
        for (int i = 0; i < localNums.length; i++) {
            sum += dp(remain - localNums[i]);
        }
        dp[remain] = sum;
        return sum;
    }
}
