/**
 * Leetcode - Algorithm - CountingBits
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class CountingBits implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private CountingBits() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int[] countBits(int num); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /* 朴素办法 */
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int[] countBits(int num) {
            int[] res = new int[num+1];
            for (int i = 0; i <= num; i++) {
                res[i] = count(i);
            }
            return res;
        }
        private int count(int n) {
            int count = 0;
            while (n != 0) {
                n &= (n-1);
                ++count;
            }
            return count;
        }
    }
    /* DP */
    private class Solution2 extends Solution {
        { super.id = 2; }

        public int[] countBits(int num) {
            int[] res = new int[num+1];
            res[0] = -1;
            int start = 0, size = 1, slow = 0, fast = 0;
            while (true) {
                while (size-- > 0) {
                    if (fast > num) { return res; }
                    res[fast++] = res[slow++] + 1;
                }
                start = fast;
                size = fast;
                slow = 0;
            }
        }
    }
    /* Math: f[i] = f[i / 2] + i % 2 */
    private class Solution3 extends Solution {
        { super.id = 3; }

            public int[] countBits(int num) {
                int[] nums = new int[num+1];
                for (int i = 1; i <= num; i++) { nums[i] = nums[i/2] + i%2; }
                return nums;
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
        private CountingBits problem = new CountingBits();
        private Solution solution = null;

        // call method in solution
        private void call(int n) {
            System.out.println(n + ":   " + Arrays.toString(solution.countBits(n)));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** involk call() method HERE */
            call(10);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        test.test(2);
        test.test(3);
    }
}
