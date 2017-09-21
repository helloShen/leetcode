/**
 * Leetcode - Algorithm - MaximumXorOfTwoNumbersInAnArray
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class MaximumXorOfTwoNumbersInAnArray implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private MaximumXorOfTwoNumbersInAnArray() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int findMaximumXOR(int[] nums); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public int findMaximumXOR(int[] nums) {
            Set<Integer> prefixSet = new HashSet<>();
            int mask = Integer.MIN_VALUE >> 1;
            int max = 0;
            for (int i = 30; i >= 0; i--,mask >>= 1) {
                prefixSet.clear();
                for (int num : nums) {
                    prefixSet.add(num & mask);
                }
                int candidate = max | (1 << i);
                for (Integer prefix : prefixSet) {
                    if (prefixSet.contains(candidate ^ prefix)) {
                        max = candidate;
                    }
                }
            }
            return max;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public int findMaximumXOR(int[] nums) {
            return 2;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public int findMaximumXOR(int[] nums) {
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
        private MaximumXorOfTwoNumbersInAnArray problem = new MaximumXorOfTwoNumbersInAnArray();
        private Solution solution = null;

        // call method in solution
        private void call(int[] nums, String answer) {
            System.out.println("Array: " + Arrays.toString(nums));
            System.out.println("Maximum XOR = " + solution.findMaximumXOR(nums) + "\t\t [answer:" + answer + "]");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] nums1 = new int[]{3, 10, 5, 25, 2, 8};
            // int[] nums2 = new int[]{};
            // int[] nums3 = new int[]{};

            /** involk call() method HERE */
            call(nums1,"28");
            // call(nums2,"");
            // call(nums3,"");
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
