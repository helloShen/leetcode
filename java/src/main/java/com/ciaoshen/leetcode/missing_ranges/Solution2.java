/**
 * Leetcode - missing_ranges
 */
package com.ciaoshen.leetcode.missing_ranges;
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

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<>();
        long lo = lower;
        for (int i = 0; i < nums.length && nums[i] <= upper; i++) {
            long hi = nums[i];
            if (hi < lo) continue;
            if (hi == lo) {
                lo++;
                continue;
            }
            list.add(getRange(lo, hi - 1));
            lo = hi + 1;
        }
        if ((long)upper >= lo) list.add(getRange(lo, upper));
        return list;
    }

    // assertion: for [lo, hi], lo <= hi
    private String getRange(long lo, long hi) {
        String range = String.valueOf(lo);
        if (hi > lo) {
            range += "->";
            range += String.valueOf(hi);
        }
        return range;
    }

}
