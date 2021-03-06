/**
 * Leetcode - Algorithm - MissingNumber
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class MissingNumber implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private MissingNumber() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int missingNumber(int[] nums); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public int missingNumber(int[] nums) {
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                res ^= i; res ^= nums[i];
            }
            res ^= nums.length;
            return res;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public int missingNumber(int[] nums) {
            return 2;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public int missingNumber(int[] nums) {
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
        private MissingNumber problem = new MissingNumber();
        private Solution solution = null;

        // call method in solution
        private void call(int[] nums) {
            System.out.println(Arrays.toString(nums));
            System.out.println("Missing number is: " + solution.missingNumber(nums) + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            int[] nums1 = new int[]{0,1,3};
            int[] nums2 = new int[]{0};
            int[] nums3 = new int[]{1};
            int[] nums4 = new int[]{};
            int[] nums5 = new int[]{1,0};

            /** involk call() method HERE */
            call(nums1);
            call(nums2);
            call(nums3);
            call(nums4);
            call(nums5);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
