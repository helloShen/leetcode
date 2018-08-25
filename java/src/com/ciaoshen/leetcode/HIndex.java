/**
 * Leetcode - Algorithm - HIndex
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class HIndex implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private HIndex() {
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
            if (citations == null || citations.length == 0) { return 0; }
            int len = citations.length;
            Arrays.sort(citations);
            for (int i = 0; i < len; i++) {
                int times = citations[i];
                int papers = len - i;
                if (times >= (len - i)) { return papers; }
            }
            return 0;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int hIndex(int[] citations) {
            if (citations == null || citations.length == 0) { return 0; }
            int len = citations.length;
            int[] freq = new int[len+1];
            for (int i = 0; i < len; i++) {
                if (citations[i] >= len) {
                    freq[len]++;
                } else {
                    freq[citations[i]]++;
                }
            }
            int sum = 0;
            for (int i = len; i >= 0; i--) {
                sum += freq[i];
                if (sum >= i) {
                    return i;
                }
            }
            return 0;
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
        private HIndex problem = new HIndex();
        private Solution solution = null;

        // call method in solution
        private void call(int[] citations, int ans) {
            System.out.println("Citations: " + Arrays.toString(citations));
            System.out.println("H Index = " + solution.hIndex(citations));
            System.out.println("Answer should be: " + ans + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] citations1 = new int[]{3, 0, 6, 1, 5};
            int ans1 = 3;
            int[] citations2 = new int[]{100};
            int ans2 = 1;

            /** involk call() method HERE */
            call(citations1,ans1);
            call(citations2,ans2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
