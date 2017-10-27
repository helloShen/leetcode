/**
 * Leetcode - Algorithm - LongestIncreasingSubsequence
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class LongestIncreasingSubsequence implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private LongestIncreasingSubsequence() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int lengthOfLIS(int[] nums); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        public int lengthOfLIS(int[] nums) {
            if (nums.length == 0) { return 0; }
            int[] dp = new int[nums.length];
            dp[nums.length-1] = 1;
            int max = 1;
            for (int i = nums.length-2; i >= 0; i--) {
                dp[i] = 1;
                for (int j = i+1; j < nums.length; j++) {
                    if (nums[j] > nums[i]) { dp[i] = Math.max(dp[i],dp[j]+1); }
                }
                max = Math.max(max,dp[i]);
            }
            return max;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        public int lengthOfLIS(int[] nums) {
            int[] tails = new int[nums.length];
            int cur = 0;
            for (int num : nums) {
                int lo = 0, hi = cur - 1;
                while (lo <= hi) {
                    int mid = lo + (hi - lo) / 2;
                    if (tails[mid] < num) {
                        lo = mid + 1;
                    } else if (tails[mid] > num) {
                        hi = mid - 1;
                    } else {
                        lo = mid; break;
                    }
                }
                if (lo == cur) {
                    tails[cur++] = num;
                } else {
                    tails[lo] = num;
                }
            }
            return cur;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public int lengthOfLIS(int[] nums) {
            return 3;
        }
    }
    // you can expand more solutions HERE if you want...


    /**
     * register a solution in the solution registry
     * return false if this type of solution already exist in the registry.
     */
    private boolean register(Solution s) {
        return (solutions.put(s.id,s) == null)? true : false;
    }
    /**
     * chose one of the solution to test
     * return null if solution id does not exist
     */
    private Solution solution(int id) {
        return solutions.get(id);
    }

    private static class Test {
        private LongestIncreasingSubsequence problem = new LongestIncreasingSubsequence();
        private Solution solution = null;


        // call method in solution
        private void call(int[] nums) {
            System.out.pritnln("For array: " + Arrays.toString(nums));
            System.out.println("Longest Increasing Subsequence is: " + solution.lengthOfLIS(nums));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);
            /** initialize your testcases HERE... */
            int[] nums1 = new int[]{10, 9, 2, 5, 3, 7, 101, 18};

            /** involk call() method HERE */
            call(nums1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
