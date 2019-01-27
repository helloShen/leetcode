/**
 * Leetcode - valide_triangle_number
 */
package com.ciaoshen.leetcode.valide_triangle_number;
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

    public int triangleNumber(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        if (log.isDebugEnabled()) {
            log.debug("sorted array = {}", Arrays.toString(nums));
        }
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                int max = nums[i] + nums[j];
                if (log.isDebugEnabled()) {
                    log.debug("x = {}, y = {}, max = {}", nums[i], nums[j], max);
                }
                int searchResult = Arrays.binarySearch(nums, max);
                if (log.isDebugEnabled()) {
                    log.debug("searchResult = {}", searchResult);
                }
                int maxIdx = (searchResult < 0)? - (searchResult + 1) : searchResult;
                if (maxIdx < nums.length) {
                    while (maxIdx > 0 && nums[maxIdx - 1] == nums[maxIdx]) maxIdx--;
                }
                if (log.isDebugEnabled()) {
                    log.debug("maxIdx = {}", maxIdx);
                }
                int diff = maxIdx - (j + 1);
                if (diff > 0) count += diff;
            }
        }
        return count;
    }

}
