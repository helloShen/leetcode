/**
 * Leetcode - maximum_sum_circular_subarray
 */
package com.ciaoshen.leetcode.maximum_sum_circular_subarray;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * This solution WRONG!!
 * 核心理念就是遇到负数段就放弃前面的累加和。
 * 因为连续的负数项也不应该被抛弃，比如：[100, -1, -1, -1, -1, 100, -1, -1, -1, -1]
 *
 * 详细做法：
 * 先把相邻的正数和负数累加合并起来：[0,5,8,-9,9,-7,3,-2] -> [13,-9,9,-7,3,-2]
 *                                                              -9+9   -7+3   -2+13
 *                                                                |      |      |
 * 合并之后，找到成对的负数和正数，求和: [13),(-9,9),(-7,3),(-2] -> [0, 0, 0, -4, 0, 11]
 *
 * 最后再把连续非负项累加起来：[1, 2, 3, -4, 5, 6] -> [6, 5, 3, -4, 11, 6]
 *                                                |  |  |   |   |  |
 *                                            1+2+3 2+3 3  -4  5+6 6
 */
class Solution2 implements Solution {

    public int maxSubarraySumCircular(int[] A) {
        if (A.length == 0) return 0;
        int[][] reduced = reduce(A);
        if (reduced[1][0] <= 0) return reduced[1][0];
        int[] table = pairSum(reduced[0]);
        int max = Integer.MIN_VALUE;
        int start = (reduced[0][0] < 0)? 1 : 0;
        for (int i = start; i < reduced[0].length; i += 2) {
            int curr = reduced[0][i];
            int idx = (i + 1) % table.length;
            // if (log.isDebugEnabled()) {
            //     log.debug("leading head = {}, following tail = {}", curr, table[idx]);
            // }
            if (idx != i && table[idx] > 0) curr += table[idx];
            max = Math.max(max, curr);
        }
        return max;
    }

    // 把相邻的正数和负数累加合并起来：[0,5,8,-9,9,-7,3,-2] -> [13,-9,9,-7,3,-2]
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
        // if (log.isDebugEnabled()) {
        //     log.debug("after reduce() method, array = {}", Arrays.toString(res[0]));
        // }
        return res;
    }

    private boolean isSameSign(int a, int b) {
        return !(a > 0 && b < 0) && !(a < 0 && b > 0);
    }

    /**
     *                                                              -9+9   -7+3   -2+13
     *                                                                |      |      |
     * 合并之后，找到成对的负数和正数，求和: [13),(-9,9),(-7,3),(-2] -> [0, 0, 0, -4, 0, 11]
     *
     * 最后再把连续非负项累加起来：[1, 2, 3, -4, 5, 6] -> [6, 5, 3, -4, 11, 6]
     *                                                |  |  |   |   |  |
     *                                            1+2+3 2+3 3  -4  5+6 6
     */
    private int[] pairSum(int[] nums) {
        // sum pair
        if (nums.length == 1) return nums;
        int[] sum = new int[nums.length];
        int start = (nums[0] > 0)? 1 : 0;
        int p = start;
        while (p + 1 < nums.length) {
            sum[p] = nums[p] + nums[p + 1];
            p += 2;
        }
        if (p == nums.length - 1) sum[p] = nums[p] + nums[0];
        // if (log.isDebugEnabled()) {
        //     log.debug("after pairSum() method, array = {}", Arrays.toString(sum));
        // }
        // accumulate
        int[] res = new int[sum.length];
        int accum = 0;
        int lo = start, hi = lo;
        while (lo < sum.length) {
            // if (log.isDebugEnabled()) {
            //     log.debug("accum lo = {}", lo);
            // }
            if (sum[lo] > 0) {
                if (lo < hi) {
                    // if (log.isDebugEnabled()) {
                    //     log.debug("minus last sum[{}] = {}", lo, sum[lo]);
                    // }
                    accum -= sum[lo - 2];
                    res[lo] = accum;
                } else {
                    accum = 0;
                    hi = lo;
                    while (hi < lo + sum.length - 2) {
                        int idx = hi % sum.length;
                        if (sum[idx] > 0) {
                            accum += sum[idx];
                        } else {
                            break;
                        }
                        hi += 2;
                    }
                    res[lo] = accum;
                }
            } else {
                res[lo] = sum[lo];
            }
            lo += 2;
        }
        // if (log.isDebugEnabled()) {
        //     log.debug("after accum() method, array = {}", Arrays.toString(res));
        // }
        return res;
    }

    // assert: nums must contains positive number
    private int doJob(int[] nums) {
        if (nums.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int start = 0;
        if (nums.length > 1 && nums[0] < 0) start = 1;
        for (int i = start; i < nums.length; i += 2) {
            int sum = nums[i];
            for (int window = i + 2; window < i + nums.length; window += 2) {
                sum += nums[window % nums.length];
                max = Math.max(max, sum);
            }
        }
        return max;
    }

}
