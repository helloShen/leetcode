/**
 * Leetcode - Algorithm - SortTransformedArray
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class SortTransformedArray implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private SortTransformedArray() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int[] sortTransformedArray(int[] nums, int a, int b, int c); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
            if (nums == null) { return null; }
            int[] result = new int[nums.length];
            //calculate
            for (int i = 0; i < nums.length; i++) {
                result[i] = function(nums[i],a,b,c);
            }
            //sort
            int[] sorted = new int[nums.length];
            int p = (a <= 0)? 0 : nums.length - 1; //a > 0，正漏斗。 a <= 0，倒漏斗。
            for (int left = 0, right = nums.length-1; left <= right; ) {
                if (a <= 0) {
                    sorted[p++] = (result[left] <= result[right])? result[left++] : result[right--];
                } else {
                    sorted[p--] = (result[left] >= result[right])? result[left++] : result[right--];
                }
            }
            return sorted;
        }
        private int function(int x, int a, int b, int c) {
            return (a*x*x + b*x + c);
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
            return null;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
            return null;
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
        private SortTransformedArray problem = new SortTransformedArray();
        private Solution solution = null;

        // call method in solution
        private void call(int[] nums, int[] params, int[] answer) {
            System.out.println("X array: " + Arrays.toString(nums));
            System.out.println("Result array: " + Arrays.toString(solution.sortTransformedArray(nums,params[0],params[1],params[2])));
            System.out.println("Answer : " + Arrays.toString(answer) + "\n\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] nums1 = new int[]{-4,-2,2,4};
            int[] params1 = new int[]{1,3,5};
            int[] answer1 = new int[]{3,9,15,33};
            int[] nums2 = new int[]{-4,-2,2,4};
            int[] params2 = new int[]{-1,3,5};
            int[] answer2 = new int[]{-23,-5,1,7};
            int[] nums3 = new int[0];
            int[] params3 = params1;
            int[] answer3 = new int[0];
            /** involk call() method HERE */
            call(nums1,params1,answer1);
            call(nums2,params2,answer2);
            call(nums3,params3,answer3);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
