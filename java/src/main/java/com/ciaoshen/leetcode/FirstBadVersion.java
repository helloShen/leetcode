/**
 * Leetcode - Algorithm - FirstBadVersion
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class FirstBadVersion implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private FirstBadVersion() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        protected int badVersion = 0;
        protected void init(int badVersion) {
            this.badVersion = badVersion;
        }
        protected boolean isBadVersion(int version) {
            return version == badVersion;
        }
        abstract public int firstBadVersion(int n); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public int firstBadVersion(int n) {
            return binarySearch(1,n);
        }
        private int binarySearch(int lo, int hi) {
            if (lo > hi) { return lo; }
            int mid = lo + (hi - lo) / 2;
            boolean midIsBadVersion = isBadVersion(mid);
            if (midIsBadVersion) {
                return binarySearch(lo,mid-1);
            } else {
                return binarySearch(mid+1,hi);
            }
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public int firstBadVersion(int n) {
            return 2;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public int firstBadVersion(int n) {
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
        private FirstBadVersion problem = new FirstBadVersion();
        private Solution solution = null;

        // call method in solution
        private void call(int n) {
            solution.init(n);
            System.out.println("First Bad Version is: " + solution.firstBadVersion(n) + "\t\t[answer: " + n + "]");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** involk call() method HERE */
            Random r = new Random();
            for (int i = 0; i < 10; i++) {
                call(r.nextInt(100)+1);
            }
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
