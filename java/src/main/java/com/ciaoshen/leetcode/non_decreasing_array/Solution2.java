/**
 * Leetcode - non_decreasing_array
 */
package com.ciaoshen.leetcode.non_decreasing_array;
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

    public boolean checkPossibility(int[] nums) {
        boolean decreased = false;
        int min = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) return false;
            if (decreased) min = nums[i];
            if (nums[i] < nums[i - 1]) {
                decreased = true;
                if (i == 1 || (i > 1 && nums[i] >= nums[i - 2])) {
                    min = nums[i];
                } else {
                    min = nums[i - 1];
                }
            }
        }
        return true;
    }

}
