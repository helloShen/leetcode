/**
 * Leetcode - Algorithm - Contains Duplicate
 */
package com.ciaoshen.leetcode;
import java.util.*;

class ContainsDuplicate {
    /**
     * O(nlogn): use Arrays.sort();
     */
    public class Solution {
        public boolean containsDuplicate(int[] nums) {
            if (nums.length == 0) { return false; }
            Arrays.sort(nums);
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i-1]) { return true; }
            }
            return false;
        }
    }
    /**
     * O(n): use HashSet to check the duplicate
     */
    public class SolutionV2 {
        public boolean containsDuplicate(int[] nums) {
            if (nums.length == 0) { return false; }
            Set<Integer> checkSet = new HashSet<>();
            for (int n : nums) {
                if (!checkSet.add(n)) { return true; }
            }
            return false;
        }
    }
    private static ContainsDuplicate test = new ContainsDuplicate();
    private static Solution solution = test.new Solution();
    private static void callContainsDuplicate(int[] nums) {
        System.out.println(Arrays.toString(nums) + " contains duplicate? " + solution.containsDuplicate(nums));
    }
    private static void test() {
        int[] nums1 = new int[]{1,1,1,1};
        int[] nums2 = new int[]{1,2,3,4};
        callContainsDuplicate(nums1);
        callContainsDuplicate(nums2);
    }
    public static void main(String[] args) {
        test();
    }
}
