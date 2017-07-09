/**
 * Leetcode Two Sum
 */
package com.ciaoshen.leetcode;
import java.util.*;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        if (nums.length < 2) {
            throw new IllegalArgumentException();
        }
        int[] result = new int[] {-1,-1};
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }
    public static int[] twoSumLogN(int[] nums, int target) {
        if (nums.length < 2) {
            throw new IllegalArgumentException();
        }
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                return new int[] {map.get(diff),i};
            }
            map.put(nums[i],i);
        }
        return new int[] {0,0};
    }
    private static void testTwoSum() {
        int[] nums1 = new int[] {3,2,4};
        int target1 = 6;
        int[] result1 = new int[] {1,2};
        //if (Arrays.equals(twoSum(nums1,target1),result1)) {
        if (Arrays.equals(twoSumLogN(nums1,target1),result1)) {
            System.out.println("Test 1 ... pass!");
        } else {
            System.out.println("Test 1 ... fail!");
            System.out.println("Answer: " + Arrays.toString(result1) + "My result: " + Arrays.toString(twoSum(nums1,target1)));
        }
    }
    public static void main(String[] args) {
        testTwoSum();
    }
}
