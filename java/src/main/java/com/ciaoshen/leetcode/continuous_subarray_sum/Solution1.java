/**
 * Leetcode - continuous_subarray_sum
 */
package com.ciaoshen.leetcode.continuous_subarray_sum;
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

    public boolean checkSubarraySum(int[] nums, int k) {
        if (checkContinuousZeros(nums)) return true;
        if (k == 0) return false;
        for (int lo = 0; lo < nums.length - 1; lo++) {
            int sum = nums[lo];
            for (int hi = lo + 1; hi < nums.length; hi++) {
                sum += nums[hi];
                if (sum % k == 0) return true;
            }
        }
        return false;
    }

    private boolean checkContinuousZeros(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0 && nums[i + 1] == 0) return true;
        }
        return false;
    }

}
