/**
 * Leetcode - Algorithm - Find Peek Element
 */
package com.ciaoshen.leetcode;
import java.util.*;

class FindPeakElement {
    public class SolutionV1 {
        public int findPeakElement(int[] nums) {
            if (nums.length < 2) { return 0; }
            for (int i = 0; i < nums.length; i++) {
                if (i == 0) {
                    if (nums[i] > nums[i+1]) { return 0; }
                } else if (i == nums.length-1) {
                    if (nums[i-1] < nums[i]) { return i; }
                } else {
                    if (nums[i-1] < nums[i] && nums[i] > nums[i+1]) { return i; }
                }
            }
            return -1;
        }
    }
    public class SolutionV2 {
        public int findPeakElement(int[] nums) {
            if (nums.length < 2) { return 0; }
            int lo = 0, hi = nums.length-1;
            while (hi - lo > 1) {
                int mid = lo + (hi - lo) / 2;
                //System.out.println("lo=" + lo + ", hi=" + hi + ", mid=" + mid);
                int val = nums[mid];
                if (val < nums[hi]){
                    lo = mid;
                } else if (val < nums[lo]) {
                    hi = mid;
                } else {
                    lo++; hi--;
                }
            }
            return (nums[lo] > nums[hi])? lo : hi;
        }
    }
    public class Solution {
        public int findPeakElement(int[] nums) {
            if (nums.length < 2) { return 0; }
            int lo = 0, hi = nums.length-1;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (nums[mid] < nums[mid+1]) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            return lo;    
        }
    }
    private static FindPeakElement test = new FindPeakElement();
    private static Solution solution = test.new Solution();
    private static void testFindPeakElement(int[] nums) {
        System.out.println("For array: " + Arrays.toString(nums) + ", find peak @" + solution.findPeakElement(nums));
    }
    public static void main(String[] args) {
        int[] nums1 = new int[]{ 1 }; // answer = 0
        int[] nums2 = new int[]{ 1, 2 }; // answer = 1
        int[] nums3 = new int[]{ 1, 2, 3, 1}; // answer = 2
        testFindPeakElement(nums1);
        testFindPeakElement(nums2);
        testFindPeakElement(nums3);
    }
}
