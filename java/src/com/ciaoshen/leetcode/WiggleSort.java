/**
 * Leetcode - Algorithm - WiggleSort
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class WiggleSort implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private WiggleSort() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public void wiggleSort(int[] nums); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public void wiggleSort(int[] nums) {
            boolean greaterThanNext = false;
            for (int i = 0; i < nums.length-1; i++) {
                if (greaterThanNext) {
                    if (nums[i] <= nums[i+1]) { exch(nums,i,i+1); }
                } else {
                    if (nums[i] >= nums[i+1]) { exch(nums,i,i+1); }
                }
                greaterThanNext = !greaterThanNext;
            }
        }
        private void exch(int[] nums, int a, int b) {
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public void wiggleSort(int[] nums) {
            // code here
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public void wiggleSort(int[] nums) {
            // code here
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
        private WiggleSort problem = new WiggleSort();
        private Solution solution = null;

        // call method in solution
        private void call(int[] nums) {
            System.out.println("Original Nums: " + Arrays.toString(nums));
            solution.wiggleSort(nums);
            System.out.println("Sorted Nums: " + Arrays.toString(nums));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] nums1 = new int[]{3,5,2,1,6,4};

            /** involk call() method HERE */
            call(nums1);
            // call(nums2);
            // call(nums3);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
