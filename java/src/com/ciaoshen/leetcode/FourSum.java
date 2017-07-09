/**
 * Leetcode - 4Sum
 */
package com.ciaoshen.leetcode;
import java.util.*;

// [1, 0, -1, 0, -2, 2]
// [-2, -1, 0, 0, 1, 2]
public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) { return result; }
        Arrays.sort(nums);
        //System.out.println(Arrays.toString(nums));
        for (int first = 0; first < nums.length - 3; first++) {
            for (int second = first+1; second < nums.length -2; second++) {
                int low = second + 1;
                int high = nums.length - 1;
                while (low < high) {
                    int sum = nums[first] + nums[second] + nums[low] + nums[high];
                    //System.out.println(sum + " First=" + first + ", Second=" + second + ", Low=" + low + ", High=" + high);
                    if (sum == target) {
                        //System.out.println("    FIND!!!");
                        result.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{nums[first],nums[second],nums[low],nums[high]})));
                    }
                    if (sum <= target) {
                        while(low+1 < high && nums[low] == nums[low+1]) { low++; }
                        low++;
                    }
                    if (sum >= target) {
                        while(low < high-1 && nums[high] == nums[high-1]) { high--; }
                        high--;
                    }
                }
                while(second+1 < nums.length-2 && nums[second] == nums[second+1]) { second++; }
            }
            while(first+1 < nums.length-3 && nums[first] == nums[first+1]) { first++; }
        }
        return result;
    }

    private static void testFourSum() {
        int[] array1 = new int[]{1, 0, -1, 0, -2, 2};
        FourSum fs = new FourSum();
        System.out.println(fs.fourSum(array1,0));
    }
    public static void main(String[] args) {
        testFourSum();
    }
}
