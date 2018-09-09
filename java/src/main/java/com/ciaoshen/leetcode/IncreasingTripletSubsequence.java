/**
 * Leetcode - Algorithm - IncreasingTripletSubsequence
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class IncreasingTripletSubsequence implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private IncreasingTripletSubsequence() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public boolean increasingTriplet(int[] nums); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /* DFS */
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        private int[] local = new int[0];
        public boolean increasingTriplet(int[] nums) {
            local = nums;
            return dfs(Integer.MAX_VALUE,0,2);
        }
        /* 在index及其以后位置，找remain个大于pre的数 */
        private boolean dfs(int pre, int index, int remain) {
            if (remain == 0) { return true; }
            if (index == local.length) { return false; }
            if (local[index] > pre) {
                if (dfs(local[index],index+1,remain-1)) { return true; } // 当前数大于pre, 可以算上当前数
            }
            if (dfs(pre,index+1,remain)) { return true; }                // 无论当前数大小，都可以不算上当前数
            return dfs(local[index],index+1,2);                          // 也可以完全从当前数重新开始
        }
    }

    /* DP from bottom */
    private class Solution2 extends Solution {
        { super.id = 2; }
        public boolean increasingTriplet(int[] nums) {
            int first = 0, second = 0, third = 0;
            for (int i = nums.length - 3; i >= 0; i--) {
                first = nums[i];
                for (int j = i + 1; j < nums.length - 1; j++) {
                    second = nums[j];
                    if (second > first) {
                        for (int k = j + 1; k < nums.length; k++) {
                            third = nums[k];
                            if (third > second) { return true; }
                        }
                    }
                }
            }
            return false;
        }
    }

    /* O(n) Iteration */
    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public boolean increasingTriplet(int[] nums) {
            int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                int n = nums[i];
                /* Assertion: small < big */
                if (n <= small) {           // if n <= small update small
                    small = n;
                } else if (n <= big) {      // if small < n <= big update big
                    big = n;
                } else {                    // if small < big < n, result found!
                    return true;
                }
            }
            return false;
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
        private IncreasingTripletSubsequence problem = new IncreasingTripletSubsequence();
        private Solution solution = null;

        // call method in solution
        private void call(int[] nums, String answer) {
            System.out.println("Nums = " + Arrays.toString(nums));
            System.out.println("Result = " + solution.increasingTriplet(nums) + "\t\t[answer: " + answer + "]");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] nums1 = new int[]{1,2,3,4,5};
            int[] nums2 = new int[]{5,4,3,2,1};
            int[] nums3 = new int[]{5,1,5,5,2,5,4};
            int[] nums4 = new int[]{0,4,2,1,0,-1,-3};
            int[] nums5 = new int[]{2,1,5,0,4,6};
            int[] nums6 = new int[]{1,1,1,1,1,1,1,1,1,1,1};

            /** involk call() method HERE */
            call(nums1,"true");
            call(nums2,"false");
            call(nums3,"true");
            call(nums4,"false");
            call(nums5,"true");
            call(nums6,"false");
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        test.test(3);
    }
}
