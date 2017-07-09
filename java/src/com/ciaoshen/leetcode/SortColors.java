/**
 * Leetcode - Algorithm - Sort Colors
 */
package com.ciaoshen.leetcode;
import java.util.*;

class SortColors {

    public void sortColors(int[] colors) {
        threeWayPartition(colors,1);
    }
    public void threeWayPartition(int[] nums, int target) {
        int firstEqual = -1, firstGreater = -1;
        for (int i = 0; i < nums.length; i++) {
            if (firstGreater < 0 && nums[i] > target) {
                firstGreater = i; continue;
            }
            if (nums[i] == target) {
                if (firstGreater >= 0) {
                    exchange(nums,firstGreater,i);
                    if (firstEqual < 0) { firstEqual = firstGreater; }
                    firstGreater++;
                } else if (firstEqual < 0){
                    firstEqual = i;
                }
            }
            if (nums[i] < target) {
                int tempCursor = i; // 标明需要交换的位置
                if (firstGreater >= 0) {
                    exchange(nums,firstGreater,tempCursor);
                    tempCursor = firstGreater;
                    firstGreater++;
                }
                if (firstEqual >= 0) {
                    exchange(nums,firstEqual,tempCursor);
                    firstEqual++;
                }
            }
        }
    }
    public void exchange(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
    private static SortColors test = new SortColors();
    private static void testSortColors() {

    }
    public static void main(String[] args) {

    }
}
