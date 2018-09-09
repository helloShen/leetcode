/**
 * Leetcode - Algorithm - Summary Ranges
 */
package com.ciaoshen.leetcode;
import java.util.*;

class SummaryRanges {
    public class Solution {
        /** [0,1,2,4,5,7]  ->  ["0->2","4->5","7"] */
        public List<String> summaryRanges(int[] nums) {
            List<String> result = new ArrayList<>();
            int cur = 0;
            while (cur < nums.length) {
                String range = String.valueOf(nums[cur++]);
                int start = cur;
                while (cur < nums.length && (nums[cur] == nums[cur-1] + 1)) { cur++; }
                if (cur > start) { range = range + "->" + String.valueOf(nums[cur-1]); }
                result.add(range);
            }
            return result;
        }
    }
    private class Test {
        private void callSummaryRanges(int[] nums, String answer) {
            System.out.println("Original array: " + Arrays.toString(nums));
            System.out.println("Summary Ranges is: " + solution.summaryRanges(nums) + "     [answer = " + answer + "]");
        }
        private void test() {
            int[] nums1 = new int[]{};
            int[] nums2 = new int[]{1};
            int[] nums3 = new int[]{0,1,2,4,5,7};
            callSummaryRanges(nums1,"[]");
            callSummaryRanges(nums2,"[1]");
            callSummaryRanges(nums3,"[0->2, 4-5, 7]");
        }
    }
    private static SummaryRanges problem = new SummaryRanges();
    private static Solution solution = problem.new Solution();
    private static Test test = problem.new Test();
    public static void main(String[] args) {
        test.test();
    }
}
