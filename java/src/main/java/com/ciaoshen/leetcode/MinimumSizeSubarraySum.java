/**
 * Leetcode - Algorithm - Minimum Size Subarray Sum
 */
package com.ciaoshen.leetcode;
import java.util.*;

class MinimumSizeSubarraySum {
    /**
     * Two Pointers
     */
    public class SolutionV1 {
        public int minSubArrayLen(int s, int[] nums) {
            int len = 0, minLen = 0;
            int sum = 0;
            for (int slow = 0, fast = 0; fast < nums.length; fast++) {
                sum+= nums[fast];
                len++;
                if (sum >= s) {
                    minLen = (minLen == 0)? len : Math.min(len,minLen);
                    while (slow <= fast) {
                        int forward = sum - nums[slow];
                        if (forward >= s) {
                            slow++;
                            len--;
                            sum = forward;
                        } else {
                            break;
                        }
                    }
                    minLen = Math.min(minLen,len);
                }
            }
            return minLen;
        }
    }
    /**
     * Dynamic Programming O(m*n), where m is the size of the min window
     */
    public class SolutionV2 {
        public int minSubArrayLen(int s, int[] nums) {
            int minLen = 0, sum = 0;
            for (int i = nums.length-1; i >= 0; i--) {
                if (minLen == 0) { // sum < target number
                    sum += nums[i];
                    if (sum >= s) {
                        int newTail = nums.length;
                        do {
                            sum -= nums[--newTail];
                        } while (sum >= s);
                        minLen = newTail - i + 1;
                        // System.out.println("First window is [" + i + "," + newTail + "]"); }
                    }
                } else {
                    int localSum = 0;
                    for (int j = i; j < i+minLen-1; j++) {
                        localSum += nums[j];
                        if (localSum >= s) { minLen = j-i+1; }
                    }
                }
            }
            return minLen;
        }
    }
    /**
     * Divide and Conquer O(nlogn)
     */
    public class SolutionV3 {
        public int minSubArrayLen(int s, int[] nums) {
            if (nums.length == 0) { return 0; }
            return recursive(s,nums,0,nums.length-1);
        }
        public int recursive(int s, int[] nums, int lo, int hi) {
            if (lo == hi) {
                // if (nums[lo] >= s) {
                //     System.out.println("Base case: [lo = " + lo + ", hi = " + hi + ", minLen = " + 1 + "]");
                // } else {
                //     System.out.println("Base case: [lo = " + lo + ", hi = " + hi + ", minLen = " + 0 + "]");
                // }
                return (nums[lo] >= s)? 1 : 0;
            }
            int mid = lo + (hi - lo) / 2;
            int left = recursive(s,nums,lo,mid);
            int right = recursive(s,nums,mid+1,hi);
            int minLen = Math.min(left,right);
            if (left == 0) { minLen = right; }
            if (right == 0) { minLen = left; }
            // 中间合并部分
            int l = mid, r = mid+1, middleSize = 2, limit = (minLen == 0)? Integer.MAX_VALUE : minLen;
            int middleSum = nums[l] + nums[r];
            middleSize = middleRecursion(s,nums,lo,hi,mid,mid+1,limit,middleSum);
            int ret = Math.min(minLen,middleSize);
            if (minLen == 0) { ret = middleSize; }
            if (middleSize == 0) { ret = minLen; }
            return ret;
        }
        public int middleRecursion(int s, int[] nums, int lo, int hi, int l, int r, int limit, int localSum) {
            int size = r-l+1;
            if (localSum >= s) { return size; }
            int minLeft = 0, minRight = 0;
            if (l > 0 && l > lo && size < limit) { minLeft = middleRecursion(s,nums,lo,hi,l-1,r,limit,localSum+nums[l-1]); }
            if (r < nums.length && r < hi && size < limit) { minRight = middleRecursion(s,nums,lo,hi,l,r+1,limit,localSum+nums[r+1]); }
            int minLen = Math.min(minLeft,minRight);
            if (minLeft == 0) { minLen = minRight; }
            if (minRight == 0) { minLen = minLeft; }
            return minLen;
        }
    }
    public class Solution {
        public int minSubArrayLen(int s, int[] nums) {
            // cumulate sum
            int[] cumulate = new int[nums.length+1];
            for (int i = 0; i < nums.length; i++) {
                cumulate[i+1] = cumulate[i] + nums[i];
            }
            // 计算从每个节点开始的最小窗口
            int minLen = 0, len = 0;
            for (int i = 1; i <= nums.length; i++) {
                int lo = i, hi = nums.length, pre = i-1;
                while (lo <= hi) {
                    int mid = lo + (hi - lo) / 2;
                    int diff = cumulate[mid] - cumulate[pre];
                    if (diff < s) {
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
                len = (lo > nums.length)? 0 : lo - pre;
                if (len > 0) {
                    minLen = (minLen == 0)? len : Math.min(minLen,len);
                }
            }
            return minLen;
        }
    }
    private static MinimumSizeSubarraySum test = new MinimumSizeSubarraySum();
    private static Solution solution = test.new Solution();
    private static void callMinSubArrayLen(int s, int[] nums, String ant) {
        System.out.println("Numbers Array is: " + Arrays.toString(nums));
        System.out.println("Target sum is: " + s);
        System.out.println("The minimum size is: " + solution.minSubArrayLen(s,nums) + "    [answer is " + ant + "]");
    }
    private static void test() {
        int[] nums1 = new int[]{2,3,1,2,4,3};
        int s1 = 7;
        int[] nums2 = new int[0];
        int s2 = 3;
        int[] nums3 = new int[]{10,5,13,4,8,4,5,11,14,9,16,10,20,8};
        int s3 = 80;
        int[] nums4 = new int[]{1,4,4};
        int s4 = 4;
        int[] nums5 = new int[]{12,28,83,4,25,26,25,2,25,25,25,12};
        int s5 = 213;
        callMinSubArrayLen(s1,nums1,"2");
        callMinSubArrayLen(s2,nums2,"0");
        callMinSubArrayLen(s3,nums3,"6");
        callMinSubArrayLen(s4,nums4,"1");
        callMinSubArrayLen(s5,nums5,"8");
    }
    public static void main(String[] args) {
        test();
    }
}
