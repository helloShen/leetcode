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
class Solution1 implements Solution {

    public int maxSubarraySumCircular(int[] A) {
        if (log.isDebugEnabled()) {
            log.debug("Simplified Array = {}", Arrays.toString(reduce(A)[0]));
        }
        int[][] reduced = reduce(A);
        if (reduced[1][0] <= 0) return reduced[1][0];
        return doJob(reduced[0]);
    }

    private int[][] reduce(int[] nums) {
        int[] sums = new int[nums.length];
        int max = Integer.MIN_VALUE;
        int p = 0;
        int i = 0;
        while (i < nums.length) {
            max = Math.max(max, nums[i]);
            int sum = nums[i++];
            while (i < nums.length && isSameSign(sum, nums[i])) {
                max = Math.max(max, nums[i]);
                sum += nums[i++];
            }
            sums[p++] = sum;
        }
        if (p > 1 && isSameSign(sums[0], sums[p - 1])) sums[0] += sums[--p];
        int[][] res = new int[2][];
        res[0] = Arrays.copyOfRange(sums, 0, p);
        res[1] = new int[]{max};
        return res;
    }

    private boolean isSameSign(int a, int b) {
        return !(a > 0 && b < 0) && !(a < 0 && b > 0);
    }

    private int doJob(int[] nums) {
        if (nums.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i, sum = 0; j < i + nums.length; j++) {
                sum += nums[j % nums.length];
                max = Math.max(max, sum);
            }
        }
        return max;
    }

}
