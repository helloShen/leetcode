/**
 * Leetcode - Search in Rotated Sorted Array
 */
package com.ciaoshen.leetcode;
import java.util.*;


public class SearchInRotatedSortedArray {
    public int searchV1(int[] nums, int target) {
        if (nums.length == 0) { return -1; }
        if (nums.length == 1) { return (target == nums[0])? 0 : -1; }
        int cursor = 0, next = 0;
        while (true) {
            if (nums[cursor] == target) { return cursor; }
            if (nums[cursor] < target) {
                next = (cursor + 1) % nums.length;
                if (nums[next] < nums[cursor] || nums[next] > target) { return -1; }
            }
            if (nums[cursor] > target) {
                next = (cursor - 1 < 0)? cursor - 1 + nums.length : cursor - 1;
                if (nums[next] > nums[cursor] || nums[next] < target) { return -1; }
            }
            cursor = next;
        }
    }

    public int searchV2(int[] nums, int target) {
        if (nums.length == 0) { return -1; }
        if (nums.length == 1) { return (target == nums[0])? 0 : -1; }
        int pivot = searchPivot(nums,0,nums.length-1);
        return searchRecursive(nums,0,nums.length-1,target,pivot);
    }
    public int searchRecursive(int[] nums, int low, int high, int target, int rotate) {
        if (low > high) { return -1; }
        if (low == high) {
            int index = (low + rotate) % nums.length;
            return (nums[index] == target)? index : -1;
        }
        int median = low + (high - low) / 2;
        int medianRotated = (median + rotate) % nums.length;
        if (nums[medianRotated] < target) {
            return searchRecursive(nums,median+1,high,target,rotate);
        } else if (nums[medianRotated] > target) {
            return searchRecursive(nums,low,median-1,target,rotate);
        } else { // nums[medianRotated] == target
            return medianRotated;
        }
    }
    // find the minimum number in nums array
    public int searchPivot(int[] nums, int low, int high) { // low and high index are both included
        if (nums.length == 0) { return -1; }
        if (nums.length == 1) { return 0; }
        if (high == low) { return low; }
        if (high - low == 1) { return (nums[low]< nums[high])? low : high; }
        int median = low + (high - low) / 2;
        System.out.println("Low=" + low + ", High=" + high + ", Median=" + median);
        if (nums[median] < nums[high]) {
            return searchPivot(nums,low,median);
        } else {
            return searchPivot(nums,median+1,high);
        }
    }

    public int search(int[] nums, int target) {
        if (nums.length == 0) { return -1; }
        if (nums.length == 1) { return (target == nums[0])? 0 : -1; }
        int pivot = pivot(nums,0,nums.length-1);
        return searchRecur(nums,0,nums.length-1,target,pivot);
    }
    // simple version of searchPivot
    public int pivot(int[] nums, int low, int high) {
        if (high == low) { return low; }
        int median = low + (high - low) / 2;
        if (nums[median] < nums[high]) {
            return pivot(nums,low,median);
        } else {
            return pivot(nums,median+1,high);
        }
    }
    public int searchRecur(int[] nums, int low, int high, int target, int rotate) {
        if (low > high) { return -1; }
        int median = low + (high - low) / 2;
        int medianRotated = (median + rotate) % nums.length;
        if (nums[medianRotated] < target) {
            return searchRecur(nums,median+1,high,target,rotate);
        } else if (nums[medianRotated] > target) {
            return searchRecur(nums,low,median-1,target,rotate);
        } else { // nums[medianRotated] == target
            return medianRotated;
        }
    }

    private static SearchInRotatedSortedArray test = new SearchInRotatedSortedArray();
    private static int[][] testArrays = new int[][]{
        {},
        {1},
        {1,2,3,4,5,6,7,8,9},
        {1,2,3,4,5,6,7,8,9,0},
        {5,6,7,8,9,0,1,2,3,4},
        {10,14,16,20,3,6,9}
    };
    private static void testSearchPivot() {
        for (int i = 0; i < testArrays.length; i++) {
            System.out.println("Pivot in " + Arrays.toString(testArrays[i]) + "is: " + test.searchPivot(testArrays[i],0,testArrays[i].length-1));
        }
    }
    private static void testSearch() {
        for (int i = 0; i < testArrays.length; i++) {
            System.out.println(i + " in " + Arrays.toString(testArrays[i]) + "is: " + test.search(testArrays[i],i));
        }
    }
    public static void main(String[] args) {
        //testSearchPivot();
        testSearch();
    }
}
