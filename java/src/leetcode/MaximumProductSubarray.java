/**
 * Leetcode - Algorithm - Maximum Product Subarray
 */
package com.ciaoshen.leetcode;
import java.util.*;

class MaximumProductSubarray {
    public class SolutionV1 {
        public int maxProduct(int[] nums) {
            if (nums.length == 0) { return 0; }
            int max = Integer.MIN_VALUE;
            int[][] memo = new int[nums.length][nums.length];
            for (int i = nums.length-1; i >= 0; i--) {
                for (int j = nums.length-1; j > i; j--) {
                    memo[i][j] = nums[i] * memo[i+1][j];
                    max = Math.max(max,memo[i][j]);
                }
                memo[i][i] = nums[i];
                max = Math.max(max,memo[i][i]);
            }
            return max;
        }
    }
    public class Solution {
        public int maxProduct(int[] nums) {
            if (nums.length == 0) { return 0; }
            int maxRes = nums[nums.length-1]; // 最终结果
            int maxSub = maxRes, minSub = maxRes;
            for (int i = nums.length-2; i >= 0; i--) {
                int val = nums[i];
                if (val >= 0) {
                    maxSub = Math.max(val,val*maxSub);
                    minSub = Math.min(val,val*minSub);
                } else if (val < 0) {
                    int tempMax = Math.max(val,val*minSub);
                    minSub = Math.min(val,val*maxSub);
                    maxSub = tempMax;
                }
                maxRes = Math.max(maxRes,maxSub);
            }
            return maxRes;
        }
    }
    private static MaximumProductSubarray test = new MaximumProductSubarray();
    private static Solution solution = test.new Solution();
    private static void testMaxProduct(int[] nums) {
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Product = " + solution.maxProduct(nums));
    }
    public static void main(String[] args) {
        int[] nums1 = new int[]{2,3,-2,4};
        int[] nums2 = new int[]{-4,-3,-2};
        testMaxProduct(nums1);
        testMaxProduct(nums2);
    }
}
