/**
 * Leetcode - Algorithm - CountNumbersWithUniqueDigits
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class CountNumbersWithUniqueDigits implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private CountNumbersWithUniqueDigits() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int countNumbersWithUniqueDigits(int n); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private int[] base = new int[]{9,9,8,7,6,5,4,3,2,1};

        public int countNumbersWithUniqueDigits(int n) {
            if (n < 0) { return 0; }
            int count = 1;
            for (int i = 0; i < n; i++) {
                int product = 1;
                for (int j = 0; j <= i; j++) {
                    product *= base[j];
                }
                count += product;
            }
            return count;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int countNumbersWithUniqueDigits(int n) {
            if (n < 0) { return 0; }
            int availableNumber = 10, product = 1;
            int count = 1; // special case: only 0 can have 0 at first position
            for (int i = 0; i < n; i++) {
                product *= (i == 0)? (availableNumber - 1 ) : availableNumber; // avoid numbers start with 0
                count += product;
                availableNumber--;
            }
            return count;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int countNumbersWithUniqueDigits(int n) {
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
        private CountNumbersWithUniqueDigits problem = new CountNumbersWithUniqueDigits();
        private Solution solution = null;

        // call method in solution
        private void call(int n) {
            System.out.println(n + " \t -> \t " + solution.countNumbersWithUniqueDigits(n));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** involk call() method HERE */
            for (int i = -1; i < 10; i++) {
                call(i);
            }
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
