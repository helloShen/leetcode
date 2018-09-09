/**
 * Leetcode - Algorithm - HIndexTwo
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class HIndexTwo implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private HIndexTwo() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int hIndex(int[] citations); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int hIndex(int[] citations) {
            for (int i = 0, j = citations.length; i < citations.length; i++, j--) {
                if (j <= citations[i]) { return j; }
            }
            return 0;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int hIndex(int[] citations) {
            int len = citations.length;
            int lo = 0, hi = len-1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (citations[mid] < (len - mid)) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            return len - lo;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int hIndex(int[] citations) {
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
        private HIndexTwo problem = new HIndexTwo();
        private Solution solution = null;

        // call method in solution
        private void call(int[] citations, int ans) {
            System.out.println("Citations = " + Arrays.toString(citations));
            System.out.println("H-Index: " + solution.hIndex(citations) + "\t[answer=" + ans + "]\n\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] citations1 = new int[]{0,1,3,5,6};
            int[] citations2 = new int[0];
            int[] citations3 = new int[]{0,1};

            /** involk call() method HERE */
            call(citations1,3);
            call(citations2,0);
            call(citations3,1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
