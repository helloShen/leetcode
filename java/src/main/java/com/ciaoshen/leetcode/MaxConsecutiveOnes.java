/**
 * Leetcode - Algorithm - MaxConsecutiveOnes
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class MaxConsecutiveOnes implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private MaxConsecutiveOnes() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int findMaxConsecutiveOnes(int[] nums); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int findMaxConsecutiveOnes(int[] nums) {
            int count = 0, max = 0, cur = 0;
            while (cur < nums.length) {
                if (nums[cur] == 1) {
                    count = 0;
                    while (cur < nums.length && nums[cur] == 1) {
                        count++; cur++;
                    }
                    max = Math.max(count,max);
                } else {
                    cur++;
                }
            }
            return max;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int findMaxConsecutiveOnes(int[] nums) {
            return 2;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int findMaxConsecutiveOnes(int[] nums) {
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
        private MaxConsecutiveOnes problem = new MaxConsecutiveOnes();
        private Solution solution = null;

        private void call(int[] nums, int ans) {
            System.out.println("Nums: " + Arrays.toString(nums));
            System.out.println("Result = " + solution.findMaxConsecutiveOnes(nums) + ", \t[answer:" + ans + "]");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] nums1 = new int[]{1,1,0,1,1,1};
            int ans1 = 3;

            /** involk call() method HERE */
            call(nums1,ans1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
