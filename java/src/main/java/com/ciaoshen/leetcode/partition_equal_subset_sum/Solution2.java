/**
 * Leetcode - partition_equal_subset_sum
 */
package com.ciaoshen.leetcode.partition_equal_subset_sum;
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
class Solution2 implements Solution {

    public boolean canPartition(int[] nums){
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum % 2 == 1) return false;
        int target = sum / 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int i = sum; i > 0; i--) {
                if (i >= num) dp[i] = dp[i] || dp[i - num];
                if (dp[target]) return true;
            }
        }
        return dp[target];
    }

}
