/**
 * Leetcode - Algorithm - House Robber Two
 */
package com.ciaoshen.leetcode;
import java.util.*;

class HouseRobberTwo {
    public class SolutionV1 {
        public int rob(int[] nums) {
            if (nums.length == 0) { return 0; }
            if (nums.length == 1) { return nums[0]; }
            // assertion: nums.length >= 2
            int[][] memo = new int[nums.length][2];
            memo[nums.length-1][0] = nums[nums.length-1]; // column 0: max if take last element
            memo[nums.length-2][1] = nums[nums.length-2]; // column 1: max if not take last element
            memo[nums.length-2][0] = nums[nums.length-1];
            for (int i = nums.length-3; i >= 0; i--) {
                memo[i][0] = Math.max(memo[i+1][0],memo[i+2][0] + nums[i]);
                memo[i][1] = Math.max(memo[i+1][1],memo[i+2][1] + nums[i]);
                // System.out.println("@" + i + ": memo=[" + memo[i][0] + "," + memo[i][1] + "]");
            }
            return Math.max(memo[0][1],memo[1][0]);
        }
    }
    public class SolutionV2 {
        public int rob(int[] nums) {
            if (nums.length == 0) { return 0; }
            if (nums.length == 1) { return nums[0]; }
            // assertion: nums.length >= 2
            int preTakeLast = nums[nums.length-1], preNotTakeLast = nums[nums.length-2];
            int bPreTakeLast = nums[nums.length-1], bPreNotTakeLast = 0;
            for (int i = nums.length-3; i >= 0; i--) {
                int currTakeLast = Math.max(preTakeLast, bPreTakeLast + nums[i]);
                int currNotTakeLast = Math.max(preNotTakeLast, bPreNotTakeLast + nums[i]);
                bPreTakeLast = preTakeLast; preTakeLast = currTakeLast;
                bPreNotTakeLast = preNotTakeLast; preNotTakeLast = currNotTakeLast;
            }
            return Math.max(preNotTakeLast,bPreTakeLast);
        }
    }
    public class Solution {
        public int rob(int[] nums) {
            if (nums.length == 0) { return 0; }
            if (nums.length == 1) { return nums[0]; }
            return Math.max(noCircleRob(nums,0,nums.length-2), noCircleRob(nums,1,nums.length-1));
        }
        public int noCircleRob(int[] nums, int lo, int hi) {
            if (lo > hi) { return 0; }
            if (lo == hi) { return nums[lo]; }
            // assertion: hi - lo > 0
            int maxPre = Math.max(nums[hi],nums[hi-1]), maxBeforePre = nums[hi];
            for (int i = hi-2; i >= lo; i--) {
                int maxCurr = Math.max(maxPre,maxBeforePre + nums[i]);
                maxBeforePre = maxPre;
                maxPre = maxCurr;
            }
            return maxPre;
        }
    }
    private static HouseRobberTwo test = new HouseRobberTwo();
    private static Solution solution = test.new Solution();
    private static void callRob(int[] nums, String answer) {
        System.out.println("For House: " + Arrays.toString(nums) + ", maximum amount of money is: " + solution.rob(nums) + ",     [answer = " + answer + "]");
    }
    private static void test() {
        int[] nums1 = new int[]{8,5,11,2,23,16};
        int[] nums2 = new int[]{};
        int[] nums3 = new int[]{1,1,1};
        int[] nums4 = new int[]{1,3,1,3,100};
        callRob(nums1,"42");
        callRob(nums2,"0");
        callRob(nums3,"1");
        callRob(nums4,"103");
    }
    public static void main(String[] args) {
        test();
    }
}
