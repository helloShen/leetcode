/**
 * Leetcode - #377 - Combination Sum Four
 */
package com.ciaoshen.leetcode.combination_sum_four;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/** 自底向上的动态规划  */
class Solution3 implements Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                int sub = i - num;
                dp[i] += (sub >= 0)? dp[sub] : 0;
            }
        }
        return dp[target];
    }
}