/**
 * Leetcode - Algorithm - SubarraySumEqualsK
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class SubarraySumEqualsK implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private SubarraySumEqualsK() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int subarraySum(int[] nums, int k); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int subarraySum(int[] nums, int k) {
            for (int i = 1; i < nums.length; i++) {
                nums[i] = nums[i-1] + nums[i];
            }
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                for (int j = i; j < nums.length; j++) {
                    int sum = nums[j] - ((i == 0)? 0 : nums[i-1]);
                    if (sum == k) { count++; }
                }
            }
            return count;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int subarraySum(int[] nums, int k) {
            int res = 0;
            int sum = 0;
            Map<Integer,Integer> map = new HashMap<>();
            map.put(0,1);
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                // System.out.println("Sum = " + sum);
                res += map.getOrDefault(sum - k,0);
                // System.out.println("Times of " + (sum - k) + " = " + map.getOrDefault(k-sum,0));
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
            return res;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int subarraySum(int[] nums, int k) {
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
        private SubarraySumEqualsK problem = new SubarraySumEqualsK();
        private Solution solution = null;

        private void call(int[] nums, int k, int ans) {
            System.out.println(Arrays.toString(nums) + ",\t K = " + k);
            System.out.println("Result = " + solution.subarraySum(nums,k) + ",\t [Answer: " + ans + "]\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] nums1 = new int[]{1,1,1};
            int k1 = 2;
            int ans1 = 2;

            /** involk call() method HERE */
            call(nums1,k1,ans1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
