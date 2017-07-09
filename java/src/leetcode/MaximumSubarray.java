/**
 * Leetcode - Algorithm - Maximum Subarray
 */
package com.ciaoshen.leetcode;
import java.util.*;

class MaximumSubarray {

    // {-2,1,-3,4,-1,2,1,-5,4}
    public int maxSubArrayV1(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum > max) { max = sum; }
            }
        }
        return max;
    }
    public int maxSubArrayV2(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; ) {
            int cursor = i, temp = 0;
            while (cursor < nums.length) {
                temp += nums[cursor++];
                max = Math.max(max,temp);
                if (temp < 0) { break; }
            }
            i = cursor;
        }
        return max;
    }
    public int maxSubArrayV3(int[] nums) {
        int heritage = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int assets = heritage + nums[i];
            max = Math.max(max,assets);
            heritage = Math.max(assets,0); //遗产为负，就是累赘，还不如没有
        }
        return max;
    }
    public int maxSubArrayV4(int[] nums) {
        int[] max = new int[]{ Integer.MIN_VALUE };
        dp(0,max,nums,0);
        return max[0];
    }
    public void dp(int heritage, int[] max, int[] nums, int cursor) {
        if (cursor == nums.length) { return; }
        int assets = heritage + nums[cursor];
        max[0] = Math.max(max[0],assets);
        heritage = Math.max(assets,0);
        dp(heritage,max,nums,++cursor);
    }
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) { return 0; }
        return dac(nums,0,nums.length-1);
    }
    public int dac(int[] nums, int low, int high) {
        if (low == high) { return nums[low]; }
        int mid = low + (high - low) / 2; // 下位中位数
        int left = dac(nums,low,mid); // 左半边最大值
        int right = dac(nums,mid+1,high); // 右半边最大值
        int candidate1 = Math.max(left,right);
        int kernel = nums[mid] + nums[mid+1];
        int candidate2 = fromMiddle(nums,low,high,mid,mid+1,kernel,kernel); //中间合并区的最大值
        //System.out.println("From " + low + " to " + high + "    >>> Left = " + left + ", Right = " + right + ", Mid = " + candidate2);
        return Math.max(candidate1,candidate2);
    }
    public int fromMiddle(int[] nums, int left, int right, int low, int high, int soFar, int max) {
        if (low == left && high == right) { return max; }
        int leftMax = Integer.MIN_VALUE;
        if (left < low) {
            int newSoFar = soFar+nums[low-1];
            int newMax = Math.max(max, newSoFar);
            leftMax = fromMiddle(nums,left,right,low-1,high,newSoFar,newMax);
        }
        int rightMax = Integer.MIN_VALUE;
        if (right > high) {
            int newSoFar = soFar+nums[high+1];
            int newMax = Math.max(max,newSoFar);
            rightMax = fromMiddle(nums,left,right,low,high+1,newSoFar,newMax);
        }
        return Math.max(leftMax,rightMax);
    }
    private static void testMaxSubArray() {
        MaximumSubarray test = new MaximumSubarray();
        int[][] testArrays = new int[][] {
            {-1,-2,-3,-4,-5},
            {-2,1,-3,4,-1,2,1,-5,4},
            {-2,3,0,2,-2,3}
        };
        for (int i = 0; i < testArrays.length; i++) {
            System.out.println(Arrays.toString(testArrays[i]) + ": " + test.maxSubArray(testArrays[i]));
        }
    }
    public static void main(String[] args) {
        testMaxSubArray();
    }
}
