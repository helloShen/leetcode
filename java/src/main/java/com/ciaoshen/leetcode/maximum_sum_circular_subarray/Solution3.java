/**
 * Leetcode - maximum_sum_circular_subarray
 */
package com.ciaoshen.leetcode.maximum_sum_circular_subarray;
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
class Solution3 implements Solution {

    public int maxSubarraySumCircular(int[] A) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int maxAccum = 0, minAccum = 0, total = 0;
        for (int n : A) {
            maxAccum += n;
            max = Math.max(max, maxAccum);
            if (maxAccum < 0) maxAccum = 0;
            minAccum += n;
            min = Math.min(min, minAccum);
            if (minAccum > 0) minAccum = 0;
            total += n;
        }
        if (log.isDebugEnabled()) {
            log.debug("max = {}, min = {}, total = {}", max, min, total);
        }
        if (total == min) return max; // all negative
        return Math.max(max, total - min);
    }

}
