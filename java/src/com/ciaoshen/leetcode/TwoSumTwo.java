/**
 * Leetcode - Algorithm - Two Sum Two - Input Array is Sorted
 */
package com.ciaoshen.leetcode;
import java.util.*;

class TwoSumTwo {
    /**
     * 时间复杂度：O(n^2)
     */
    public class SolutionV1 {
        public int[] twoSum(int[] numbers, int target) {
            int[] res = new int[] {-1,-1};
            if (numbers.length < 2) { return res; }
            int slow = 0;
            while (slow < numbers.length-1 && numbers[slow] <= target) {
                int num1 = numbers[slow];
                for (int fast = slow + 1; fast < numbers.length; fast++) {
                    int sum = num1 + numbers[fast];
                    if (sum == target) { // find target
                        res[0] = slow+1; res[1] = fast+1;
                        return res;
                    } else if (sum > target) {
                        break;
                    }
                }
                slow++;
            }
            return res; // do not find target
        }
    }
    /**
     * 时间复杂度：O(nlogn)
     */
    public class SolutionV2 {
        public int[] twoSum(int[] numbers, int target) {
            int[] res = new int[] {-1,-1};
            if (numbers.length < 2) { return res; }
            int slow = 0;
            while (slow < numbers.length-1 && numbers[slow] <= target) {
                int remain = target - numbers[slow];
                int lo = slow+1, hi = numbers.length-1;
                while (lo <= hi) {
                    int mid = lo + (hi - lo) / 2;
                    int median = numbers[mid];
                    if (median < remain) {
                        lo = mid+1;
                    } else if (median > remain) {
                        hi = mid-1;
                    } else { // median == remain
                        res[0] = slow+1; res[1] = mid+1;
                        return res; // find target
                    }
                }
                slow++;
            }
            return res; // do not find target
        }
    }
    /**
     * 时间复杂度：O(n)
     */
    public class Solution {
        public int[] twoSum(int[] numbers, int target) {
            int[] res = new int[] {-1,-1};
            if (numbers.length < 2) { return res; }
            int lo = 0, hi = numbers.length-1;
            while (lo < hi) {
                int sum = numbers[lo] + numbers[hi];
                if (sum < target) {
                    lo++;
                } else if (sum > target) {
                    hi--;
                } else { // sum == target [FIND!!!]
                    res[0] = lo+1; res[1] = hi+1;
                    break;
                }
            }
            return res;
        }
    }
    private static TwoSumTwo test = new TwoSumTwo();
    private static Solution solution = test.new Solution();
    private static void callTwoSum(int[] numbers, int target) {
        int[] index = solution.twoSum(numbers,target);
        if (index[0] == -1 && index[1] == -1) {
            System.out.println("For array: " + Arrays.toString(numbers) + "     >>> Nothing Found!");
        } else {
            System.out.println("For array: " + Arrays.toString(numbers) + "     >>> " + numbers[index[0]-1] + " + " + numbers[index[1]-1] + " = " + target);
        }
    }
    private static void test() {
        int[] numbers1 = new int[]{2,7,11,15}; int target1 = 9;
        int[] numbers2 = new int[]{1,2}; int target2 = 5;
        int[] numbers3 = new int[]{-5,-3,1,6}; int target3 = -2;
        int[] numbers4 = new int[]{-1,0}; int target4 = -1;
        callTwoSum(numbers1,target1);
        callTwoSum(numbers2,target2);
        callTwoSum(numbers3,target3);
        callTwoSum(numbers4,target4);
    }
    public static void main(String[] args) {
        test();
    }
}
