/**
 * Leetcode - Search for a range
 */
package com.ciaoshen.leetcode;
import java.util.*;

public class SearchForARange {
    public int[] searchRangeV1(int[] nums, int target) {
        return search(nums,target,0,nums.length-1);
    }
    public int[] search(int[] nums, int target, int low, int high) {
        if (low > high) { return new int[]{-1,-1}; }
        int median = low + (high - low) / 2;
        if (nums[median] < target) {
            return search(nums,target,median+1,high);
        } else if (nums[median] > target) {
            return search(nums,target,low,median-1);
        } else {
            int lowBound = searchLowBound(nums,target,low,median);
            int highBound = searchHighBound(nums,target,median,high);
            return new int[]{lowBound,highBound};
        }
    }
    public int searchLowBound(int[] nums, int target, int lowBound, int lowCertain) {
        System.out.println("LowBound=" + lowBound + ", LowCertain=" + lowCertain);
        if (lowBound == lowCertain) { return lowCertain; }
        int median = (lowBound + lowCertain) / 2;
        if (nums[median] < target) {
            return searchLowBound(nums,target,median+1,lowCertain);
        } else { // nums[median] == target
            return searchLowBound(nums,target,lowBound,median);
        }
    }
    public int searchHighBound(int[] nums, int target, int highCertain, int highBound) {
        System.out.println("HighCertain=" + highCertain + ", HighBound=" + highBound);
        if (highBound == highCertain) { return highCertain; }
        int median = (highCertain + highBound + 1) / 2;
        if (nums[median] > target) {
            return searchHighBound(nums,target,highCertain,median-1);
        } else { // nums[median] == target
            return searchHighBound(nums,target,median,highBound);
        }
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) { return new int[]{-1,-1}; }
        int start = firstGreaterEqual(nums,target,0,nums.length-1);
        if (start == nums.length || nums[start] != target) { return new int[]{-1,-1}; }
        int end = firstGreaterEqual(nums,target+1,start,nums.length-1);
        return new int[]{start,end-1};
    }

    public int firstGreaterEqual(int[] nums, int target, int low, int high) {
        if (low == high) { return (nums[low] >= target)? low : nums.length; }
        int median = (low + high) >> 1;
        if (nums[median] < target) {
            return firstGreaterEqual(nums,target,median+1,high);
        } else {
            return firstGreaterEqual(nums,target,low,median);
        }
    }

    private static void testSearchForARange() {
        SearchForARange test = new SearchForARange();
        int[][] testArrays = new int[][]{
            {},
            {1},
            {1,2,3,4,5,6,7,8,9},
            {0,1,2,3,4,5,6,7,8,9},
            {1,5},
            {10,14,16,20,23,26,29},
            {1,1,3,5,5,5,6,6,6,6,6,6,7,8,8,8,8,8,8,10,12},
            {7,7,7,7,9,10,10,10,11,12},
            {3,4,5,6,8,8,8,8}
        };
        for (int i = 0; i < testArrays.length; i++) {
            System.out.println(Arrays.toString(testArrays[i]) + "   Range of " + i + " >>>     " + Arrays.toString(test.searchRange(testArrays[i],i)));
        }
    }
    public static void main(String[] args) {
        testSearchForARange();
    }
}
