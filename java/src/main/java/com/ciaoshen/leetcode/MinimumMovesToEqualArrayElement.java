/**
 * Leetcode - Algorithm - MinimumMovesToEqualArrayElement
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class MinimumMovesToEqualArrayElement implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private MinimumMovesToEqualArrayElement() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int minMoves(int[] nums); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int minMoves(int[] nums) {
            if (nums.length < 2) { return 0; }
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int num : nums) {
                min = Math.min(min,num);
                max = Math.max(max,num);
            }
            if (min == max) { return 0; }
            int size = nums.length, divisor = size - 1, target = max;
            int diff = 0;
            for (int num : nums) {
                diff += (max - num);
            }
            while (diff % divisor != 0 || diff % (target - min) != 0) {
                diff += size;
                target += 1;
            }
            return target - min;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int minMoves(int[] nums) {
            if (nums.length < 2) { return 0; }
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int num : nums) {
                min = Math.min(min,num);
                max = Math.max(max,num);
            }
            int size = nums.length, divisor = size - 1, target = max;
            int diffInit = 0;
            for (int num : nums) {
                diffInit += (max - num);
            }
            return (max - min) * nums.length - diffInit;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int minMoves(int[] nums) {
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
        private MinimumMovesToEqualArrayElement problem = new MinimumMovesToEqualArrayElement();
        private Solution solution = null;

        // call method in solution
        private void call(int[] nums) {
            System.out.println(Arrays.toString(nums) + "    ->  " + solution.minMoves(nums));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] nums1 = new int[]{1,2,3};
            int[] nums2 = new int[]{1,2,3,4,5};
            int[] nums3 = new int[]{3,7,4};
            int[] nums4 = new int[]{4,8,5,10};

            /** involk call() method HERE */
            call(nums1);
            call(nums2);
            call(nums3);
            call(nums4);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
