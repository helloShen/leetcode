/**
 * Leetcode - Algorithm - Find Minimum in Rotated Sorted Array
 */
package com.ciaoshen.leetcode;
import java.util.*;

class FindMinimumInRotatedSortedArray {
    public class Solution {
        public int findMin(int[] nums) {
            if (nums.length == 0) { return 0; }
            int lo = 0, hi = nums.length-1;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (nums[mid] > nums[hi]) { lo = mid+1; continue; } // 在右半部分
                if (nums[mid] < nums[hi]) { hi = mid; } // 在左半部分
            }
            return nums[lo];
        }
    }
    private static FindMinimumInRotatedSortedArray test = new FindMinimumInRotatedSortedArray();
    private static Solution solution = test.new Solution();
    private static void testFindMin(int[] nums) {
        System.out.println("For array: " + Arrays.toString(nums));
        System.out.println("Min = " + solution.findMin(nums));
    }
    public static void main(String[] args) {
        int[] nums1 = new int[]{4,5,6,7,0,1,2};
        int[] nums2 = new int[]{0,1,2,3,4,5,6,7};
        int[] nums3 = new int[]{0};
        int[] nums4 = new int[0];
        int[] nums5 = new int[]{-5,0};
        testFindMin(nums1);
        testFindMin(nums2);
        testFindMin(nums3);
        testFindMin(nums4);
        testFindMin(nums5);
    }
}
