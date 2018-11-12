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
class Solution2 implements Solution {

    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> prevMod = new HashMap<>();
        prevMod.put(0, -1);
        for (int i = 0, sum = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) sum %= k;
            if (log.isDebugEnabled()) {
                log.debug("mod[{}] = {}", i, sum);
            }
            Integer prevIndex = prevMod.get(sum);
            if (prevIndex != null) {
                if (i - prevIndex > 1) return true;
            } else {
                prevMod.put(sum, i);
            }
        }
        return false;
    }

}
