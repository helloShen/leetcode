/**
 * Leetcode - longest_continous_increasing_subsequence
 */
package com.ciaoshen.leetcode.longest_continous_increasing_subsequence;
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

    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) return 0;
        int maxCount = 1;
        for (int lo = 0, hi = 1; lo < nums.length; lo = hi, hi = lo + 1) {
            while (hi < nums.length && nums[hi] > nums[hi - 1]) hi++;
            // if (log.isDebugEnabled()) {
            //     log.debug("lo = {}, hi = {}", lo, hi);
            // }
            maxCount = Math.max(maxCount, hi - lo);
        }
        return maxCount;
    }

}
