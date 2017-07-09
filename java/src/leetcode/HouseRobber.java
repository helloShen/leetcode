/**
 * Leetcode - Algorithm - House Robber
 */
package com.ciaoshen.leetcode;
import java.util.*;

class HouseRobber {
    /**
     * 动态规划。递归版
     */
    public class SolutionV1 {
        public int rob(int[] nums) {
            int[] memo = new int[nums.length];
            return dp(nums,0,memo);
        }
        public int dp(int[] nums, int cur, int[] memo) {
            // base case
            if (cur >= nums.length) { return 0; }
            if (cur == nums.length - 1) { return nums[nums.length-1]; }
            // dp
            if (memo[cur] > 0) { return memo[cur]; }
            memo[cur] = Math.max(nums[cur] + dp(nums,cur+2,memo), nums[cur+1] + dp(nums,cur+3,memo));
            return memo[cur];
        }
    }
    /**
     * 动态规划。迭代版
     */
    public class SolutionV2 {
        public int rob(int[] nums) {
            if (nums.length == 0) { return 0; }
            int[] memo = new int[nums.length+3];
            memo[nums.length-1] = nums[nums.length-1];
            for (int i = nums.length-2; i >= 0; i--) {
                memo[i] = Math.max(nums[i] + memo[i+2], nums[i+1] + memo[i+3]);
            }
            return memo[0];
        }
    }
    /**
     * 动态规划。迭代版。O(1)额外空间。
     */
    public class Solution {
        public int rob(int[] nums) {
            if (nums.length == 0) { return 0; }
            int robPrevious = 0, notRobPrevious = 0;
            int cur = nums.length-1;
            do {
                int notRobCurrent = Math.max(robPrevious,notRobPrevious);
                int robCurrent = nums[cur--] + notRobPrevious;
                notRobPrevious = notRobCurrent;
                robPrevious = robCurrent;
            } while (cur >= 0);
            return Math.max(robPrevious,notRobPrevious);
        }
    }
    private static HouseRobber test = new HouseRobber();
    private static Solution solution = test.new Solution();
    private static void callRob(int[] nums, String ans) {
        System.out.println("For array: " + Arrays.toString(nums) + ", the optimal solution is: " + solution.rob(nums) + "      >>> [answer should be " + ans + "]");
    }
    private static void test() {
        int[] nums1 = new int[]{8,222,54,6,34};
        int[] nums2 = new int[]{1,1};
        // int[] nums3 = new int[]{ };
        callRob(nums1,"256");
        callRob(nums2,"1");
        // callRob(nums3);
    }
    public static void main(String[] args) {
        test();
    }
}
