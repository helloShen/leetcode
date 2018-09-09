/**
 * Leetcode - Search Insert Position
 */
package com.ciaoshen.leetcode;
import java.util.*;

public class SearchInsertPosition {
    public int searchInsertV1(int[] nums, int target) {
        if (nums.length == 0) { return 0; }
        return searchInsertRecur(nums,target,0,nums.length-1);
    }
    public int searchInsertRecur(int[] nums, int target, int low, int high) {
        if (low >= high) {
            //System.out.println("low == high at: " + low + ". And nums[low] <= target? " + (nums[low] >= target));
            return (nums[low] >= target)? low : ++low;
        }
        int median = low + ( (high - low) >> 1 );
        if (nums[median] < target) {
            return searchInsertRecur(nums,target,median+1,high);
        } else if (nums[median] > target) {
            return searchInsertRecur(nums,target,low,median-1);
        } else {
            return median;
        }
    }
    public int searchInsertV2(int[] nums, int target) {
        if (nums.length == 0) { return 0; }
        int low = 0, high = nums.length-1;
        while (low < high) {
            int median = low + ( (high - low) >> 1 );
            if (nums[median] < target) { low = median + 1; }
            if (nums[median] > target) { high = median - 1; }
            if (nums[median] == target) { return median; }
        }
        return (nums[low] >= target)? low : ++low;
    }
    public int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length-1;
        while (low <= high) {
            int median = low + ( (high - low) >> 1 );
            if (nums[median] < target) { low = median + 1; }
            if (nums[median] > target) { high = median - 1; }
            if (nums[median] == target) { return median; }
        }
        return low;
    }

    private static void testSearchInsert() {
        SearchInsertPosition test = new SearchInsertPosition();
        int[][] testArrays = new int[][]{
            {1,3},
            {1},
            {1,2,3,4,5,6,7,8,9},
            {0,1,2,3,4,5,6,7,8,9},
            {1,5},
            {10,14,16,20,23,26,29},
            {1,3,5,7,8,20,22},
            {7,9,10,11,12},
            {3,4,5,6,8}
        };
        for (int i = 0; i < testArrays.length; i++) {
            System.out.println(Arrays.toString(testArrays[i]) + "   position for " + i + " >>>     " + test.searchInsert(testArrays[i],i));
        }
    }
    public static void main(String[] args) {
        testSearchInsert();
    }
}
