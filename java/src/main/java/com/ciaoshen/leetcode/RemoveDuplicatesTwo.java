/**
 * Leetcode - Algorithm - Remove Duplicates Two
 */
package com.ciaoshen.leetcode;
import java.util.*;

class RemoveDuplicatesTwo {

    // [1,1,1,2,2,3]
    /* two pointers 两大关键点：
     *  1. 明确每个pointer的职责
     *  2. 归纳每个pointer的行为，要学会站在每个pointer的视角看问题
     */
    public int removeDuplicatesV1(int[] nums) {
        if (nums.length == 0) { return 0; }
        int register = nums[0], count = 1, slow = 1;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] > register) {
                register = nums[fast];
                count = 1;
                nums[slow++] = nums[fast];
            } else if (nums[fast] == register && count < 2) {
                count++;
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }
    public int removeDuplicatesV2(int[] nums) {
        if (nums.length == 0) { return 0; }
        int boundary = 1, count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[boundary-1]) {
                count = 1;
                nums[boundary++] = nums[i];
            } else if (nums[i] == nums[boundary-1] && count < 2) {
                count++;
                nums[boundary++] = nums[i];
            }
        }
        return boundary;
    }
    public int removeDuplicates(int[] nums) {
       int i = 0;
       for (int n : nums)
          if (i < 2 || n > nums[i - 2])
             nums[i++] = n;
       return i;
    }
    private static void testRemoveDuplicates() {
        RemoveDuplicatesTwo test = new RemoveDuplicatesTwo();
        int[][] testCases = new int[][]{
            {1,2,3},
            {1,1,2,2,3,3},
            {1,1,1,2,2,2,3,3,3},
            {1,1,1,2,2,3}
        };
        for (int[] nums: testCases) {
            System.out.println("Length of " + Arrays.toString(nums) + " is: " + test.removeDuplicates(nums));
        }
    }
    public static void main(String[] args) {
        testRemoveDuplicates();
    }
}
