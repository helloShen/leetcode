/**
 * Leetcode - Algorithm - MaxSizeSubarraySumK
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class MaxSizeSubarraySumK implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private MaxSizeSubarraySumK() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int maxSubArrayLen(int[] nums, int k); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int maxSubArrayLen(int[] nums, int k) {
            int[] sums = new int[nums.length+1];
            for (int i = 1; i < sums.length; i++) {
                sums[i] = sums[i-1] + nums[i-1];
            }
            for (int i = nums.length; i > 0; i--) {
                for (int j = 1, l = j + i - 1; l < sums.length; j++, l++) {
                    if (sums[l] - sums[j-1] == k) { return i; }
                }
            }
            return 0;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int maxSubArrayLen(int[] nums, int k) {
            Map<Integer,Integer> map = new HashMap<>();
            int sum = 0, max = 0;
            for (int i = 1; i <= nums.length; i++) {
                sum += nums[i-1];
                if (sum == k) {
                    max = Math.max(max,i);
                } else if (map.containsKey(sum - k)) {
                    max = Math.max(max,i - map.get(sum - k));
                    System.out.println("i = " + i + ", previous = " + map.get(sum-k));
                }
                if (!map.containsKey(sum)) { map.put(sum,i); } // 只保留和为某值的最短长度
                // System.out.println(map);
            }
            return max;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int maxSubArrayLen(int[] nums, int k) {
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
        private MaxSizeSubarraySumK problem = new MaxSizeSubarraySumK();
        private Solution solution = null;

        private void call(int[] nums, int k, int ans) {
            System.out.println("Numbers: " + Arrays.toString(nums));
            System.out.println("Longest Subarray Sum " + k + " = " + solution.maxSubArrayLen(nums,k));
            System.out.println("Answer should be: " + ans + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] nums1 = new int[]{1, -1, 5, -2, 3};
            int k1 = 3;
            int ans1 = 4;

            int[] nums2 = new int[]{-2, -1, 2, 1};
            int k2 = 1;
            int ans2 = 2;

            int[] nums3 = new int[]{1,0,-1};
            int k3 = -1;
            int ans3 = 2;

            int[] nums4 = new int[]{1,1,0};
            int k4 = 1;
            int ans4 = 2;

            /** involk call() method HERE */
            call(nums1, k1, ans1);
            call(nums2, k2, ans2);
            call(nums3, k3, ans3);
            call(nums4, k4, ans4);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
