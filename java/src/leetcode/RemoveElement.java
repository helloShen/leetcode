/**
 * Leetcode - Remove Element
 */
package com.ciaoshen.leetcode;
import java.util.*;

public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int cursor = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[cursor++] = nums[i];
            }
        }
        return cursor;
    }

    private static void testRemoveElement() {
        RemoveElement test = new RemoveElement();
        int[] nums0 = new int[]{};
        int[] nums1 = new int[]{1,1,2,2,2,3,4,5,6,6,7,7,8,9}; // answer = 9
        int[] nums2 = new int[]{2,2,4,4,6,6,8,8}; // answer = 4
        System.out.println(Arrays.toString(nums0) + ": " + test.removeElement(nums0,2) + Arrays.toString(nums0));
        System.out.println(Arrays.toString(nums1) + ": " + test.removeElement(nums1,2) + Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2) + ": " + test.removeElement(nums2,6) + Arrays.toString(nums2));
    }
    public static void main(String[] args) {
        testRemoveElement();
    }
}
