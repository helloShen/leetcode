/**
 * Leetcode - Algorithm - RangeSumArrayImmutable
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class RangeSumArrayImmutable implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private RangeSumArrayImmutable() {
        register(new Solution1(new int[]{-2, 0, 3, -5, 2, -1}));
        // register(new Solution2());
        // register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int sumRange(int i, int j); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        private int[] sumArray;
        private Solution1(int[] nums) {
            sumArray = new int[nums.length+1];
            for (int i = 0; i < nums.length; i++) {
                sumArray[i+1] = sumArray[i] + nums[i];
            }
        }
        // implement your solution's method HERE...
        public int sumRange(int i, int j) {
            return sumArray[j+1] - sumArray[i];
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public int sumRange(int i, int j) {
            return 0;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public int sumRange(int i, int j) {
            return 0;
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
        private RangeSumArrayImmutable problem = new RangeSumArrayImmutable();
        private Solution solution = null;

        // call method in solution
        private void call(int i , int j) {
            System.out.println("sum[" + i + "," + j + "] = " + solution.sumRange(i,j));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            call(1,2);
            call(0,2);
            call(2,4);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
    }
}
