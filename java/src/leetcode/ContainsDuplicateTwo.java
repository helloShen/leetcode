/**
 * Leetcode - Algorithm - Contains Duplicate Two
 */
package com.ciaoshen.leetcode;
import java.util.*;

class ContainsDuplicateTwo {
    /**
     * O(n): use HashSet to check the duplicate
     */
    public class Solution {
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            if (nums.length == 0) { return false; }
            Set<Integer> checkSet = new HashSet<>();
            for (int i = 0; i <= k && i < nums.length; i++) {
                if (!checkSet.add(nums[i])) { return true; }
            }
            for (int i = 0, j = k + 1; j < nums.length; i++,j++) {
                checkSet.remove(new Integer(nums[i]));
                if (!checkSet.add(nums[j])) { return true; }
            }
            return false;
        }
    }
    private static ContainsDuplicateTwo test = new ContainsDuplicateTwo();
    private static Solution solution = test.new Solution();
    private static void callContainsNearbyDuplicate(int[] nums, int k, String answer) {
        System.out.println(Arrays.toString(nums) + " contains duplicate in window of " + k + "? " + solution.containsNearbyDuplicate(nums,k) + "    (answer = " + answer + ")");
    }
    private static void test() {
        int[] nums1 = new int[]{1,1,1,1};
        int[] nums2 = new int[]{1,2,3,4};
        int[] nums3 = new int[]{1,2,3,4,5,6,3,8,9};
        callContainsNearbyDuplicate(nums1,2,"true");
        callContainsNearbyDuplicate(nums2,2,"false");
        callContainsNearbyDuplicate(nums3,3,"false");
        callContainsNearbyDuplicate(nums3,4,"true");
    }
    public static void main(String[] args) {
        test();
    }
}
