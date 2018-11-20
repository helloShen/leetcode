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
class Solution1 implements Solution {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<>();
        if (lower > upper) return list;
        String range = "";
        if (nums.length == 0) {
            list.add(toRange(lower, upper));
            return list;
        }
        int loIdx = Arrays.binarySearch(nums, lower);
        if (loIdx < 0) loIdx = -(loIdx + 1);
        int hiIdx = Arrays.binarySearch(nums, upper);
        if (hiIdx < 0) hiIdx = -(hiIdx + 1) - 1;
        if (loIdx <= hiIdx) {
            int leftRange = 0, rightRange = 0;
            if (nums[loIdx] != Integer.MIN_VALUE) {
                leftRange = lower;
                rightRange = nums[loIdx] - 1;
                range = toRange(leftRange, rightRange);
                if (!range.isEmpty()) list.add(range);
            }
            while (loIdx < hiIdx) {
                if (nums[loIdx + 1] != Integer.MIN_VALUE) {
                    leftRange = nums[loIdx] + 1;
                    rightRange = nums[loIdx + 1] - 1;
                    range = toRange(leftRange, rightRange);
                    if (!range.isEmpty()) list.add(range);
                }
                loIdx++;
            }
            if (nums[hiIdx] != Integer.MAX_VALUE) {
                leftRange = nums[hiIdx] + 1;
                rightRange = upper;
                range = toRange(leftRange, rightRange);
                if (!range.isEmpty()) list.add(range);
            }
        }
        return list;
    }

    private String toRange(int left, int right) {
        String range = "";
        if (left <= right) {
            range = String.valueOf(left);
            if (left < right) {
                range += "->";
                range += String.valueOf(right);
            }
        }
        return range;
    }

}
