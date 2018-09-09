/**
 * Leetcode - Next Permutation
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place, do not allocate extra memory.
 *Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 *   1,2,3 → 1,3,2
 *   3,2,1 → 1,2,3
 *   1,1,5 → 1,5,1
 */
package com.ciaoshen.leetcode;
import java.util.*;

public class NextPermutation {

    public void nextPermutationV1(int[] nums) {
        int toSwitch = -1;
        for (int i = nums.length-1; i > 0; i--) {
            if (nums[i] > nums[i-1]) { toSwitch = i-1; break; }
        }
        //System.out.println("Switch Index: " + toSwitch);
        if (toSwitch >= 0){
            int anotherToSwitch = 0;
            for (int i = nums.length-1; i > toSwitch; i--) {
                if (nums[i] > nums[toSwitch]) {
                    anotherToSwitch = i;
                    //System.out.println("Another to Switch = " + anotherToSwitch);
                    break;
                }
            }
            int temp = nums[toSwitch];
            nums[toSwitch] = nums[anotherToSwitch];
            nums[anotherToSwitch] = temp;
        }
        Arrays.sort(nums,toSwitch+1,nums.length);
    }
    public void nextPermutation(int[] nums) {
        int toSwap = -1;
        for (int i = nums.length-1; i > 0; i--) {
            if (nums[i] > nums[i-1]) { toSwap = i-1; break; }
        }
        if (toSwap >= 0){
            int anotherToSwap = 0;
            for (int i = nums.length-1; i > toSwap ; i--) {
                if (nums[i] > nums[toSwap]) { anotherToSwap = i; break; }
            }
            swap(nums,toSwap,anotherToSwap);
        }
        reverseSort(nums, toSwap+1, nums.length-1);
    }
    public void swap(int[] nums, int high, int low) {
        int temp = nums[high];
        nums[high] = nums[low];
        nums[low] = temp;
    }
    public void reverseSort(int[] nums, int begin, int end) { // begin & end are both included
        while (begin < end) {
            swap(nums,begin,end);
            begin++; end--;
        }
    }
    private static void testNextPermutation() {
        NextPermutation test = new NextPermutation();
        int[] nums1 = new int[]{0}; // ans = 0
        int[] nums2 = new int[]{1}; // ans = 1
        int[] nums3 = new int[]{1,2}; // ans = 2,1
        int[] nums4 = new int[]{1,2,3,4,5}; // ans = 1,2,3,5,4
        int[] nums5 = new int[]{5,4,3,2,1}; // ans = 1,2,3,4,5
        int[] nums6 = new int[]{3,4,8,9,7,5,2,0}; // ans = 3,4,9,0,2,5,7,8
        int[] nums7 = new int[]{9,8,3,7,5,9,9,9,8,7,0}; // ans= 9,8,3,7,7,0,5,8,9,9,9
        System.out.println("Before: " + Arrays.toString(nums1));
        test.nextPermutation(nums1);
        System.out.println("After: " + Arrays.toString(nums1));
        System.out.println("Before: " + Arrays.toString(nums2));
        test.nextPermutation(nums2);
        System.out.println("After: " + Arrays.toString(nums2));
        System.out.println("Before: " + Arrays.toString(nums3));
        test.nextPermutation(nums3);
        System.out.println("After: " + Arrays.toString(nums3));
        System.out.println("Before: " + Arrays.toString(nums4));
        test.nextPermutation(nums4);
        System.out.println("After: " + Arrays.toString(nums4));
        System.out.println("Before: " + Arrays.toString(nums5));
        test.nextPermutation(nums5);
        System.out.println("After: " + Arrays.toString(nums5));
        System.out.println("Before: " + Arrays.toString(nums6));
        test.nextPermutation(nums6);
        System.out.println("After: " + Arrays.toString(nums6));
        System.out.println("Before: " + Arrays.toString(nums7));
        test.nextPermutation(nums7);
        System.out.println("After: " + Arrays.toString(nums7));
    }
    public static void main(String[] args) {
        testNextPermutation();
    }
}
