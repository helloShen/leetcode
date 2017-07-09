/**
 * Leetcode - Algorithm - Product of Array Except Self
 */
package com.ciaoshen.leetcode;
import java.util.*;

class ProductOfArrayExceptSelf {
    /**
     * time: O(n)
     * space: O(n)
     */
    public class SolutionV1 {
        public int[] productExceptSelf(int[] nums) {
            int[] fromLeftTill = new int[nums.length];
            fromLeftTill[0] = 1;
            for (int i = 1; i < nums.length; i++) {
                fromLeftTill[i] = fromLeftTill[i-1] * nums[i-1];
            }
            int[] fromRightTill = new int[nums.length];
            fromRightTill[nums.length-1] = 1;
            for (int i = nums.length-2; i >= 0; i--) {
                fromRightTill[i] = fromRightTill[i+1] * nums[i+1];
            }
            int[] product = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                product[i] = fromLeftTill[i] * fromRightTill[i];
            }
            return product;
        }
    }
    /**
     * time: O(n)
     * space: O(1)
     */
    public class Solution {
        public int[] productExceptSelf(int[] nums) {
            int[] product = new int[nums.length];
            product[nums.length-1] = 1;
            for (int i = nums.length-2; i >= 0; i--) { // go left
                product[i] = product[i+1] * nums[i+1];
            }
            // System.out.println(Arrays.toString(product));
            int productSoFarFromLeft = 1;
            for (int i = 0; i < nums.length-1; i++) { // go back to right
                product[i] *= productSoFarFromLeft;
                productSoFarFromLeft *= nums[i];
            }
            product[nums.length-1] = productSoFarFromLeft;
            return product;
        }
    }
    private class Test {
        private void callProductExceptSelf(int[] nums, int[] answer) {
            System.out.println("Original Array: " + Arrays.toString(nums));
            System.out.println("Product Array: " + Arrays.toString(solution.productExceptSelf(nums)));
            System.out.println("Answer should be: " + Arrays.toString(answer));
        }
        private void test() {
            int[] nums1 = new int[]{1,2,3,4};
            int[] answer1 = new int[]{24,12,8,6};
            // int[] nums2 = new int[]{};
            // int[] answer2 = new int[]{};
            // int[] nums3 = new int[]{};
            // int[] answer3 = new int[]{};
            callProductExceptSelf(nums1,answer1);
            // callProductExceptSelf(nums2,answer2);
            // callProductExceptSelf(nums3,answer3);
        }
    }
    private static ProductOfArrayExceptSelf problem = new ProductOfArrayExceptSelf();
    private static Solution solution = problem.new Solution();
    private static Test test = problem.new Test();
    public static void main(String[] args) {
        test.test();
    }
}
