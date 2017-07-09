/**
 * Leetcode - Algorithm - Find Minimum in Rotated Sorted Array Two
 */
package com.ciaoshen.leetcode;
import java.util.*;

class FindMinimumInRotatedSortedArrayTwo {
    public class Solution {
        public int findMin(int[] nums) {
            if (nums.length == 0) { return 0; }
            int lo = 0, hi = nums.length-1;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (nums[mid] < nums[hi]) { hi = mid; continue; } // 最小值在左半边
                if (nums[mid] > nums[hi]) { lo = mid+1; continue; } // 最小值在右半边
                if (nums[mid] == nums[hi]) { hi--; } // 有可能在左半边，也可能在右半边。只能放心丢弃最高位的元素，因为至少中位数的值和他相等。
            }
            return nums[lo];
        }
    }
    private static FindMinimumInRotatedSortedArrayTwo test = new FindMinimumInRotatedSortedArrayTwo();
    private static Solution solution = test.new Solution();
    private static void testFindMin(int[] nums) {
        System.out.println("For Array: " + Arrays.toString(nums));
        System.out.println("Min = " + solution.findMin(nums));
    }
    public static void main(String[] args) {
        int[] nums1 = new int[]{4,4,4,4,4,1,3,3,3,3};
        int[] nums2 = new int[]{4,4,4,4,4,1,4,4,4,4};
        testFindMin(nums1);
        testFindMin(nums2);
    }
}
