/**
 * Leetcode 3Sum Cloest
 */
package com.ciaoshen.leetcode;
import java.util.*;
import java.lang.Math;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        int result = target;
        if (nums == null || nums.length < 3) { return result; }
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int main = 0;
        long minDiff = Math.abs((long)Integer.MIN_VALUE - (long)target);
        outerWhile:
        while (main < nums.length-2) {
            int low = main + 1;
            int high = nums.length - 1;
            //System.out.println("Main=" + main + ", Low=" + low + ", High=" + high);
            while (low < high) {
                long sum = (long)nums[main] + (long)nums[low] + (long)nums[high];
                long tempDiff = sum - target;
                //System.out.println("Low=" + low + ", High=" + high + ", Sum=" + sum + ", Sum-100=" + tempDiff);
                if (tempDiff == 0) { result = (int)sum; break outerWhile; }
                if (tempDiff < 0) {
                    while (low+1 < high && nums[low] == nums[low+1]) { low++; }
                    low++;
                }
                if (tempDiff > 0) {
                    while (low < high-1 && nums[high] == nums[high-1]) { high--; }
                    high--;
                }
                long absDiff = Math.abs(tempDiff);
                if (absDiff < minDiff) {
                    //System.out.println("Find a closer one. Sum=" + sum);
                    minDiff = absDiff;
                    result = (int)sum;
                }
            }
            while (main < nums.length-2 && nums[main] == nums[main+1]) { main++; }
            main++;
        }
        return result;
    }
    private static void testThreeSumClosest() {
        ThreeSumClosest tsc = new ThreeSumClosest();
        System.out.println(tsc.threeSumClosest(new int[]{-1,0,1,2,-1,-4},8));
        System.out.println(tsc.threeSumClosest(new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0},-3));
        System.out.println(tsc.threeSumClosest(new int[]{-4,-21,91,-35,-14,-41,74,-82,30,24,50,-7,3,11,-5,100},100));
        System.out.println(tsc.threeSumClosest(new int[]{1,1,1,1},0));
        System.out.println(tsc.threeSumClosest(new int[]{1,1,1,0},-100));
        System.out.println(tsc.threeSumClosest(new int[]{13,2,0,-14,-20,19,8,-5,-13,-3,20,15,20,5,13,14,-17,-7,12,-6,0,20,-19,-1,-15,-2,8,-2,-9,13,0,-3,-18,-9,-9,-19,17,-14,-19,-4,-16,2,0,9,5,-7,-4,20,18,9,0,12,-1,10,-17,-11,16,-13,-14,-3,0,2,-18,2,8,20,-15,3,-13,-12,-2,-19,11,11,-10,1,1,-10,-2,12,0,17,-19,-7,8,-19,-17,5,-5,-10,8,0,-12,4,19,2,0,12,14,-9,15,7,0,-16,-5,16,-12,0,2,-16,14,18,12,13,5,0,5,6},-59));
    }
    public static void main(String[] args) {
        testThreeSumClosest();
    }
}
