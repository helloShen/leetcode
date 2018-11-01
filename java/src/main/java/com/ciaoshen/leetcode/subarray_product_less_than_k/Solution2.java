/**
 * Leetcode - subarray_product_less_than_k
 */
package com.ciaoshen.leetcode.subarray_product_less_than_k;
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

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k < 1) return 0;
        int lo = 0, hi = 0, prod = 1, count = 0;
        while (lo < nums.length) {
            if (lo < hi) {
                count += (hi - lo);
            } else {
                prod = 1;
                hi = lo;
            }
            while (hi < nums.length && prod * nums[hi] < k) {
                prod *= nums[hi++];
                count++;
            }
            prod /= nums[lo++];
        }
        return count;
    }

}
