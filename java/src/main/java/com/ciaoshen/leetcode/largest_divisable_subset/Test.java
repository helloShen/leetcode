/**
 * Leetcode - #368 - Largest Divisable Subset
 */
package com.ciaoshen.leetcode.largest_divisable_subset;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Test {

    private static int[] nums1 = new int[]{3,2,1};
    private static int[] answer1 = new int[]{1,2};
    private static int[] nums2 = new int[]{2,102,7,20,32,54,3,66,150,1};
    private static int[] answer2 = new int[]{1,2,20};
    private static int[] nums3 = new int[]{1};
    private static int[] answer3 = new int[]{1};
    private static int[] nums4 = new int[]{3,4,16,8};
    private static int[] answer4 = new int[]{4,8,16};
    private static int[] nums5 = new int[]{4,8,10,240};
    private static int[] answer5 = new int[]{4,8,240};

    private static int[][] nums = new int[][]{nums1, nums2, nums3, nums4, nums5};
    private static int[][] answers = new int[][]{answer1, answer2, answer3, answer4, answer5};

    private static void call(Solution s, int[] nums, int[] answer) {
        System.out.println("Nums:\t" + Arrays.toString(nums));
        System.out.println("Result:\t" + s.largestDivisibleSubset(nums));
        System.out.println("Answer:\t" + Arrays.toString(answer));
    }
    private static void test(Solution s) {
        for (int i = 0; i < nums.length; i++) {
            call(s, nums[i], answers[i]);
        }
    }

    public static void main(String[] args) {
        // test(new Solution1());
        // test(new Solution2());
        // test(new Solution3());
        test(new Solution4());

    }
}