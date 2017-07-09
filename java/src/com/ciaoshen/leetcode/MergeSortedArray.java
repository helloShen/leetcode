/**
 * Leetcode - Algorithm - Merge Sorted Array
 */
package com.ciaoshen.leetcode;
import java.util.*;

class MergeSortedArray {
    public void mergeV1(int[] nums1, int m, int[] nums2, int n) {
        int tail1 = m - 1, tail2 = n - 1;
        int cursor = m + n - 1;
        //System.out.println("Cursor=" + cursor + ", Tail1=" + tail1 + ", Tail2=" + tail2);
        while (tail1 >= 0 || tail2 >= 0) {
            int first = Integer.MIN_VALUE;
            int second = Integer.MIN_VALUE;
            if (tail1 >= 0) { first = nums1[tail1]; }
            if (tail2 >= 0) { second = nums2[tail2]; }
            if (first >= second) {
                nums1[cursor--] = nums1[tail1--];
            } else {
                nums1[cursor--] = nums2[tail2--];
            }
        }
    }
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int tail1 = m - 1, tail2 = n - 1, cur = m + n - 1;
        while (tail1 >= 0 && tail2 >= 0) {
            if (nums1[tail1] >= nums2[tail2]) {
                nums1[cur--] = nums1[tail1--];
            } else {
                nums1[cur--] = nums2[tail2--];
            }
        }
        while (tail2 >= 0) { nums1[cur--] = nums2[tail2--]; }
    }
    private static MergeSortedArray test = new MergeSortedArray();
    private static int[][] nums1Set = new int[][]{
        {1,0},
        {1,3,0,0},
        {1,2,3,4,5,0,0,0,0,0},
        {1,3,5,7,9,0,0,0,0,0},
        {9,9,9,9,9,0,0,0,0,0,0,0,0,0}
    };
    private static int[][] nums2Set = new int[][]{
        {1},
        {2,2},
        {1,2,3,4,5},
        {1,2,3,4,5},
        {1,3,5,7,9}
    };
    private static int[][] size = new int[][]{
        {1,1},
        {2,2},
        {5,5},
        {5,5},
        {5,5}
    };
    private static void testMerge() {
        for (int i = 0; i < nums1Set.length; i++) {
            System.out.println("Merge " + Arrays.toString(nums1Set[i]) + ", and " + Arrays.toString(nums2Set[i]));
            test.merge(nums1Set[i],size[i][0],nums2Set[i],size[i][1]);
            System.out.println(Arrays.toString(nums1Set[i]));
        }
    }
    public static void main(String[] args) {
        testMerge();
    }
}
