/**
 * Leetcode - Remove Duplicates from Sorted Array
 */
package com.ciaoshen.leetcode;
import java.util.*;

public class RemoveDuplicates {
    public int removeDuplicatesV1(int[] nums) {
        if (nums == null || nums.length == 0) { return 0; }
        int slow = 0, fast = 0, end = nums.length;
        while (fast < end) {
            if (nums[fast] - nums[slow] > 0) {
                if (fast - slow > 1) { // need to move
                    int gap = fast - slow - 1;
                    for (int j = fast; j < end; j++) {
                        nums[j-gap] = nums[j];
                    }
                    end -= gap;
                }
                slow++;
                fast = slow;
            }
            fast++;
        }
        return slow+1;
    }
    public int removeDuplicatesV2(int[] nums) {
        if (nums == null || nums.length == 0) { return 0; }
        int slow = 0, fast = 0;
        int max = nums[slow];
        while (fast < nums.length) {
            if (nums[fast] > max) {
                nums[++slow] = nums[fast];
                max = nums[slow];
            }
            fast++;
        }
        return slow + 1;
    }
    public int removeDuplicatesV3(int[] nums) {
        int slow = -1, fast = 0, max = Integer.MIN_VALUE;
        while (fast < nums.length) {
            if (nums[fast++] > max) {
                nums[++slow] = nums[fast-1];
                max = nums[slow];
            }
        }
        return slow + 1;
    }
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) { return 0; }
        int cursor = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[cursor]) {
                nums[++cursor] = nums[i];
            }
        }
        return ++cursor;
    }
    private static void testRemoveDuplicates() {
        RemoveDuplicates rd = new RemoveDuplicates();
        int[] nums0 = new int[]{};
        int[] nums1 = new int[]{1,1,2,2,2,3,4,5,6,6,7,7,8,9}; // answer = 9
        int[] nums2 = new int[]{2,2,4,4,6,6,8,8}; // answer = 4
        System.out.println(Arrays.toString(nums0) + ": " + rd.removeDuplicates(nums0) + Arrays.toString(nums0));
        System.out.println(Arrays.toString(nums1) + ": " + rd.removeDuplicates(nums1) + Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2) + ": " + rd.removeDuplicates(nums2) + Arrays.toString(nums2));
    }

    public static void main(String[] args) {
        testRemoveDuplicates();
    }
}
