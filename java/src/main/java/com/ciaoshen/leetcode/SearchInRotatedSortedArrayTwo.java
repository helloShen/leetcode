/**
 * Leetcode - Algorithm - Search in Rotated Sorted Array Two
 */
package com.ciaoshen.leetcode;
import java.util.*;

class SearchInRotatedSortedArrayTwo {
    public boolean searchV1(int[] nums, int target) {
        if (nums.length == 0) { return false; }
        for (int num : nums) {
            if (num == target) { return true; }
        }
        return false;
    }
    public boolean searchV2(int[] nums, int target) {
        if (nums.length == 0) { return false; }
        int pivot = pivot(nums);
        int lo = 0, hi = nums.length-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int absMid = (mid + pivot) % nums.length;
            System.out.println("Pos [" + absMid + "]: " + nums[absMid]);
            if (nums[absMid] < target) {
                lo = mid + 1;
            } else if (nums[absMid] > target) {
                hi = mid - 1;
            } else { // nums[absMid] == target
                return true;
            }
        }
        return false;
    }
    public int pivot(int[] nums) {
        if (nums.length < 2) { return nums.length; }
        int lo = 0, hi = nums.length-1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] <= nums[hi]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
    // 也是错的
    public int pivot2(int[] nums) {
        if (nums.length < 2) { return nums.length; }
        int lo = 0, hi = nums.length-1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < nums[hi]) {
                hi = mid;
            } else if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else { // nums[mid] == nums[hi] 错误！不能这么任性左移最高位
                hi--;
            }
        }
        return lo;
    }
    public boolean search(int[] nums, int target) {
        if (nums.length == 0) { return false; }
        int lo = 0, hi = nums.length-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) { return true; }
            if (nums[mid] < nums[hi]) { // right half is sorted, ex:[7,0,1,3,5,6]
                if (nums[mid] < target && target <= nums[hi]) { // target can only be in right half
                    lo = mid + 1;
                } else { // target is not in right half
                    hi = mid - 1;
                }
            } else if (nums[mid] > nums[hi]) { // left half is sorted, ex:[1,3,5,6,1,1,1]
                if (nums[lo] <= target && target < nums[mid]) { // target can only be in left half
                    hi = mid - 1;
                } else { // target is not in left half
                    lo = mid + 1;
                }
            } else { // nums[mid] == nums[hi]: [2,2,2,0,2,2]
                hi--;
            }
        }
        return false;
    }
    private static SearchInRotatedSortedArrayTwo test = new SearchInRotatedSortedArrayTwo();
    private static int[][] testCases = new int[][]{
        {2,2},
        {2,3},
        {3,2},
        {4,4,4,5,0,1,2},
        {4,4,4,5,5,6,7},
        {5,6,7,5,5,5,5},
        {2,2,2,0,2,2},
        {0,2,2,2,2,2},
        {2,2,2,2,2,0}
    };
    private static int[] yes = new int[]{2,3,3,4,5,7,0,0,0};
    private static int[] no = new int[]{1,5,4,3,8,4,1,1,1};
    private static void testSearch() {
        System.out.println("Should be >>> TRUE <<<");
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Find " + yes[i] + " in " + Arrays.toString(testCases[i]) + ":    " + test.search(testCases[i],yes[i]));
        }
        System.out.println("Should be >>> FALSE <<<");
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Find " + no[i] + " in " + Arrays.toString(testCases[i]) + ":    " + test.search(testCases[i],no[i]));
        }
    }
    private static void testPivot() {
        for (int[] nums : testCases) {
            System.out.println("Pivot of " + Arrays.toString(nums) + " is: " + test.pivot2(nums));
        }
    }
    public static void main(String[] args) {
        //testSearch();
        testPivot();
    }
}
