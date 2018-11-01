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
class Solution1 implements Solution {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int count = 0;
        for (int from = 0; from < nums.length; from++) {
            int prod = 1;
            for (int to = from; to < nums.length; to++) {
                prod *= nums[to];
                if (prod >= k) break;
                count++;
            }
        }
        return count;
    }

}
