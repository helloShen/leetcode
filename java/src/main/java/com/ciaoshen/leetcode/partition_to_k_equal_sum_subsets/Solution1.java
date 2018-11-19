/**
 * Leetcode - partition_to_k_equal_sum_subsets
 */
package com.ciaoshen.leetcode.partition_to_k_equal_sum_subsets;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * log instance is defined in Solution interface
 * this is how slf4j will work in this class:
 * =============================================
 *     if (log.isDebugEnabled()) {
 *         log.debug("a + b = {}", sum);
 *     }
 * =============================================
 */
class Solution1 implements Solution {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum % k != 0) return false;
        int targetSum = sum / k;
        localNums = nums;
        taken = new boolean[nums.length];
        Arrays.sort(localNums);
        int idx = nums.length - 1;
        while (true) {
            while (idx >= 0 && taken[idx]) idx--;
            if (idx < 0) break;
            if (!backtracking(targetSum, idx)) return false;
        }
        return true;
    }

    private int[] localNums;
    private boolean[] taken;

    private boolean backtracking(int sum, int idx) {
        if (log.isDebugEnabled()) {
            if (sum == 0) {
                log.debug("idx = {}, sum == 0. Find 1 path!", idx);
            } else {
                if (idx < 0) log.debug("idx = {} < 0. kill this path!", idx);
                if (sum < 0) log.debug("sum = {} < 0, idx = {}, kill this path!", sum, idx);
                if (taken[idx]) log.debug("number at idx {} is taken, sum = {}, kill this path!", idx, sum);
            }
        }
        if (sum == 0) return true;
        if (idx < 0 || sum < 0 || taken[idx]) return false;
        int remain = sum - localNums[idx];
        taken[idx] = true;
        if (log.isDebugEnabled()) {
            log.debug("Take {}@{}, remain {}, go to deeper recursion!", localNums[idx], remain, idx);
        }
        if (remain == 0 && idx == 0) return true;
        for (int i = idx - 1; i >= 0; i--) {
            if (backtracking(remain, i)) return true;
        }
        taken[idx] = false;
        return false;
    }

}
